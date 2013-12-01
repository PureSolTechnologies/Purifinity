package com.puresoltechnologies.purifinity.client.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * This test is used to check the basic Eclipse application which is used as
 * basis for the standalone client.
 * </p>
 * <p>
 * All basic menu entries need to be checked as well as the basic
 * functionalities like perspective handling.
 * </p>
 * 
 * @author Rick-Rainer Ludwig
 */
public class ApplicationStartupTest extends AbstractUITest {

	@Before
	public void clearUIBeforeEachTest() {
		clearUI();
	}

	@Test
	public void testFileMenu() {
		SWTBotMenu fileMenu = getMenu("File");
		getMenu(fileMenu, "Print...");
		getMenu(fileMenu, "Restart");
		getMenu(fileMenu, "Export...");
		getMenu(fileMenu, "Exit");
	}

	@Test
	public void testOptionsMenu() {
		SWTBotMenu optionsMenu = getMenu("Options");
		// XXX Removed, but needs to be put in again!
		// checkAndReturnMenu(optionsMenu, "License Manager...");
		getMenu(optionsMenu, "Preferences...");
	}

	@Test
	public void testWindowMenu() {
		SWTBotMenu windowMenu = getMenu("Window");
		getMenu(windowMenu, "New Window");
		getMenu(windowMenu, "Show View...");
		getMenu(windowMenu, "Show Perspective...");
		getMenu(windowMenu, "Customize Perspective...");
		getMenu(windowMenu, "Save Perspective As...");
		getMenu(windowMenu, "Reset Perspective...");
		getMenu(windowMenu, "Close Perspective");
		getMenu(windowMenu, "Close All Perspectives");
		getMenu(windowMenu, "Close Part");
	}

	@Test
	public void testHelpMenu() {
		SWTBotMenu helpMenu = getMenu("Help");
		getMenu(helpMenu, "Welcome");
		getMenu(helpMenu, "Help Contents...");
		getMenu(helpMenu, "Search...");
		getMenu(helpMenu, "Dynamic Help");
		getMenu(helpMenu, "PureSol Technologies Website...");
		getMenu(helpMenu, "Bug and Feature Request Tracker Website...");
		getMenu(helpMenu, "About Purifinity...");
		getMenu(helpMenu, "Check for Updates");
		getMenu(helpMenu, "Install New Software...");
	}

	@Test
	public void testPreferences() {
		SWTBotMenu optionsMenu = getMenu("Options");
		SWTBotMenu preferencesItem = optionsMenu.menu("Preferences...");
		preferencesItem.click();
		SWTBotShell activeShell = getWorkbenchBot().activeShell();
		assertNotNull("Exptected 'Preferences' dialog.", activeShell.getText()
				.contains("Preferences"));
		SWTBotButton okButton = activeShell.bot().button("OK");
		okButton.click();
	}

	@Test
	public void testShowView() {
		SWTBotMenu windowMenu = getMenu("Window");
		SWTBotMenu showViewItem = windowMenu.menu("Show View...");
		showViewItem.click();
		SWTBotShell activeShell = getWorkbenchBot().activeShell();
		assertNotNull("Exptected 'Show View' dialog.", activeShell.getText()
				.contains("Show View"));
		SWTBotButton cancelButton = activeShell.bot().button("Cancel");
		cancelButton.click();
	}

	@Test
	public void testShowPerspective() {
		SWTBotMenu windowMenu = getMenu("Window");
		SWTBotMenu showPerspectiveItem = windowMenu.menu("Show Perspective...");
		showPerspectiveItem.click();
		SWTBotShell activeShell = getWorkbenchBot().activeShell();
		assertNotNull("Exptected 'Show Perspective' dialog.", activeShell
				.getText().contains("Show Perspective"));
		SWTBotButton cancelButton = activeShell.bot().button("Cancel");
		cancelButton.click();
	}

	@Test
	public void testAbout() {
		SWTBotMenu helpMenu = getMenu("Help");

		SWTBotMenu aboutPurifinityMenu = getMenu(helpMenu,
				"About Purifinity...");
		aboutPurifinityMenu.click();

		SWTBotShell activeShell = getWorkbenchBot().activeShell();
		assertTrue("Expected 'About' dialog did not pop up.", activeShell
				.getText().contains("About"));
		SWTBotButton okButton = activeShell.bot().button("OK");
		assertNotNull("No 'OK' button found in 'About Purifinity' dialog.",
				okButton);
		okButton.click();
	}
}
