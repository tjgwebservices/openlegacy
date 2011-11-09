package org.openlegacy.terminal;

import apps.inventory.screens.InventoryManagement;
import apps.inventory.screens.ItemDetails1;
import apps.inventory.screens.ItemsList;
import apps.inventory.screens.MainMenu;
import apps.inventory.screens.SignOn;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openlegacy.AbstractTest;
import org.openlegacy.terminal.actions.SendKeyActions;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DefaultScreenEntityBinderTest extends AbstractTest {

	@Test
	public void testScreenBinder() throws IOException {

		SignOn signOn = terminalSession.getEntity(SignOn.class);
		Assert.assertNotNull(signOn);
		Assert.assertNotNull(signOn.getTerminalScreen());
		Assert.assertNotNull(signOn.getUserField());

		// tests doAction with expected class type
		MainMenu mainMenu = terminalSession.doAction(SendKeyActions.ENTER, null, MainMenu.class);
		Assert.assertNotNull(mainMenu);
		Assert.assertEquals("101", mainMenu.getCompany());

		InventoryManagement inventoryManagement = terminalSession.doAction(SendKeyActions.ENTER, null, InventoryManagement.class);
		Assert.assertNotNull(inventoryManagement);

		ItemsList itemList = terminalSession.doAction(SendKeyActions.ENTER, null, ItemsList.class);
		Assert.assertNotNull(itemList);

		ItemDetails1 itemDetails1 = terminalSession.doAction(SendKeyActions.ENTER, null, ItemDetails1.class);
		Assert.assertNotNull(itemDetails1);
		Assert.assertEquals("2000", itemDetails1.getItemNumber());

		Assert.assertEquals("2000", itemDetails1.getItemDetails2().getItemNumber());

		// tests @ScreenPart & related screen entity
		Assert.assertEquals("17/01/2005", itemDetails1.getItemDetails2().getAuditDetails().getCreatedDate());
		Assert.assertEquals("STUDENT2", itemDetails1.getItemDetails2().getAuditDetails().getCreatedBy());

		// make sure no extra fetch is made
		Assert.assertEquals("2000", itemDetails1.getItemDetails2().getItemNumber());

	}

}
