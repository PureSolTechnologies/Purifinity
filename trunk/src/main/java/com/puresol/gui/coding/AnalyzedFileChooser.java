package com.puresol.gui.coding;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.puresol.coding.analysis.AnalyzedFile;
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
public class AnalyzedFileChooser extends TreeViewer<FileTree> {

	private static final long serialVersionUID = 7855693564694783199L;

	private ProjectAnalyzer projectAnalyzer = null;

	public AnalyzedFileChooser() {
		super();
	}

	public AnalyzedFileChooser(ProjectAnalyzer projectAnalyser) {
		super();
		setProjectAnalyzer(projectAnalyser);
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
			java.util.List<AnalyzedFile> analyzedFiles = projectAnalyzer
					.getAnalyzedFiles();
			List<File> files = new ArrayList<File>();
			for (AnalyzedFile analyzedFile : analyzedFiles) {
				files.add(analyzedFile.getFile());
			}
			Collections.sort(files);
			setTreeData(FileUtilities.convertFileListToTree(projectAnalyzer
					.getProjectDirectory().getPath(), files));
		} else {
			removeAll();
		}
	}

	public AnalyzedFile getAnalyzedFile() {
		return projectAnalyzer.findAnalyzedFileBySourceFile(getSelection()
				.getPathFile());
	}
}
