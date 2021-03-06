package org.openlegacy.rpc.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openlegacy.exceptions.OpenLegacyRuntimeException;
import org.openlegacy.exceptions.SessionInitException;
import org.openlegacy.rpc.RpcSession;
import org.openlegacy.rpc.RpcSessionFactory;
import org.openlegacy.rpc.actions.RpcAction;
import org.openlegacy.support.AbstractSessionPoolFactory;
import org.openlegacy.utils.ReflectionUtil;
import org.springframework.context.ApplicationContext;

import java.text.MessageFormat;

import javax.inject.Inject;

public class SimpleRpcSessionPoolFactory extends AbstractSessionPoolFactory<RpcSession, RpcAction> implements RpcSessionFactory {

	private static final Log logger = LogFactory.getLog(SimpleRpcSessionPoolFactory.class);

	@Inject
	private ApplicationContext applicationContext;

	@Override
	protected void init() {
		if (keepAliveAction != null) {
			keepAliveThread = new Thread() {

				@Override
				public void run() {
					while (!stopThreads) {
						try {
							logger.debug("Keep alive is sleeping");
							sleep(keepAliveInterval);
						} catch (InterruptedException e) {
							throw (new RuntimeException(e));
						}
						RpcAction keepAliveAction1 = ReflectionUtil.newInstance(keepAliveAction);

						RpcSession[] sessions = blockingQueue.toArray(new RpcSession[blockingQueue.size()]);

						logger.debug(sessions.length + " sessions found in queue during keep alive");
						for (RpcSession session : sessions) {
							if (!actives.contains(session)) {
								try {

									keepAliveAction1.perform(session, null);
									logger.debug("Keep alive action " + keepAliveAction.getSimpleName() + " performed for "
											+ session);
								} catch (Exception e) {
									logger.fatal(e, e);
								}
							}
						}
					}
				}

			};
			keepAliveThread.start();
		}
		if (cleanupAction != null) {
			returnSessionsThread = new Thread() {

				@Override
				public void run() {
					while (!stopThreads) {
						while (dirties.size() > 0) {
							RpcSession session = dirties.get(0);
							logger.debug(MessageFormat.format("Async clean up of session {0}", session));
							returnSessionInner(session);
							dirties.remove(0);
						}
						try {
							Thread.sleep(returnSessionsInterval);
						} catch (InterruptedException e) {
							throw (new RuntimeException(e));
						}
					}
				}
			};
			returnSessionsThread.start();
		}

	}

	@Override
	protected void initSession() {
		try {
			super.initSession();
		} catch (SessionInitException e) {
			logger.debug(e.getMessage());
			return;
		}
		RpcSession rpcSession = applicationContext.getBean(RpcSession.class);
		logger.debug(MessageFormat.format("New session {0} created for pool", rpcSession));
		if (initAction != null) {
			initAction.perform(rpcSession, null);
			// ReflectionUtil.newInstance(initAction).perform(rpcSession, null);
			logger.debug(MessageFormat.format("New session {0} init action {1} performed", rpcSession,
					initAction.getClass().getSimpleName()));
		}
		try {
			blockingQueue.put(rpcSession);
			logger.debug("New session " + rpcSession + " added to blocking queue");
		} catch (InterruptedException e) {
			throw (new OpenLegacyRuntimeException(e));
		}
	}

}
