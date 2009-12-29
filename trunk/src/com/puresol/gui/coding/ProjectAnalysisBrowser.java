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

import javax.i18n4j.Translator;
import javax.swingx.HTMLTextPane;
import javax.swingx.Label;
import javax.swingx.Panel;
import javax.swingx.TabbedPane;

import com.puresol.coding.ProjectAnalyser;

public class ProjectAnalysisBrowser extends Panel {

	private static final long serialVersionUID = 3469716304984536673L;

	private static final Translator translator = Translator
			.getTranslator(ProjectAnalysisBrowser.class);

	private ProjectAnalyser project = null;
	private Label directory = null;
	private TabbedPane tabbedPane = null;
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
		setLayout(new BorderLayout());
		add(directory = new Label(), BorderLayout.NORTH);
		add(tabbedPane = new TabbedPane(), BorderLayout.CENTER);
		tabbedPane.addTab(translator.i18n("Overview"),
				projectOverview = new HTMLTextPane());
		tabbedPane.addTab(translator.i18n("Modules"),
				codeRangeBrowser = new CodeRangeAnalysisBrowser());
	}

	public void setProjectAnalyser(ProjectAnalyser project) {
		this.project = project;
		directory.setText(project.getProjectDirectory().getPath());
		codeRangeBrowser.setProjectAnalyser(project);
		projectOverview
				.setText("<html><body>Project Metrics Summary</body></html>");
	}

	public ProjectAnalyser getProjectAnalyser() {
		return project;
	}
	
	public void refresh() {
		codeRangeBrowser.refresh();
	}
}
