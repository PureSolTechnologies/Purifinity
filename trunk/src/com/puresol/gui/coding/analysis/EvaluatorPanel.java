package com.puresol.gui.coding.analysis;

import java.awt.BorderLayout;
import java.util.Hashtable;
import java.util.List;

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

import org.apache.log4j.Logger;

import com.puresol.coding.EvaluatorFactory;
import com.puresol.coding.Evaluators;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.NotSupportedException;

public class EvaluatorPanel extends Panel {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Logger logger = Logger.getLogger(EvaluatorPanel.class);
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
		List<EvaluatorFactory> evaluatorFactories = Evaluators.getInstance()
				.getEvaluators();
		Hashtable<Object, Object> values = new Hashtable<Object, Object>();
		for (EvaluatorFactory evaluatorFactory : evaluatorFactories) {
			values.put(evaluatorFactory.getEvaluatorName(), evaluatorFactory);
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
		EvaluatorFactory evaluatorFactory = (EvaluatorFactory) evaluators
				.getSelectedValue();
		if ((evaluatorFactory == null) || (projectAnalyser == null)) {
			return;
		}
		try {
			Evaluator evaluator = evaluatorFactory.create(projectAnalyser);
			ProgressWindow progress = new ProgressWindow(evaluator);
			progress.run();
			progress.connect("finished", this, "finished",
					ProgressObservable.class);
		} catch (NotSupportedException e) {
			logger.warn(e.getMessage(), e);
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
		EvaluatorFactory evaluatorFactory = (EvaluatorFactory) o;
		if (evaluatorFactory == null) {
			description.setText("");
		} else {
			description.setText(evaluatorFactory.getEvaluatorDescription());
		}
	}
}
