package com.puresol.gui.coding;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import com.puresol.coding.analysis.ProjectAnalyzer;

/**
 * This GUI element lists all available project analyzers and enables the user
 * to run them and to explore the results.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectGraphPanel extends JPanel {

	private static final long serialVersionUID = 7855693564694783199L;

	private ProjectAnalyzer projectAnalyser = null;

	private final ProjectEvaluatorChooser evaluators = new ProjectEvaluatorChooser();
	private final JTabbedPane tabbedPane = new JTabbedPane();

	public ProjectGraphPanel() {
		super();
		initUI();
	}

	public ProjectGraphPanel(ProjectAnalyzer projectAnalyser) {
		super();
		this.projectAnalyser = projectAnalyser;
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, new JScrollPane(evaluators), tabbedPane);

		add(splitPane, BorderLayout.CENTER);
	}

	public ProjectAnalyzer getProjectAnlayser() {
		return projectAnalyser;
	}

	public void setProjectAnalyser(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
	}
}
