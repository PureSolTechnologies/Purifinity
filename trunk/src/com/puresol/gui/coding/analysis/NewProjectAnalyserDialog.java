package com.puresol.gui.coding.analysis;

import java.awt.BorderLayout;
import java.io.File;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swingx.Button;
import javax.swingx.Dialog;
import javax.swingx.Label;
import javax.swingx.Panel;
import javax.swingx.TextField;
import javax.swingx.connect.Slot;

public class NewProjectAnalyserDialog extends Dialog {

	private static final long serialVersionUID = 7343378623505747546L;

	private static final Translator translator = Translator
			.getTranslator(NewProjectAnalyserDialog.class);

	private TextField sourceDirectory;
	private Button sourceDirectoryButton;
	private TextField workspaceDirectory;
	private Button workspaceDirectoryButton;

	public NewProjectAnalyserDialog() {
		super(translator.i18n("New Project Analyser"), true);
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout());

		Panel directoriesPanel = new Panel();
		directoriesPanel.setLayout(new BoxLayout(directoriesPanel,
				BoxLayout.Y_AXIS));

		Panel sourceDirectoryPanel = new Panel();
		sourceDirectoryPanel.setLayout(new BoxLayout(sourceDirectoryPanel,
				BoxLayout.X_AXIS));
		sourceDirectoryPanel.add(new Label(translator
				.i18n("Source Directory: ")));
		sourceDirectoryPanel.add(sourceDirectory = new TextField());
		sourceDirectoryPanel.add(sourceDirectoryButton = new Button(translator
				.i18n("Choose...")));
		sourceDirectoryButton.connect("start", this, "chooseSourceDirectory");

		Panel workspaceDirectoryPanel = new Panel();
		workspaceDirectoryPanel.setLayout(new BoxLayout(
				workspaceDirectoryPanel, BoxLayout.X_AXIS));
		workspaceDirectoryPanel.add(new Label(translator
				.i18n("Workspace Directory: ")));
		workspaceDirectoryPanel.add(workspaceDirectory = new TextField());
		workspaceDirectoryPanel.add(workspaceDirectoryButton = new Button(
				translator.i18n("Choose...")));
		workspaceDirectoryButton.connect("start", this,
				"chooseWorkspaceDirectory");

		directoriesPanel.add(sourceDirectoryPanel);
		directoriesPanel.add(workspaceDirectoryPanel);

		add(directoriesPanel, BorderLayout.CENTER);
		add(createDefaultOkCancelPanel(), BorderLayout.SOUTH);

		pack();
	}

	@Slot
	void chooseSourceDirectory() {
		File directory = chooseDirectory(translator.i18n("Source Directory"));
		if (directory != null) {
			sourceDirectory.setText(directory.toString());
		}
	}

	@Slot
	void chooseWorkspaceDirectory() {
		File directory = chooseDirectory(translator.i18n("Workspace Directory"));
		if (directory != null) {
			workspaceDirectory.setText(directory.toString());
		}
	}

	private File chooseDirectory(String title) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(translator.i18n("Workspace Directory"));
		chooser.setFileHidingEnabled(true);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}

	public File getSourceDirectory() {
		return new File(sourceDirectory.getText());
	}

	public File getWorkspaceDirectory() {
		return new File(workspaceDirectory.getText());
	}
}
