package com.puresol.purifinity.client.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * http://www.vogella.com/articles/SWTBot/article.html
 * 
 * @author Rick-Rainer Ludwig
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ApplicationStartupTest {

	private static SWTBot bot;

	@BeforeClass
	public static void initialize() {
		bot = new SWTBot();
	}

	@Before
	public void setupCleanUI() throws InterruptedException {
		SWTBotMenu windowMenu = bot.menu("Window");
		assertNotNull("No 'Window' menu present.", windowMenu);
		SWTBotMenu closeAllPerspectivesMenu = windowMenu
				.menu("Close All Perspectives");
		assertNotNull("No 'Close All Perspectives' menu present.",
				closeAllPerspectivesMenu);
		if (closeAllPerspectivesMenu.isEnabled()) {
			closeAllPerspectivesMenu.click();
		}
	}

	private static SWTBotMenu checkAndReturnMenu(String name) {
		SWTBotMenu menu = bot.menu(name);
		assertNotNull("No '" + name + "' menu.", menu);
		return menu;
	}

	private static SWTBotMenu checkAndReturnMenu(SWTBotMenu parentMenu,
			String name) {
		SWTBotMenu menu = parentMenu.menu(name);
		assertNotNull("No '" + name + "' menu.", menu);
		return menu;
	}

	@Test
	public void testFileMenu() {
		SWTBotMenu fileMenu = checkAndReturnMenu("File");
		checkAndReturnMenu(fileMenu, "Print...");
		checkAndReturnMenu(fileMenu, "Restart");
		checkAndReturnMenu(fileMenu, "Export...");
		checkAndReturnMenu(fileMenu, "Exit");
	}

	@Test
	public void testOptionsMenu() {
		SWTBotMenu optionsMenu = checkAndReturnMenu("Options");
		// XXX Removed, but needs to be put in again!
		// checkAndReturnMenu(optionsMenu, "License Manager...");
		checkAndReturnMenu(optionsMenu, "Preferences...");
	}

	@Test
	public void testWindowMenu() {
		SWTBotMenu windowMenu = checkAndReturnMenu("Window");
		checkAndReturnMenu(windowMenu, "New Window");
		checkAndReturnMenu(windowMenu, "Show View...");
		checkAndReturnMenu(windowMenu, "Show Perspective...");
		checkAndReturnMenu(windowMenu, "Customize Perspective...");
		checkAndReturnMenu(windowMenu, "Save Perspective As...");
		checkAndReturnMenu(windowMenu, "Reset Perspective...");
		checkAndReturnMenu(windowMenu, "Close Perspective");
		checkAndReturnMenu(windowMenu, "Close All Perspectives");
	}

	@Test
	public void testHelpMenu() {
		SWTBotMenu helpMenu = checkAndReturnMenu("Help");
		checkAndReturnMenu(helpMenu, "Welcome");
		checkAndReturnMenu(helpMenu, "About Purifinity...");
	}

	@Test
	public void testAbout() {
		SWTBotMenu helpMenu = checkAndReturnMenu("Help");

		SWTBotMenu aboutPurifinityMenu = checkAndReturnMenu(helpMenu,
				"About Purifinity...");
		aboutPurifinityMenu.click();

		SWTBotShell activeShell = bot.activeShell();
		assertTrue("Expected 'About' dialog did not pop up.", activeShell
				.getText().contains("About"));
		SWTBotButton okButton = activeShell.bot().button("OK");
		assertNotNull("No 'OK' button found in 'About Purifinity' dialog.",
				okButton);
		okButton.click();
	}

	@AfterClass
	public static void exitApplication() {
		SWTBotMenu fileMenu = bot.menu("File");
		assertNotNull(fileMenu);

		SWTBotMenu exitMenu = fileMenu.menu("Exit");
		assertNotNull(exitMenu);
		exitMenu.click();
	}
}
