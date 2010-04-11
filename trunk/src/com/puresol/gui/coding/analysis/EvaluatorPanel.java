package com.puresol.gui.coding.analysis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.i18n4j.Translator;
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

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.EvaluatorManager;
import com.puresol.utils.ClassInstantiationException;

public class EvaluatorPanel extends Panel {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Translator translator = Translator
			.getTranslator(EvaluatorPanel.class);

	private ProjectAnalyser projectAnalyser = null;

	private final FreeList evaluators = new FreeList();
	private final TabbedPane tabbedPane = new TabbedPane();
	private final TextArea description = new TextArea();
	private final Button run = new Button(translator.i18n("Run..."));

	public EvaluatorPanel() {
		super();
		initUI();
	}

	public EvaluatorPanel(ProjectAnalyser projectAnalyser) {
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
		EvaluatorManager manager = EvaluatorManager.getInstance();
		ArrayList<Class<? extends Evaluator>> evaluatorClasses = manager
				.getEvaluatorClasses();
		Hashtable<Object, Object> values = new Hashtable<Object, Object>();
		for (Class<? extends Evaluator> evaluatorClass : evaluatorClasses) {
			values.put(manager.getName(evaluatorClass), evaluatorClass);
		}
		evaluators.setListData(values);
	}

	public ProjectAnalyser getProjectAnlayser() {
		return projectAnalyser;
	}

	public void setProjectAnalyser(ProjectAnalyser projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
	}

	@Slot
	public void run() {
		@SuppressWarnings("unchecked")
		Class<? extends Evaluator> clazz = (Class<? extends Evaluator>) evaluators
				.getSelectedValue();
		if ((clazz == null) || (projectAnalyser == null)) {
			return;
		}
		try {
			Evaluator evaluator = EvaluatorManager.createEvaluatorInstance(
					clazz, projectAnalyser);
			ProgressWindow progress = new ProgressWindow(evaluator);
			progress.run();
			progress.connect("finished", this, "finished",
					ProgressObservable.class);
		} catch (ClassInstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Slot
	public void finished(ProgressObservable observable) {
		Evaluator evaluator = (Evaluator) observable;
		EvaluatorViewer viewer = new EvaluatorViewer(evaluator);
		tabbedPane.add(evaluator.getName(), viewer);
	}

	@Slot
	public void evaluatorChanged(Object o) {
		@SuppressWarnings("unchecked")
		Class<? extends Evaluator> clazz = (Class<? extends Evaluator>) o;
		if (clazz == null) {
			description.setText("");
		} else {
			description.setText(EvaluatorManager.getInstance().getDescription(
					clazz));
		}
	}
}
