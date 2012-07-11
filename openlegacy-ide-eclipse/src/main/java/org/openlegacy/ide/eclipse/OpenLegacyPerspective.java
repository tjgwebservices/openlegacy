/*******************************************************************************
 * Copyright (c) 2012 OpenLegacy Inc.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     OpenLegacy Inc. - initial API and implementation
 *******************************************************************************/
package org.openlegacy.ide.eclipse;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.navigator.resources.ProjectExplorer;

/**
 * 
 * Sets the initial perspective for the IDE plug-in
 * 
 * @author MaayanS
 * 
 */
public class OpenLegacyPerspective implements IPerspectiveFactory {

	private static final String JAVA_PERSPECTIVE_ID = "org.eclipse.jdt.ui.JavaPerspective";
	private static final String DEBUG_PERSPECTIVE_ID = "org.eclipse.debug.ui.DebugPerspective";
	private static final String NEW_FOLDER_WIZARD_ID = "org.eclipse.ui.wizards.new.folder";
	private static final String NAVIGATOR_VIEW_ID = "org.eclipse.ui.views.ResourceNavigator";
	private static final String ERROR_LOG_VIEW_ID = "org.eclipse.pde.runtime.LogView";
	private static final String BOTTOM = "bottom";
	private static final String LEFT = "left";

	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
		definePerspectiveShortcuts(layout);
	}

	protected void defineLayout(IPageLayout layout) {
		// Creates a folder at the left side of the screen
		IFolderLayout leftFolder = layout.createFolder(LEFT, IPageLayout.LEFT, 0.25f, layout.getEditorArea());
		leftFolder.addView(ProjectExplorer.VIEW_ID);
		leftFolder.addView(NAVIGATOR_VIEW_ID);

		// Creates a folder at the bottom of the screen
		IFolderLayout bottom = layout.createFolder(BOTTOM, IPageLayout.BOTTOM, 0.76f, layout.getEditorArea());
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottom.addPlaceholder(ERROR_LOG_VIEW_ID);

		// IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.76f, LEFT);
		// bottomLeft.addView(PluginConstants.OPENLEGACY_IMAGE_VIEW_ID);
	}

	protected void definePerspectiveShortcuts(IPageLayout layout) {
		layout.addPerspectiveShortcut(DEBUG_PERSPECTIVE_ID);
		layout.addPerspectiveShortcut(JAVA_PERSPECTIVE_ID);
	}

	protected void defineActions(IPageLayout layout) {
		addWizardsShortcuts(layout);

		layout.addActionSet(JavaUI.ID_OPEN_ACTION_SET);
		layout.addActionSet(JavaUI.ID_SEARCH_ACTION_SET);
		layout.addActionSet(JavaUI.ID_ACTION_SET);
		layout.addActionSet(JavaUI.ID_ELEMENT_CREATION_ACTION_SET);
		layout.addActionSet(JavaUI.ID_CODING_ACTION_SET);
		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
		layout.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);
		layout.addActionSet("org.eclipse.ui.NavigateActionSet");

		// Add to "show views".
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		layout.addShowViewShortcut(ERROR_LOG_VIEW_ID);
		layout.addShowViewShortcut(PluginConstants.OPENLEGACY_IMAGE_VIEW_ID);
		layout.addShowViewShortcut(ProjectExplorer.VIEW_ID);
		layout.addShowViewShortcut(NAVIGATOR_VIEW_ID);

		// Add to "open perspective"
		layout.addPerspectiveShortcut(PluginConstants.OPENLEGACY_PERSPECTIVE_ID);
	}

	protected void addWizardsShortcuts(IPageLayout layout) {

		layout.addNewWizardShortcut(PluginConstants.NEW_PROJECT_WIZARD_ID);
		layout.addNewWizardShortcut(NEW_FOLDER_WIZARD_ID);
	}
}
