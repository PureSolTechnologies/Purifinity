package com.puresoltechnologies.purifinity.client.test;

import static org.junit.Assert.assertEquals;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.junit.Before;
import org.junit.Test;

public class EvaluationPerspectiveTest extends AbstractUITest {

	@Before
	public void setupCleanUI() {
		clearUI();
	}

	@Test
	public void testOpenPerspecitve() {
		SWTBotMenu windowMenu = getMenu("Window");
		SWTBotMenu showPerspectiveItem = windowMenu.menu("Show Perspective...");
		showPerspectiveItem.click();
		SWTBotShell activeShell = getWorkbenchBot().activeShell();
		assertEquals("Expected 'Open Perspective' dialog.", "Open Perspective",
				activeShell.getText());

		SWTBotTable table = activeShell.bot().table();
		table.select("Code Evaluation");

		SWTBotButton okButton = activeShell.bot().button("OK");
		okButton.click();
	}
}
