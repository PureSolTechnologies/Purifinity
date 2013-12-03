package com.puresol.coding.richclient.application.test;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.junit.Test;

public class SWTBotTest {

	private final SWTWorkbenchBot bot = new SWTWorkbenchBot();

	@Test
	public void test() {
		SWTBotMenu fileMenu = bot.menu("File");
		fileMenu.click();
		fileMenu.menu("Exit").click();
	}

}
