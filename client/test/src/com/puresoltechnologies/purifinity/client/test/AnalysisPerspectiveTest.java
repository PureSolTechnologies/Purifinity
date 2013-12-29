package com.puresoltechnologies.purifinity.client.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AnalysisPerspectiveTest extends AbstractUITest {

	@BeforeClass
	public static void cleanCodeStore() {
		// TODO Storage reset!!!
		// assertTrue("Could not delete code storage.",
		// DirectoryUtilities.deleteDirectoryRecursivly(AnalysisStoreImpl
		// .getStorageDirectory()));
	}

	@Before
	public void setupCleanUI() {
		clearUI();

		SWTBotMenu windowMenu = getMenu("Window");
		SWTBotMenu showPerspectiveItem = windowMenu.menu("Show Perspective...");
		showPerspectiveItem.click();
		SWTBotShell activeShell = getWorkbenchBot().activeShell();
		assertEquals("Expected 'Open Perspective' dialog.", "Open Perspective",
				activeShell.getText());

		SWTBotTable table = activeShell.bot().table();
		table.select("Analysis");

		SWTBotButton okButton = activeShell.bot().button("OK");
		okButton.click();
	}

	@Test
	public void testCreateAndDeleteProject() throws InterruptedException {
		SWTWorkbenchBot workbenchBot = getWorkbenchBot();
		SWTBotView analysisProjectsView = workbenchBot
				.viewByTitle("Analysis Projects");
		SWTBot analysisProjectsViewBot = analysisProjectsView.bot();

		createProject(workbenchBot, analysisProjectsViewBot);
		waitForAnalysisFinish(analysisProjectsViewBot);
		deleteProject(workbenchBot, analysisProjectsView);
	}

	private void waitForAnalysisFinish(SWTBot analysisProjectsViewBot)
			throws InterruptedException {
		final SWTBotTable table = analysisProjectsViewBot.table();
		UITestHelper.waitFor(new UITestHelper.Condition() {

			@Override
			public boolean valid() {
				try {
					table.select("Project Name");
					return true;
				} catch (IllegalArgumentException e) {
					return false;
				}
			}
		}, 30, 1, TimeUnit.SECONDS);
	}

	private void createProject(SWTWorkbenchBot workbenchBot,
			SWTBot analysisProjectsViewBot) {
		SWTBotToolbarButton addButton = analysisProjectsViewBot
				.toolbarButton(0);
		assertEquals("Add...", addButton.getText());
		addButton.click();
		SWTBotShell addProjectShell = workbenchBot.activeShell();
		assertEquals("New Analysis", addProjectShell.getText());
		SWTBot bot = addProjectShell.bot();
		bot.text(0).setText("Project Name");
		bot.text(1).setText(
				new File(System.getProperty("user.dir")).getAbsolutePath());
		bot.text(2).setText(
				"The best and most sexy software project in the world...");
		findButton(bot, IDialogConstants.FINISH_LABEL).click();
	}

	private void deleteProject(SWTWorkbenchBot workbenchBot,
			SWTBotView analysisProjectsView) {
		SWTBotToolbarButton deleteButton = analysisProjectsView.bot()
				.toolbarButton(2);
		assertEquals("Delete...", deleteButton.getText());
		deleteButton.click();
		SWTBotShell deleteProjectShell = workbenchBot.activeShell();
		assertEquals("Delete?", deleteProjectShell.getText());
		findButton(deleteProjectShell.bot(), IDialogConstants.YES_LABEL)
				.click();
	}

}
