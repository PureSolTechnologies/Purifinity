/***************************************************************************
 *
 *   CodeRangeAnalysisBrowser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swingx.List;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.connect.Slot;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.gui.CodeRangeViewer;

public class CodeRangeBrowser extends Panel {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeBrowser.class);

	private ProjectAnalyzer project = null;
	private List fileList = null;
	private List failedFilesList = null;
	private CodeRangeViewer codeRangeViewer = null;

	public CodeRangeBrowser() {
		super();
		initUI();
	}

	public CodeRangeBrowser(ProjectAnalyzer project) {
		super();
		initUI();
		setProjectAnalyser(project);
	}

	private void initUI() {
		setLayout(new BorderLayout());

		Panel listsPanel = new Panel();
		listsPanel.setLayout(new BoxLayout(listsPanel, BoxLayout.Y_AXIS));

		fileList = new List();
		fileList.connect("indexChanged", this, "showFile", int.class);
		ScrollPane fileScroller = new ScrollPane(fileList);
		fileScroller
				.setBorder(new TitledBorder(translator.i18n("Source Files")));
		listsPanel.add(fileScroller);

		failedFilesList = new List();

		add(listsPanel, BorderLayout.WEST);
		add(codeRangeViewer = new CodeRangeViewer(), BorderLayout.CENTER);
		add(new ScrollPane(failedFilesList), BorderLayout.EAST);
	}

	public void setProjectAnalyser(ProjectAnalyzer project) {
		this.project = project;
		refresh();
	}

	public void refresh() {
		fileList.removeAll();
		if (project == null) {
			return;
		}
		java.util.List<File> files = project.getFiles();
		if (files != null) {
			Collections.sort(files);
			fileList.setListData(new Vector<File>(files));
		}
		files = project.getFailedFiles();
		if (files != null) {
			Collections.sort(files);
			failedFilesList.setListData(new Vector<File>(files));
		}
	}

	@Slot
	public void showFile(int index) {
		File file = (File) fileList.getSelectedValue();
		AST ast = project.getAnalyzer(file).getAST();
		codeRangeViewer.setParserTree(ast);
	}

}
