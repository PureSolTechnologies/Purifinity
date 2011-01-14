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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.document.convert.html.HTMLConverter;
import com.puresol.gui.HTMLTextPane;

public class ProjectAnalysisBrowser extends JPanel {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(ProjectAnalysisBrowser.class);

	private ProjectAnalyzer project = null;

	private final JTabbedPane tabbedPane = new JTabbedPane();
	private HTMLTextPane projectReport = new HTMLTextPane();
	private final ProjectEvaluatorPanel projectEvaluatorPanel = new ProjectEvaluatorPanel();
	private final CodeRangeBrowser codeRangeBrowser = new CodeRangeBrowser();

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

		tabbedPane.addTab(translator.i18n("Report"), projectReport);
		tabbedPane.addTab(translator.i18n("Project Evaluators"),
				projectEvaluatorPanel);
		tabbedPane.addTab(translator.i18n("Code Ranges"), codeRangeBrowser);

		add(tabbedPane, BorderLayout.CENTER);
	}

	public void setProjectAnalyser(ProjectAnalyzer project) {
		this.project = project;
		projectReport
				.setText(new HTMLConverter(project.getReport()).toString());
		tabbedPane.setSelectedIndex(0);
		codeRangeBrowser.setProjectAnalyser(project);
		projectEvaluatorPanel.setProjectAnalyser(project);
	}

	public ProjectAnalyzer getProjectAnalyser() {
		return project;
	}

}
