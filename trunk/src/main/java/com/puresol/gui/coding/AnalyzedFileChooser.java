package com.puresol.gui.coding;

import java.io.File;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.connect.Signal;
import javax.swingx.connect.Slot;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.gui.TreeViewer;
import com.puresol.trees.FileTree;
import com.puresol.utils.FileUtilities;

/**
 * This GUI element is for selecting analyzed files from a project analyzer.
 * Some signals bind actions within this element to outer actions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyzedFileChooser extends Panel {

	private static final long serialVersionUID = 7855693564694783199L;

	private ProjectAnalyzer projectAnalyzer = null;

	private final TreeViewer<FileTree> fileTree = new TreeViewer<FileTree>();

	public AnalyzedFileChooser() {
		super();
		initUI();
	}

	public AnalyzedFileChooser(ProjectAnalyzer projectAnalyser) {
		super();
		this.projectAnalyzer = projectAnalyser;
		initUI();
	}

	private void initUI() {
		fileTree.connect("valueChanged", this, "fileSelected");

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(new ScrollPane(fileTree));
	}

	public ProjectAnalyzer getProjectAnlayzer() {
		return projectAnalyzer;
	}

	public void setProjectAnalyzer(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyzer = projectAnalyser;
		refresh();
	}

	private void refresh() {
		if (projectAnalyzer != null) {
			java.util.List<File> files = projectAnalyzer.getFiles();
			Collections.sort(files);
			fileTree.setTreeData(FileUtilities.convertFileListToTree(
					projectAnalyzer.getProjectDirectory().getPath(), files));
		} else {
			fileTree.removeAll();
		}
	}

	public File getFile() {
		return fileTree.getSelection().getPathFile();
	}

	@Slot
	void fileSelected() {
		fileChanged(fileTree.getSelection().getPathFile());
	}

	@Signal
	public void fileChanged(File file) {
		connectionManager.emitSignal("fileChanged", file);
	}
}
