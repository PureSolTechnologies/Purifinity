/***************************************************************************
 *
 *   ProjectAnalysisBrowser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui.coding;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;
import javax.swingx.Panel;
import javax.swingx.TabbedPane;
import javax.swingx.TextArea;

import com.puresol.coding.analysis.ProjectAnalyzer;

public class ProjectAnalysisBrowser extends Panel {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(ProjectAnalysisBrowser.class);

	private ProjectAnalyzer project = null;

	private final TabbedPane tabbedPane = new TabbedPane();
	private final CodeRangeBrowser codeRangeBrowser = new CodeRangeBrowser();
	private final ProjectEvaluatorPanel projectEvaluatorPanel = new ProjectEvaluatorPanel();
	private Panel analysisReport;

	public ProjectAnalysisBrowser() {
		super();
		initUI();
	}

	public ProjectAnalysisBrowser(ProjectAnalyzer project) {
		super();
		initUI();
		setProjectAnalyser(project);
	}

	private void initUI() {
		setLayout(new BorderLayout());

		analysisReport = new Panel();
		analysisReport.add(new TextArea("No Analysis available yet."));
		tabbedPane.addTab(translator.i18n("Analysis Report"), analysisReport);
		tabbedPane.addTab(translator.i18n("Project Evaluators"),
				projectEvaluatorPanel);
		tabbedPane.addTab(translator.i18n("Code Ranges"), codeRangeBrowser);

		add(tabbedPane, BorderLayout.CENTER);
	}

	public void setProjectAnalyser(ProjectAnalyzer project) {
		this.project = project;
		tabbedPane.removeTabAt(0);
		tabbedPane.insertTab(translator.i18n("Analysis Report"), null,
				project.getInformationPanel(), null, 0);
		tabbedPane.setSelectedIndex(0);
		codeRangeBrowser.setProjectAnalyser(project);
		projectEvaluatorPanel.setProjectAnalyser(project);
	}

	public ProjectAnalyzer getProjectAnalyser() {
		return project;
	}

}
