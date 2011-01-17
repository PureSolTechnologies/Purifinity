package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

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
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.document.Document;
import com.puresol.document.convert.gui.GUIConverter;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

public class CodeRangeEvaluationBrowser extends JPanel implements
		ListSelectionListener {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Logger logger = Logger
			.getLogger(CodeRangeEvaluationBrowser.class);

	private ProjectAnalyzer projectAnalyser = null;

	private final CodeRangeEvaluatorChooser evaluators = new CodeRangeEvaluatorChooser();
	private AnalyzedFile file;
	private CodeRange codeRange;

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
				evaluators), new JPanel()), BorderLayout.CENTER);
	}

	public ProjectAnalyzer getProjectAnlayser() {
		return projectAnalyser;
	}

	public void setProjectAnalyser(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
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
					|| (projectAnalyser == null)) {
				return;
			}
			CodeRangeEvaluatorFactory evaluatorFactory = (CodeRangeEvaluatorFactory) evaluators
					.getSelectedValue();
			if (evaluatorFactory == null) {
				return;
			}
			Analysis analysis = projectAnalyser.getAnalysis(file);
			Evaluator evaluator = evaluatorFactory.create(
					analysis.getLanguage(), codeRange);
			File reportFile = file.getReportFile(evaluator, codeRange);
			Document document = (Document) Persistence.restore(reportFile);
			removeAll();
			add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
					new JScrollPane(evaluators), new JScrollPane(
							new GUIConverter(document).toPanel())),
					BorderLayout.CENTER);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		showReport(evaluators.getSelectedValue());
	}
}
