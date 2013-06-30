package com.puresol.purifinity.client.application.test;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class PurifinityTest {

	private static SWTWorkbenchBot bot;

	@BeforeClass
	public static void initialize() {
		bot = new SWTWorkbenchBot();
	}

	@AfterClass
	public static void destroy() {
		bot.sleep(2000);
	}

	@Test
	public void test() throws Exception {
		Thread.sleep(1000);

		bot.menu("Help").menu("About Source Code Analysis...").click();

		SWTBotShell aboutDialog = bot.shell("About Purifinity");
		SWTBot aboutBot = aboutDialog.bot();
		Thread.sleep(1000);
		aboutBot.button("OK").click();

		Thread.sleep(1000);
		bot.menu("File").menu("Exit").click();
	}
}
