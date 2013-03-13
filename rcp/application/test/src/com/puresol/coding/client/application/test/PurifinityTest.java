package com.puresol.coding.client.application.test;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.Test;

public class PurifinityTest {

    @Test
    public void test() throws Exception {
	SWTWorkbenchBot bot = new SWTWorkbenchBot();
	Thread.sleep(1000);
	SWTBotMenu helpMenu = bot.menu("Help");
	helpMenu.click();
	Thread.sleep(1000);
	helpMenu.menu("About Source Code Analysis...").click();

	SWTBotShell aboutDialog = bot.shell("About Purifinity");
	SWTBot aboutBot = aboutDialog.bot();
	Thread.sleep(1000);
	aboutBot.button("OK").click();

	SWTBotMenu fileMenu = bot.menu("File");
	Thread.sleep(1000);
	fileMenu.click();
	Thread.sleep(1000);
	fileMenu.menu("Exit").click();
    }
}
