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

import javax.i18n4java.Translator;
import javax.swingx.Label;
import javax.swingx.Panel;
import javax.swingx.TabbedPane;

import com.puresol.coding.analysis.ProjectAnalyzer;

public class ProjectAnalysisBrowser extends Panel {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(ProjectAnalysisBrowser.class);

	private ProjectAnalyzer project = null;

	private final Label directory = new Label();
	private final TabbedPane tabbedPane = new TabbedPane();
	private final EvaluatorPanel evaluatorPanel = new EvaluatorPanel();

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
		add(directory, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab(translator.i18n("Code Evaluators"), evaluatorPanel);
	}

	public void setProjectAnalyser(ProjectAnalyzer project) {
		this.project = project;
		if (project != null) {
			directory.setText(project.getProjectDirectory().getPath());
		} else {
			directory.setText("");
		}
		evaluatorPanel.setProjectAnalyser(project);
	}

	public ProjectAnalyzer getProjectAnalyser() {
		return project;
	}

	public void refresh() {
	}

}
