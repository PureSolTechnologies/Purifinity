package com.puresol.apps;

import java.awt.BorderLayout;

import javax.i18n4j.Translator;
import javax.swingx.Label;
import javax.swingx.Menu;
import javax.swingx.MenuBar;
import javax.swingx.MenuItem;
import javax.swingx.Panel;

import com.puresol.gui.PureSolApplication;

public class CodeAnalysis extends PureSolApplication {

	private static final long serialVersionUID = -3002673096551916032L;

	private static final Translator translator = Translator
			.getTranslator(CodeAnalysis.class);

	public CodeAnalysis(String title) {
		super(title);
		initUI();
	}

	private void initUI() {
		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu(translator.i18n("Menu"));

		MenuItem exit = new MenuItem("Exit");
		exit.connect("start", this, "quit");

		menuBar.add(fileMenu);
		fileMenu.add(exit);
		setJMenuBar(menuBar);

		Panel panel = new Panel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(new Label("Test"), BorderLayout.CENTER);
		setContentPane(panel);
	}

	public static void main(String[] args) {
		new PureSolApplication("Code Analysis").run();
	}

}
