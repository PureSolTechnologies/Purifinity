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

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.puresol.coding.analysis.ProjectAnalyzer;

public class ProjectAnalysisBrowser extends JPanel {

    private static final long serialVersionUID = 3469716304984536673L;

    private ProjectAnalyzer project = null;

    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final JPanel projectReport = new JPanel();
    private final ProjectEvaluatorPanel projectEvaluatorPanel;
    private final ProjectGraphPanel projectGraphPanel;
    private final CodeRangeBrowser codeRangeBrowser;

    public ProjectAnalysisBrowser() {
	super();
	projectEvaluatorPanel = new ProjectEvaluatorPanel();
	projectGraphPanel = new ProjectGraphPanel();
	codeRangeBrowser = new CodeRangeBrowser();
	initUI();
    }

    public ProjectAnalysisBrowser(ProjectAnalyzer project) {
	this();
	setProjectAnalyser(project);
    }

    private void initUI() {
	setLayout(new BorderLayout());

	projectReport.setLayout(new BorderLayout());

	tabbedPane.addTab("Report", projectReport);
	tabbedPane.addTab("Project Evaluators", projectEvaluatorPanel);
	tabbedPane.addTab("Project Graphs", projectGraphPanel);
	tabbedPane.addTab("Code Ranges", codeRangeBrowser);

	add(tabbedPane, BorderLayout.CENTER);
    }

    public void setProjectAnalyser(ProjectAnalyzer project) {
	this.project = project;
	projectReport.removeAll();
	tabbedPane.setSelectedIndex(0);
	codeRangeBrowser.setProjectAnalyser(project);
	projectEvaluatorPanel.setProjectAnalyser(project);
	projectGraphPanel.setProjectAnalyzer(project);
    }

    public ProjectAnalyzer getProjectAnalyser() {
	return project;
    }

}
