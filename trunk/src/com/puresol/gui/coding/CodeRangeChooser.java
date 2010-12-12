package com.puresol.gui.coding;

import java.io.File;
import java.util.Collections;
import java.util.Vector;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swingx.List;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.connect.Signal;
import javax.swingx.connect.Slot;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.ProjectAnalyzer;

/**
 * This GUI element is for selecting files and code ranges from a project
 * analyzer. Signals bind these selections to outer actions.
 * 
 * This element uses AnalyzedFileChooser for file selection.
 * 
 * @see AnalyzedFileChooser
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeRangeChooser extends Panel {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeChooser.class);

	private ProjectAnalyzer projectAnalyser = null;

	private final AnalyzedFileChooser fileList = new AnalyzedFileChooser();
	private final List codeRangeList = new List();

	public CodeRangeChooser() {
		super();
		initUI();
	}

	public CodeRangeChooser(ProjectAnalyzer projectAnalyser) {
		super();
		this.projectAnalyser = projectAnalyser;
		initUI();
	}

	private void initUI() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		fileList.connect("fileChanged", this, "fileSelected", File.class);
		// ScrollPane fileScroller = new ScrollPane(fileList);
		// fileScroller.setBorder(new TitledBorder(translator
		// .i18n("Analyzed Files")));
		// add(fileScroller);
		add(fileList);

		codeRangeList.connect("valueChanged", this, "codeRangeSelected",
				Object.class);
		ScrollPane codeRangeScroller = new ScrollPane(codeRangeList);
		codeRangeScroller.setBorder(new TitledBorder(translator
				.i18n("Analyzable Code Ranges")));
		add(codeRangeScroller);
	}

	public ProjectAnalyzer getProjectAnlayser() {
		return projectAnalyser;
	}

	public void setProjectAnalyser(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
		fileList.setProjectAnalyser(projectAnalyser);
		codeRangeList.removeAll();
	}

	public File getFile() {
		return fileList.getFile();
	}

	public CodeRange getCodeRange() {
		return (CodeRange) codeRangeList.getSelectedValue();
	}

	@Slot
	void fileSelected(File file) {
		Analyzer analyzer = projectAnalyser.getAnalyzer(file);
		if (analyzer != null) {
			java.util.List<CodeRange> codeRanges = analyzer
					.getAnalyzableCodeRanges();
			Collections.sort(codeRanges);
			codeRangeList.setListData(new Vector<CodeRange>(codeRanges));
			fileChanged(file);
		}
	}

	@Signal
	public void fileChanged(File file) {
		connectionManager.emitSignal("fileChanged", file);
	}

	@Slot
	void codeRangeSelected(Object o) {
		codeRangeChanged((CodeRange) o);
	}

	@Signal
	public void codeRangeChanged(CodeRange codeRange) {
		connectionManager.emitSignal("codeRangeChanged", codeRange);
	}
}
