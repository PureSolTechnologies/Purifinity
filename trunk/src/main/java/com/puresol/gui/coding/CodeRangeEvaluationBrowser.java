package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.i18n4java.Translator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.CodeRangeEvaluator;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.document.Document;
import com.puresol.document.convert.gui.GUIConverter;
import com.puresol.gui.Application;
import com.puresol.gui.progress.FinishListener;
import com.puresol.gui.progress.ProgressObservable;
import com.puresol.gui.progress.ProgressWindow;

public class CodeRangeEvaluationBrowser extends JPanel implements
		ListSelectionListener, FinishListener {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Logger logger = Logger
			.getLogger(CodeRangeEvaluationBrowser.class);
	private static final Translator translator = Translator
			.getTranslator(CodeRangeEvaluationBrowser.class);

	private ProjectAnalyzer projectAnalyzer = null;

	private final CodeRangeEvaluatorChooser evaluators = new CodeRangeEvaluatorChooser();
	private AnalyzedFile file;
	private CodeRange codeRange;
	private CodeRangeEvaluator evaluator = null;
	private final JScrollPane documentScroller = new JScrollPane();

	public CodeRangeEvaluationBrowser() {
		super();
		initUI();
	}

	public CodeRangeEvaluationBrowser(ProjectAnalyzer projectAnalyser) {
		super();
		initUI();
		setProjectAnalyser(projectAnalyser);
	}

	private void initUI() {
		setLayout(new BorderLayout());

		evaluators.addListSelectionListener(this);

		add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(
				evaluators), documentScroller), BorderLayout.CENTER);
	}

	public ProjectAnalyzer getProjectAnlayser() {
		return projectAnalyzer;
	}

	public void setProjectAnalyser(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyzer = projectAnalyser;
	}

	public AnalyzedFile getAnalyzedFile() {
		return file;
	}

	public void setAnalyzedFile(AnalyzedFile file) {
		this.file = file;
	}

	public CodeRange getCodeRange() {
		return codeRange;
	}

	public void setCodeRange(CodeRange codeRange) {
		this.codeRange = codeRange;
	}

	private void showReport(Object o) {
		try {
			if ((file == null) || (codeRange == null)
					|| (projectAnalyzer == null)) {
				return;
			}
			CodeRangeEvaluatorFactory evaluatorFactory = (CodeRangeEvaluatorFactory) evaluators
					.getSelectedValue();
			if (evaluatorFactory == null) {
				return;
			}
			Analysis analysis = projectAnalyzer.getAnalysis(file);
			evaluator = evaluatorFactory.create(analysis.getLanguage(),
					codeRange);
			ProgressWindow progress = new ProgressWindow(
					Application.getInstance(), true);
			progress.addFinishListener(this);
			progress.runAsynchronous(evaluator);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == evaluators) {
			showReport(evaluators.getSelectedValue());
		}
	}

	@Override
	public void finished(ProgressObservable observable) {
		if (evaluator != null) {
			Document document = evaluator.getReport();
			documentScroller.setViewportView(new GUIConverter(document)
					.toPanel());
		}
	}

	@Override
	public void terminated(ProgressObservable observable) {
		int result = JOptionPane
				.showConfirmDialog(
						Application.getInstance(),
						translator
								.i18n("Code range calcualtion was aborted. The results are now not completed and may be wrong.\n"
										+ "Do you want to have them displayed anyway?"),
						translator.i18n("Caluclation aborted"),
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			finished(observable);
		}
	}
}
