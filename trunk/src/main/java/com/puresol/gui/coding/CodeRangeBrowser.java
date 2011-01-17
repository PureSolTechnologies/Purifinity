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
import java.io.IOException;

import javax.i18n4java.Translator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.gui.Application;
import com.puresol.gui.TreeViewer;
import com.puresol.gui.uhura.CodeRangeViewer;
import com.puresol.uhura.ast.ParserTree;

/**
 * The code range browser show all analyzed files and their analyzable code
 * ranges. The user has the chance to look into the source code and into the
 * parser tree generated by the parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeRangeBrowser extends JPanel implements TreeSelectionListener,
		ListSelectionListener {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeBrowser.class);

	private final CodeRangeChooser codeRangeChooser = new CodeRangeChooser();
	private final CodeRangeViewer codeRangeViewer = new CodeRangeViewer();
	private final TreeViewer<ParserTree> parserTreeViewer = new TreeViewer<ParserTree>();
	private final CodeRangeEvaluationBrowser codeRangeEvaluatorBrowser = new CodeRangeEvaluationBrowser();

	private ProjectAnalyzer project = null;

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
		codeRangeChooser.addTreeListener(this);
		codeRangeChooser.addListListener(this);

		JTabbedPane tabbedViewer = new JTabbedPane();
		tabbedViewer.add(translator.i18n("Source Code"), codeRangeViewer);
		tabbedViewer.add(translator.i18n("Parser Tree"), parserTreeViewer);
		tabbedViewer.add(translator.i18n("CodeRange Evaluators"),
				codeRangeEvaluatorBrowser);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, codeRangeChooser, tabbedViewer);
		add(splitPane, BorderLayout.CENTER);
	}

	public void setProjectAnalyser(ProjectAnalyzer project) {
		this.project = project;
		codeRangeChooser.setProjectAnalyser(project);
		codeRangeViewer.setCodeRange(new CodeRange("", CodeRangeType.FILE,
				new ParserTree("")));
		parserTreeViewer.setTreeData(new ParserTree(""));
		codeRangeEvaluatorBrowser.setProjectAnalyser(project);
	}

	private void showFile(AnalyzedFile file) {
		try {
			Analysis analysis = project.getAnalysis(file);
			if (analysis == null) {
				return;
			}
			ParserTree parserTree = analysis.getParserTree();
			ProgrammingLanguage language = analysis.getLanguage();

			codeRangeViewer.setCodeRange(language.getAnalyzableCodeRanges(
					parserTree).get(0));
			parserTreeViewer.setTreeData(parserTree);
			codeRangeEvaluatorBrowser.setAnalyzedFile(file);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Application.getInstance(),
					translator.i18n("IOException was thrown!"),
					translator.i18n("Error"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void showCodeRange(CodeRange codeRange) {
		if (codeRange != null) {
			ParserTree parserTree = codeRange.getParserTree();
			codeRangeViewer.setCodeRange(codeRange);
			parserTreeViewer.setTreeData(parserTree);
			codeRangeEvaluatorBrowser.setCodeRange(codeRange);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		showCodeRange(codeRangeChooser.getCodeRange());
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		showFile(codeRangeChooser.getAnalyzedFile());
	}
}
