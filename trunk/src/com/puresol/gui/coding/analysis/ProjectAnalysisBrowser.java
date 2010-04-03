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
import com.puresol.coding.evaluator.CodeEvaluator;

public class ProjectAnalysisBrowser extends Panel {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(ProjectAnalysisBrowser.class);

	private ProjectAnalyser project = null;
	private Label directory = null;
	private TabbedPane tabbedPane = null;
	private CodeRangeBrowser codeRangeBrowser = null;
	private MetricsBrowser metricsBrowser = null;
	private CopyAndPasteScannerPanel copyAndPasteScanner = null;
	private DuplicationScannerPanel duplicationScanner = null;
	private CodeEvaluationPanel codeEvaluation = null;

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
		add(directory = new Label(), BorderLayout.NORTH);
		add(tabbedPane = new TabbedPane(), BorderLayout.CENTER);
		tabbedPane.addTab(translator.i18n("Code Ranges"),
				codeRangeBrowser = new CodeRangeBrowser());
		tabbedPane.addTab(translator.i18n("Metrics Ranges"),
				metricsBrowser = new MetricsBrowser());
		tabbedPane.addTab(translator.i18n("Copy&Paste Scanner"),
				copyAndPasteScanner = new CopyAndPasteScannerPanel());
		tabbedPane.addTab(translator.i18n("Duplication Scanner"),
				duplicationScanner = new DuplicationScannerPanel());
		tabbedPane.addTab(translator.i18n("Source Code Evaluation"),
				codeEvaluation = new CodeEvaluationPanel());
	}

	public void setProjectAnalyser(ProjectAnalyser project) {
		this.project = project;
		if (project != null) {
			directory.setText(project.getProjectDirectory().getPath());
		} else {
			directory.setText("");
		}
		codeRangeBrowser.setProjectAnalyser(project);
		metricsBrowser.setProjectAnalyser(project);
		copyAndPasteScanner.setProjectAnalyser(project);
		duplicationScanner.setProjectAnalyser(project);
		codeEvaluation.setProjectAnalyser(project);
	}

	public ProjectAnalyser getProjectAnalyser() {
		return project;
	}

	public void refresh() {
		codeRangeBrowser.refresh();
	}
	
	public CodeEvaluator getCodeEvaluator() {
		return codeEvaluation.getCodeEvaluator();
	}
}
