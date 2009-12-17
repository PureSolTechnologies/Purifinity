package com.puresol.apps;

import java.io.File;

import javax.i18n4j.Translator;
import javax.swing.JFileChooser;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.Label;
import javax.swingx.Menu;
import javax.swingx.MenuBar;
import javax.swingx.MenuItem;
import javax.swingx.connect.Slot;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.puresol.coding.ProjectAnalyser;
import com.puresol.gui.PureSolApplication;
import com.puresol.gui.coding.ProjectAnalysisBrowser;

public class CodeAnalysis extends PureSolApplication {

	private static final long serialVersionUID = -3002673096551916032L;

	private static final Translator translator = Translator
			.getTranslator(CodeAnalysis.class);

	private Label directory = null;
	private ProjectAnalysisBrowser browser = null;
	private ProjectAnalyser analyser = null;

	public CodeAnalysis(String title) {
		super(title);
		initMenu();
		initDesktop();
	}

	private void initMenu() {
		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu(translator.i18n("File"));
		Menu projectMenu = new Menu(translator.i18n("Project"));

		MenuItem exit = new MenuItem("Exit");
		exit.connect("start", this, "quit");

		MenuItem analyse = new MenuItem("Analyse...");
		analyse.connect("start", this, "analyse");

		menuBar.add(fileMenu);
		fileMenu.add(exit);

		menuBar.add(projectMenu);
		projectMenu.add(analyse);

		setJMenuBar(menuBar);
	}

	private void initDesktop() {
		BorderLayoutWidget widget = new BorderLayoutWidget();
		setContentPane(widget);
		browser = new ProjectAnalysisBrowser();
		widget.setCenter(browser);
		directory = new Label("");
		widget.setNorth(directory);
	}

	@Slot
	public void analyse() {
		JFileChooser directory = new JFileChooser();
		directory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = directory.showOpenDialog(this);
		if (result == JFileChooser.CANCEL_OPTION) {
			return;
		}
		File file = directory.getSelectedFile();
		this.directory.setText(file.getPath());
		analyser = new ProjectAnalyser(file.getPath() + "**/*");
		analyser.update();
		browser.setProjectAnalyser(analyser);
	}

	public static void main(String[] args) {
		Logger.getRootLogger().setLevel(Level.INFO);
		new CodeAnalysis("Code Analysis").run();
	}
}
