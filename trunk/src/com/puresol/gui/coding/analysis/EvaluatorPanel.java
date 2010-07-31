package com.puresol.gui.coding.analysis;

import java.awt.BorderLayout;
import java.util.Hashtable;
import java.util.List;

import javax.i18n4java.Translator;
import javax.swing.JSplitPane;
import javax.swingx.Button;
import javax.swingx.FreeList;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.TabbedPane;
import javax.swingx.TextArea;
import javax.swingx.ToolBar;
import javax.swingx.connect.Slot;
import javax.swingx.progress.ProgressObservable;
import javax.swingx.progress.ProgressWindow;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.EvaluatorFactory;
import com.puresol.coding.evaluator.Evaluators;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;

public class EvaluatorPanel extends Panel {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Translator translator = Translator
			.getTranslator(EvaluatorPanel.class);

	private ProjectAnalyzer projectAnalyser = null;

	private final FreeList evaluators = new FreeList();
	private final TabbedPane tabbedPane = new TabbedPane();
	private final TextArea description = new TextArea();
	private final Button run = new Button(translator.i18n("Run..."));

	public EvaluatorPanel() {
		super();
		initUI();
	}

	public EvaluatorPanel(ProjectAnalyzer projectAnalyser) {
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

		evaluators.connect("valueChanged", this, "evaluatorChanged",
				Object.class);
		run.connect("start", this, "run");

		add(tools, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);
		add(description, BorderLayout.SOUTH);

		addEvaluators();
	}

	private void addEvaluators() {
		List<ProjectEvaluatorFactory> evaluatorFactories = Evaluators
				.getInstance().getProjectEvaluators();
		Hashtable<Object, Object> values = new Hashtable<Object, Object>();
		for (EvaluatorFactory evaluatorFactory : evaluatorFactories) {
			values.put(evaluatorFactory.getName(), evaluatorFactory);
		}
		evaluators.setListData(values);
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
		EvaluatorViewer viewer = new EvaluatorViewer(evaluator);
		tabbedPane.add(evaluator.getName(), viewer);
	}

	@Slot
	public void evaluatorChanged(Object o) {
		EvaluatorFactory evaluatorFactory = (EvaluatorFactory) o;
		if (evaluatorFactory == null) {
			description.setText("");
		} else {
			description.setText(evaluatorFactory.getDescription());
		}
	}
}
