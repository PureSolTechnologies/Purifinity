package com.puresol.gui.coding;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;
import javax.swing.JSplitPane;
import javax.swingx.Button;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.TabbedPane;
import javax.swingx.ToolBar;
import javax.swingx.connect.Slot;
import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressWindow;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.gui.TabButton;

/**
 * This GUI element lists all available project analyzers and enables the user
 * to run them and to explore the results.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectEvaluatorPanel extends Panel {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Translator translator = Translator
			.getTranslator(ProjectEvaluatorPanel.class);

	private ProjectAnalyzer projectAnalyser = null;

	private final EvaluatorChooser evaluators = new EvaluatorChooser();
	private final TabbedPane tabbedPane = new TabbedPane();
	private final Button run = new Button(translator.i18n("Run..."));

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

		ToolBar tools = new ToolBar();
		tools.add(run);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, new ScrollPane(evaluators), tabbedPane);

		run.connect("start", this, "run");

		add(tools, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);
	}

	public ProjectAnalyzer getProjectAnlayser() {
		return projectAnalyser;
	}

	public void setProjectAnalyser(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
	}

	@Slot
	public void run() {
		ProjectEvaluatorFactory evaluatorFactory = (ProjectEvaluatorFactory) evaluators
				.getSelectedValue();
		if ((evaluatorFactory == null) || (projectAnalyser == null)) {
			return;
		}
		Evaluator evaluator = evaluatorFactory.create(projectAnalyser);
		ProgressWindow progress = new ProgressWindow(evaluator);
		progress.run();
		progress.connect("finished", this, "finished", ProgressObservable.class);
	}

	@Slot
	public void finished(ProgressObservable observable) {
		Evaluator evaluator = (Evaluator) observable;
		ReportPanel viewer = new ReportPanel(evaluator);
		tabbedPane.add(evaluator.getName(), new ScrollPane(viewer,
				ScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
				new TabButton(tabbedPane));
	}
}
