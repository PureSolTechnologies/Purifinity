package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.puresol.gui.Application;
import com.puresol.gui.DialogButtons;
import com.puresol.gui.PureSolDialog;

public class NewProjectAnalyserDialog extends PureSolDialog implements
	ActionListener {

    private static final long serialVersionUID = 7343378623505747546L;

    private static final Translator translator = Translator
	    .getTranslator(NewProjectAnalyserDialog.class);

    private final JTextField sourceDirectory = new JTextField();
    private final JButton sourceDirectoryButton = new JButton(
	    translator.i18n("Choose..."));
    private final JTextField workspaceDirectory = new JTextField();
    private final JButton workspaceDirectoryButton = new JButton(
	    translator.i18n("Choose..."));

    public NewProjectAnalyserDialog() {
	super(Application.getInstance(), translator
		.i18n("New Project Analyser"), true);
	initUI();
	initLocation();
    }

    private void initUI() {
	JPanel directoriesPanel = new JPanel();
	directoriesPanel.setLayout(new BoxLayout(directoriesPanel,
		BoxLayout.Y_AXIS));

	JPanel sourceDirectoryPanel = new JPanel();
	sourceDirectoryPanel.setLayout(new BoxLayout(sourceDirectoryPanel,
		BoxLayout.X_AXIS));
	sourceDirectoryPanel.add(new JLabel(translator
		.i18n("Source Directory: ")));
	sourceDirectoryPanel.add(sourceDirectory);
	sourceDirectoryPanel.add(sourceDirectoryButton);

	JPanel workspaceDirectoryPanel = new JPanel();
	workspaceDirectoryPanel.setLayout(new BoxLayout(
		workspaceDirectoryPanel, BoxLayout.X_AXIS));
	workspaceDirectoryPanel.add(new JLabel(translator
		.i18n("Workspace Directory: ")));
	workspaceDirectoryPanel.add(workspaceDirectory);
	workspaceDirectoryPanel.add(workspaceDirectoryButton);

	directoriesPanel.add(sourceDirectoryPanel);
	directoriesPanel.add(workspaceDirectoryPanel);

	add(directoriesPanel, BorderLayout.CENTER);

	setButtonVisible(DialogButtons.OK, true);
	setButtonVisible(DialogButtons.CANCEL, true);

	sourceDirectoryButton.addActionListener(this);
	workspaceDirectoryButton.addActionListener(this);
    }

    private void chooseSourceDirectory() {
	File directory = chooseDirectory(translator.i18n("Source Directory"));
	if (directory != null) {
	    sourceDirectory.setText(directory.toString());
	}
    }

    private void chooseWorkspaceDirectory() {
	File directory = chooseDirectory(translator.i18n("Workspace Directory"));
	if (directory != null) {
	    workspaceDirectory.setText(directory.toString());
	}
    }

    private File chooseDirectory(String title) {
	JFileChooser chooser = new JFileChooser();
	chooser.setDialogTitle(title);
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

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == sourceDirectoryButton) {
	    chooseSourceDirectory();
	} else if (e.getSource() == workspaceDirectoryButton) {
	    chooseWorkspaceDirectory();
	}
    }
}