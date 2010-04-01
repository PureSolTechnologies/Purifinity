package com.puresol.gui.coding.analysis;

import javax.i18n4j.Translator;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.TabbedPane;
import javax.swingx.TextField;

import com.puresol.coding.analysis.evaluator.Evaluator;
import com.puresol.gui.coding.CodeViewer;

public class EvaluatorViewer extends BorderLayoutWidget {

	private static final long serialVersionUID = 7729851519489273274L;

	private static final Translator translator = Translator
			.getTranslator(EvaluatorViewer.class);

	private Evaluator evaluator = null;
	private TextField evaluatorName;
	private CodeViewer description;
	private CodeViewer projectSummary;

	public EvaluatorViewer() {
		super();
		initUI();
	}

	private void initUI() {
		setNorth(evaluatorName = new TextField());
		TabbedPane tabbedPane = new TabbedPane();
		setCenter(tabbedPane);
		tabbedPane.add(description = new CodeViewer(), translator
				.i18n("Evaluator Description"));
		tabbedPane.add(projectSummary = new CodeViewer(), translator
				.i18n("Project Summary"));
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
		description.setText(evaluator.getDescription());
		projectSummary.setText(evaluator.getProjectEvaluationComment());
	}
}
