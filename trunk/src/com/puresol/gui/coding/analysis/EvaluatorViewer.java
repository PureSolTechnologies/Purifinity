package com.puresol.gui.coding.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.i18n4j.Translator;
import javax.swing.BoxLayout;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.HTMLTextPane;
import javax.swingx.List;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.TabbedPane;
import javax.swingx.TextField;
import javax.swingx.connect.Slot;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.reporting.ReportingFormat;

public class EvaluatorViewer extends BorderLayoutWidget {

	private static final long serialVersionUID = 7729851519489273274L;

	private static final Logger logger = Logger
			.getLogger(EvaluatorViewer.class);
	private static final Translator translator = Translator
			.getTranslator(EvaluatorViewer.class);

	private final TextField evaluatorName = new TextField();
	private final HTMLTextPane description = new HTMLTextPane();
	private final HTMLTextPane projectSummary = new HTMLTextPane();
	private final List codeRangeList = new List();
	private final HTMLTextPane evaluatorCodeRangeComment = new HTMLTextPane();

	private Evaluator evaluator = null;

	public EvaluatorViewer() {
		super();
		initUI();
	}

	public EvaluatorViewer(Evaluator evaluator) {
		super();
		initUI();
		setEvaluator(evaluator);
	}

	private void initUI() {
		setNorth(evaluatorName);
		TabbedPane tabbedPane = new TabbedPane();
		setCenter(tabbedPane);
		tabbedPane.add(new ScrollPane(description), translator
				.i18n("Evaluator Description"));
		tabbedPane.add(new ScrollPane(projectSummary), translator
				.i18n("Project Summary"));
		Panel codeRangePanel = new Panel();
		codeRangePanel.setLayout(new BoxLayout(codeRangePanel, BoxLayout.X_AXIS));
		tabbedPane.add(codeRangePanel, translator.i18n("Evaluators Code Range Summary"));

		codeRangePanel.add(new ScrollPane(codeRangeList));
		codeRangeList.connect("valueChanged", this, "codeRangeChanged",
				Object.class);
		codeRangePanel.add(new ScrollPane(evaluatorCodeRangeComment));
	}

	/**
	 * @return the evaluator
	 */
	public Evaluator getEvaluator() {
		return evaluator;
	}

	/**
	 * @param evaluator
	 *            the evaluator to set
	 */
	public void setEvaluator(Evaluator evaluator) {
		this.evaluator = evaluator;
		refresh();
	}

	private void refresh() {
		evaluatorName.setText(evaluator.getName());
		try {
			description.setText(evaluator.getDescription(ReportingFormat.HTML));
		} catch (UnsupportedReportingFormatException e) {
			description.setText(translator.i18n(
					"Evaluator does not (yet) support {0} reporting format.",
					ReportingFormat.HTML));
			logger.warn(e.getMessage(), e);
		}

		try {
			projectSummary.setText(evaluator
					.getProjectComment(ReportingFormat.HTML));
		} catch (UnsupportedReportingFormatException e) {
			projectSummary.setText(translator.i18n(
					"Evaluator does not (yet) support {0} reporting format.",
					ReportingFormat.HTML));
			logger.warn(e.getMessage(), e);
		}
		evaluatorCodeRangeComment.setText("");
		refreshCodeRangeList();
	}

	private void refreshCodeRangeList() {
		codeRangeList.removeAll();
		java.util.List<CodeRange> codeRanges = new ArrayList<CodeRange>();
		for (File file : evaluator.getFiles())
			codeRanges.addAll(evaluator.getCodeRanges(file));
		Collections.sort(codeRanges);
		codeRangeList.setListData(codeRanges.toArray());
	}

	@Slot
	public void codeRangeChanged(Object o) {
		CodeRange codeRange = (CodeRange) o;
		System.out.println(codeRange);
		try {
			evaluatorCodeRangeComment.setText(evaluator.getCodeRangeComment(
					codeRange, ReportingFormat.HTML));
		} catch (UnsupportedReportingFormatException e) {
			evaluatorCodeRangeComment.setText(translator.i18n(
					"Evaluator does not (yet) support {0} reporting format.",
					ReportingFormat.HTML));
			logger.warn(e.getMessage(), e);
		}
	}
}
