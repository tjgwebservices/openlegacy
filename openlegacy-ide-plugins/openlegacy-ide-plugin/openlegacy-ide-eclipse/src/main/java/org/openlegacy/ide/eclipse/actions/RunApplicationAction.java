package org.openlegacy.ide.eclipse.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.openlegacy.ide.eclipse.util.PopupUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Timer;
import java.util.TimerTask;

public class RunApplicationAction extends AbstractAction {

	private static final String LAUNCHER_FILE_NAME = "run-application.launch";

	@Override
	public void run(IAction arg0) {
		final IProject project = (IProject)((TreeSelection)getSelection()).getFirstElement();
		String backEndSolutuion = EclipseDesignTimeExecuter.instance().getPreference(project, "BACKEND_SOLUTION");
		if (backEndSolutuion != null && backEndSolutuion.equals("SCREEN")) {
			ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
			IFile configFile = project.getFile(LAUNCHER_FILE_NAME);
			if (configFile.exists()) {
				ILaunchConfiguration configuration = manager.getLaunchConfiguration(configFile);
				DebugUITools.launch(configuration, ILaunchManager.RUN_MODE);
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						try {
							IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
							IWebBrowser browser = browserSupport.createBrowser("openlegacy");
							browser.openURL(new URL("http://localhost:8080/" + project.getName()));
						} catch (PartInitException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, 13000);
			} else {
				PopupUtil.error(MessageFormat.format("Required launcher file \"{0}\" is missing!", LAUNCHER_FILE_NAME),
						"Missing file");
			}
		} else {
			PopupUtil.error("Current project type doesn't support this action!", "Unsupported project type");
		}
	}
}