// WARNING: DO NOT EDIT THIS FILE.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.openlegacy.mvc.remoting.entities;

import org.openlegacy.terminal.ScreenEntity;

import java.io.Serializable;

privileged aspect InventoryMenu_Aspect {

	declare parents: InventoryMenu implements ScreenEntity, Serializable;
	private String InventoryMenu.focusField;

	public Integer InventoryMenu.getMenuSelection() {
		return this.menuSelection;
	}

	public void InventoryMenu.setMenuSelection(Integer menuSelection) {
		this.menuSelection = menuSelection;
	}

	public String InventoryMenu.getFocusField() {
		return focusField;
	}

	public void InventoryMenu.setFocusField(String focusField) {
		this.focusField = focusField;
	}

}