package com.puresol.gui.coding;

import javax.i18n4j.Translator;
import javax.swingx.HTMLTextPane;
import javax.swingx.TabbedPane;

import com.puresol.coding.ProjectAnalyser;

public class ProjectAnalysisBrowser extends TabbedPane {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(ProjectAnalysisBrowser.class);

	private ProjectAnalyser project = null;
	private CodeRangeAnalysisBrowser codeRangeBrowser = null;
	private HTMLTextPane projectOverview = null;

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
		addTab(translator.i18n("Overview"),
				projectOverview = new HTMLTextPane());
		addTab(translator.i18n("Modules"),
				codeRangeBrowser = new CodeRangeAnalysisBrowser());
	}

	public void setProjectAnalyser(ProjectAnalyser project) {
		this.project = project;
		codeRangeBrowser.setProjectAnalyser(project);
		projectOverview
				.setText("<html><body>Project Metrics Summary</body></html>");
	}

	public ProjectAnalyser getProjectAnalyser() {
		return project;
	}
}
