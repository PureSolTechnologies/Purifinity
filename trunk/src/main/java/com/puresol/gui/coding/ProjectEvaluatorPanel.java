package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.i18n4java.Translator;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.gui.Application;
import com.puresol.gui.TabButton;
import com.puresol.gui.progress.FinishListener;
import com.puresol.gui.progress.ProgressWindow;

/**
 * This GUI element lists all available project analyzers and enables the user
 * to run them and to explore the results.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectEvaluatorPanel extends JPanel implements ActionListener,
		FinishListener {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Translator translator = Translator
			.getTranslator(ProjectEvaluatorPanel.class);

	private ProjectAnalyzer projectAnalyser = null;

	private final ProjectEvaluatorChooser evaluators = new ProjectEvaluatorChooser();
	private final JTabbedPane tabbedPane = new JTabbedPane();
	private final JButton run = new JButton(translator.i18n("Run..."));

	public ProjectEvaluatorPanel() {
		super();
		initUI();
	}

	public ProjectEvaluatorPanel(ProjectAnalyzer projectAnalyser) {
		super();
		this.projectAnalyser = projectAnalyser;
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout());

		JToolBar tools = new JToolBar();
		tools.add(run);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, new JScrollPane(evaluators), tabbedPane);

		run.addActionListener(this);

		add(tools, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);
	}

	public ProjectAnalyzer getProjectAnlayser() {
		return projectAnalyser;
	}

	public void setProjectAnalyser(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
	}

	private void run() {
		ProjectEvaluatorFactory evaluatorFactory = (ProjectEvaluatorFactory) evaluators
				.getSelectedValue();
		if ((evaluatorFactory == null) || (projectAnalyser == null)) {
			return;
		}
		Evaluator evaluator = evaluatorFactory.create(projectAnalyser);
		ProgressWindow progress = new ProgressWindow(Application.getInstance());
		progress.addFinishListener(this);
		progress.run(evaluator);
	}

	@Override
	public void finished(Object observable) {
		Evaluator evaluator = (Evaluator) observable;
		ReportPanel viewer = new ReportPanel(evaluator);
		tabbedPane.add(evaluator.getName(), new JScrollPane(viewer,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
				new TabButton(tabbedPane));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == run) {
			run();
		}
	}
}
