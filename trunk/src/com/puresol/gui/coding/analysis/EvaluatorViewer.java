package com.puresol.gui.coding.analysis;

import javax.i18n4j.Translator;
import javax.swing.BoxLayout;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.HTMLTextPane;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.TabbedPane;
import javax.swingx.TextField;

import org.apache.log4j.Logger;

import com.puresol.coding.evaluator.Evaluator;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.UnsupportedReportingFormatException;

public class EvaluatorViewer extends BorderLayoutWidget {

	private static final long serialVersionUID = 7729851519489273274L;

	private static final Logger logger = Logger
			.getLogger(EvaluatorViewer.class);
	private static final Translator translator = Translator
			.getTranslator(EvaluatorViewer.class);

	private final TextField evaluatorName = new TextField();
	private final HTMLTextPane description = new HTMLTextPane();
	private final HTMLTextPane report = new HTMLTextPane();

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
		tabbedPane.add(new ScrollPane(description),
				translator.i18n("Evaluator Description"));
		tabbedPane.add(new ScrollPane(report),
				translator.i18n("Project Summary"));
		Panel codeRangePanel = new Panel();
		codeRangePanel
				.setLayout(new BoxLayout(codeRangePanel, BoxLayout.X_AXIS));
		tabbedPane.add(codeRangePanel,
				translator.i18n("Evaluators Code Range Summary"));
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
		if (evaluator == null) {
			clearUI();
			return;
		}
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
			report.setText(evaluator.getReport(ReportingFormat.HTML));
		} catch (UnsupportedReportingFormatException e) {
			report.setText(translator.i18n(
					"Evaluator does not (yet) support {0} reporting format.",
					ReportingFormat.HTML));
			logger.warn(e.getMessage(), e);
		}
	}

	private void clearUI() {
		evaluatorName.setText("");
		description.setText("");
		report.setText("");

	}

}
