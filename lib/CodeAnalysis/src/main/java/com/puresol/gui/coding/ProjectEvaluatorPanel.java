package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.i18n4java.Translator;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.config.Configuration;
import com.puresol.gui.Application;
import com.puresol.gui.TabButton;
import com.puresol.gui.progress.FinishListener;
import com.puresol.gui.progress.ProgressObservable;
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

    private final Configuration configuration;
    private final ProjectEvaluatorChooser evaluators = new ProjectEvaluatorChooser();
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final JButton run = new JButton(translator.i18n("Run..."));

    public ProjectEvaluatorPanel(Configuration configuration) {
	super();
	this.configuration = configuration;
	initUI();
    }

    public ProjectEvaluatorPanel(ProjectAnalyzer projectAnalyser,
	    Configuration configuration) {
	super();
	this.configuration = configuration;
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
	Evaluator evaluator = evaluatorFactory.create(projectAnalyser,
		configuration);
	ProgressWindow progress = new ProgressWindow(Application.getInstance(),
		true);
	progress.addFinishListener(this);
	progress.runAsynchronous(evaluator);
    }

    @Override
    public void finished(ProgressObservable observable) {
	tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
		new TabButton(tabbedPane));
    }

    @Override
    public void terminated(ProgressObservable observable) {
	int result = JOptionPane
		.showConfirmDialog(
			Application.getInstance(),
			translator
				.i18n("Evaluator calcualtion was aborted. The results are now not completed and may be wrong.\n"
					+ "Do you want to have them displayed anyway?"),
			translator.i18n("Caluclation aborted"),
			JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	if (result == JOptionPane.YES_OPTION) {
	    finished(observable);
	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == run) {
	    run();
	}
    }
}
