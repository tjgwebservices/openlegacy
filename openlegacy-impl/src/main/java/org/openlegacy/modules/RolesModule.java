/*******************************************************************************
 * Copyright (c) 2015 OpenLegacy Inc.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     OpenLegacy Inc. - initial API and implementation
 *******************************************************************************/

package org.openlegacy.modules;

import org.apache.commons.collections.CollectionUtils;
import org.openlegacy.EntitiesRegistry;
import org.openlegacy.EntityDefinition;
import org.openlegacy.db.definitions.DbEntityDefinition;
import org.openlegacy.definitions.ActionDefinition;
import org.openlegacy.modules.roles.Roles;
import org.openlegacy.rpc.definitions.RpcEntityDefinition;
import org.openlegacy.terminal.ScreenEntity;
import org.openlegacy.terminal.definitions.ScreenEntityDefinition;

import java.util.Arrays;

import javax.inject.Inject;

/**
 * @author Ivan Bort
 */
public class RolesModule implements Roles {

	@Inject
	private EntitiesRegistry<?, ?, ?> entitiesRegistry;

	@Override
	public void destroy() {}

	@Override
	public boolean isActionPermitted(ScreenEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEntityPermitted(Class<?> entityClass, String[] userRoles) {
		EntityDefinition<?> entityDefinition = entitiesRegistry.get(entityClass);
		return isEntityPermitted(entityDefinition, userRoles);
	}

	@Override
	public boolean isEntityPermitted(String entityName, String[] userRoles) {
		EntityDefinition<?> entityDefinition = entitiesRegistry.get(entityName);
		return isEntityPermitted(entityDefinition, userRoles);
	}

	private boolean isEntityPermitted(EntityDefinition<?> entityDefinition, String[] userRoles) {
		if (entityDefinition == null) {
			return false;
		}
		ActionDefinition actionDefinition = null;
		if (entityDefinition instanceof ScreenEntityDefinition) {
			actionDefinition = entityDefinition.getAction(org.openlegacy.terminal.actions.TerminalActions.SHOW.class);
		} else if (entityDefinition instanceof RpcEntityDefinition) {
			actionDefinition = entityDefinition.getAction(org.openlegacy.rpc.actions.RpcActions.SHOW.class);
		} else if (entityDefinition instanceof DbEntityDefinition) {
			actionDefinition = entityDefinition.getAction(org.openlegacy.db.actions.DbActions.SHOW.class);
		}
		if (actionDefinition != null && actionDefinition.isRolesRequired()) {
			if (actionDefinition.getRoles() != null && actionDefinition.getRoles().size() > 0) {
				if (!CollectionUtils.containsAny(actionDefinition.getRoles(), Arrays.asList(userRoles))) {
					return false;
				}
			}
		}
		return true;
	}

}
