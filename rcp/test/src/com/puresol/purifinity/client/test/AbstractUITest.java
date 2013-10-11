package com.puresol.purifinity.client.test;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * http://www.vogella.com/articles/SWTBot/article.html
 * 
 * @author Rick-Rainer Ludwig
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class AbstractUITest {

	private static SWTWorkbenchBot workbenchBot;

	@BeforeClass
	public static void initialize() {
		workbenchBot = new SWTWorkbenchBot();
		assertTrue("Purifinity was not started.", workbenchBot.activeShell()
				.getText().contains("Purifinity"));
		clearUI();
	}

	@AfterClass
	public static void exitApplication() {
		SWTBotMenu fileMenu = workbenchBot.menu("File");
		assertNotNull(fileMenu);

		SWTBotMenu exitMenu = fileMenu.menu("Exit");
		assertNotNull(exitMenu);
		exitMenu.click();
	}

	/**
	 * This method calls {@link #closeAllPerspectives()} and
	 * {@link #closeAllParts()} to clear the UI.
	 */
	protected static void clearUI() {
		closeAllPerspectives();
		closeAllParts();
	}

	/**
	 * This method calls 'Close All Perspectives' to close the perspectives
	 * currently opened.
	 */
	protected static void closeAllPerspectives() {
		SWTBotMenu windowMenu = getMenu("Window");
		SWTBotMenu closeAllPerspectivesMenu = getMenu(windowMenu,
				"Close All Perspectives");
		if (closeAllPerspectivesMenu.isEnabled()) {
			closeAllPerspectivesMenu.click();
		}
	}

	/**
	 * This method calls {@link #closeAllViews()} and {@link #closeAllEditors()}
	 * to close all open parts.
	 */
	protected static void closeAllParts() {
		closeAllViews();
		closeAllEditors();
	}

	/**
	 * This method uses 'Close Part' to close all open views.
	 */
	protected static void closeAllViews() {
		SWTBotMenu windowMenu = getMenu("Window");
		while (workbenchBot.views().size() > 0) {
			for (SWTBotView view : workbenchBot.views()) {
				view.setFocus();
				windowMenu.menu("Close Part").click();
			}
		}
	}

	/**
	 * This method uses 'Close Part' to close all open editors.
	 */
	protected static void closeAllEditors() {
		SWTBotMenu windowMenu = getMenu("Window");
		while (workbenchBot.editors().size() > 0) {
			for (SWTBotView view : workbenchBot.views()) {
				view.setFocus();
				windowMenu.menu("Close Part").click();
			}
		}
	}

	/**
	 * This method returns the {@link SWTWorkbenchBot} for the test.
	 * 
	 * @return A {@link SWTWorkbenchBot} object is returned.
	 */
	public static SWTWorkbenchBot getWorkbenchBot() {
		return workbenchBot;
	}

	/**
	 * This method checks for a menu with a specified name. An assert is
	 * included to check for the presence.
	 * 
	 * @param name
	 *            is the name of the menu to be found.
	 * @return An {@link SWTBotMenu} object is returned associated with the
	 *         found menu.
	 */
	protected static SWTBotMenu getMenu(String name) {
		try {
			SWTBotMenu menu = workbenchBot.menu(name);
			assertNotNull("No '" + name + "' menu.", menu);
			return menu;
		} catch (WidgetNotFoundException e) {
			fail("No '" + name + "' menu.");
			return null;
		}
	}

	/**
	 * This method checks for a menu item with a specified name. An assert is
	 * included to check for the presence.
	 * 
	 * @param parentMenu
	 *            is the parent menu of the menu item to be found.
	 * @param name
	 *            is the name of the menu to be found.
	 * @return An {@link SWTBotMenu} object is returned associated with the
	 *         found menu.
	 */
	protected static SWTBotMenu getMenu(SWTBotMenu parentMenu, String name) {
		try {
			SWTBotMenu menu = parentMenu.menu(name);
			assertNotNull("No '" + name + "' menu.", menu);
			return menu;
		} catch (WidgetNotFoundException e) {
			fail("No '" + name + "' menu.");
			return null;
		}
	}

	/**
	 * This menu searches with a {@link SWTBot} for a button with a specified
	 * label.
	 * 
	 * @param bot
	 *            is the {@link SWTBot} to be searched with for the button.
	 * @param label
	 *            the label of the button to be looked for.
	 * @return A {@link SWTBotButton} object is returned.
	 */
	protected SWTBotButton findButton(SWTBot bot, String label) {
		List<Button> controls = bot.getFinder().findControls(
				widgetOfType(Button.class));
		for (Button button : controls) {
			SWTBotButton botButton = new SWTBotButton(button);
			if (label.equals(botButton.getText())) {
				return botButton;
			}
		}
		fail("Could not find butto with label '" + label + "'.");
		return null;
	}

}
