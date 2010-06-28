package com.puresol.gui.coding.analysis;

import java.util.Hashtable;

import javax.i18n4j.Translator;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.Button;
import javax.swingx.FreeList;
import javax.swingx.ToolBar;
import javax.swingx.connect.Slot;
import javax.swingx.progress.ProgressWindow;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.Evaluator;

public class ProjectEvaluatorPanel extends BorderLayoutWidget {

	private static final long serialVersionUID = -268447243059164084L;

	private static final Translator translator = Translator
			.getTranslator(DuplicationScannerPanel.class);

	private final Button search = new Button(translator.i18n("Evaluate..."));
	private final FreeList evaluatorList = new FreeList();
	private final EvaluatorViewer evaluatorViewer = new EvaluatorViewer();

	private ProjectAnalyzer projectAnalyser = null;
	private ProjectEvaluator projectEvaluator = null;

	public ProjectEvaluatorPanel() {
		super();
		initUI();
	}

	public ProjectEvaluatorPanel(ProjectAnalyzer projectAnalyser) {
		super();
		initUI();
		setProjectAnalyser(projectAnalyser);
	}

	private void initUI() {
		ToolBar tools = new ToolBar();
		tools.add(search);
		search.connect("start", this, "evaluate");
		setNorth(tools);
		setWest(evaluatorList);
		evaluatorList.connect("valueChanged", this, "changedEvaluator",
				Object.class);
		setCenter(evaluatorViewer);
	}

	public void setProjectAnalyser(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
	}

	@Slot
	public void evaluate() {
		projectEvaluator = new ProjectEvaluator(projectAnalyser);
		ProgressWindow progress = new ProgressWindow(projectEvaluator);
		progress.connect("finished", this, "refresh");
		progress.run();
	}

	@Slot
	public void refresh() {
		Hashtable<Object, Object> listData = new Hashtable<Object, Object>();
		for (Evaluator evaluator : projectEvaluator.getEvaluators()) {
			listData.put(evaluator.getName(), evaluator);
		}
		evaluatorList.setListData(listData);
	}

	@Slot
	public void changedEvaluator(Object value) {
		Evaluator evaluator = (Evaluator) value;
		evaluatorViewer.setEvaluator(evaluator);
	}

	public ProjectEvaluator getProjectEvaluator() {
		return projectEvaluator;
	}

	public void setProjectEvaluator(ProjectEvaluator projectEvaluator) {
		this.projectEvaluator = projectEvaluator;
		refresh();
	}
}
