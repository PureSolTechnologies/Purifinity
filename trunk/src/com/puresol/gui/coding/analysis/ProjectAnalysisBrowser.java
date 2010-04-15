/***************************************************************************
 *
 *   ProjectAnalysisBrowser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui.coding.analysis;

import java.awt.BorderLayout;

import javax.i18n4j.Translator;
import javax.swingx.Label;
import javax.swingx.Panel;
import javax.swingx.TabbedPane;

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.ProjectEvaluator;

public class ProjectAnalysisBrowser extends Panel {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(ProjectAnalysisBrowser.class);

	private ProjectAnalyser project = null;

	private final Label directory = new Label();
	private final TabbedPane tabbedPane = new TabbedPane();
	private final CodeRangeBrowser codeRangeBrowser = new CodeRangeBrowser();
	private final EvaluatorPanel evaluatorPanel = new EvaluatorPanel();
	private final MetricsBrowser metricsBrowser = new MetricsBrowser();
	private final ProjectEvaluatorPanel codeEvaluation = new ProjectEvaluatorPanel();

	public ProjectAnalysisBrowser() {
		super();
		initUI();
	}

	public ProjectAnalysisBrowser(ProjectAnalyser project) {
		super();
		initUI();
		setProjectAnalyser(project);
	}

	private void initUI() {
		setLayout(new BorderLayout());
		add(directory, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab(translator.i18n("Code Ranges"), codeRangeBrowser);
		tabbedPane.addTab(translator.i18n("Code Evaluators"), evaluatorPanel);
		tabbedPane.addTab(translator.i18n("Metrics Ranges"), metricsBrowser);
		tabbedPane
				.addTab(translator.i18n("Project Evaluation"), codeEvaluation);
	}

	public void setProjectAnalyser(ProjectAnalyser project) {
		this.project = project;
		if (project != null) {
			directory.setText(project.getProjectDirectory().getPath());
		} else {
			directory.setText("");
		}
		codeRangeBrowser.setProjectAnalyser(project);
		evaluatorPanel.setProjectAnalyser(project);
		metricsBrowser.setProjectAnalyser(project);
		codeEvaluation.setProjectAnalyser(project);
	}

	public ProjectAnalyser getProjectAnalyser() {
		return project;
	}

	public void refresh() {
		codeRangeBrowser.refresh();
	}

	public ProjectEvaluator getCodeEvaluator() {
		return codeEvaluation.getProjectEvaluator();
	}

	public ProjectEvaluator getProjectEvaluator() {
		return codeEvaluation.getProjectEvaluator();
	}

	public void setProjectEvaluator(ProjectEvaluator projectEvaluator) {
		codeEvaluation.setProjectEvaluator(projectEvaluator);
	}
}
