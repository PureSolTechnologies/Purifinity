package com.puresol.gui.coding;

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

import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import apps.CodeAnalysis;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.CodeRangeEvaluatorManager;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.EvaluatorFactory;
import com.puresol.gui.TabButton;
import com.puresol.osgi.OSGiFrameworkManager;

public class CodeRangeEvaluatorPanel extends Panel {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeEvaluatorPanel.class);

	private ProjectAnalyzer projectAnalyser = null;

	private final CodeRangeChooser codeRange = new CodeRangeChooser();
	private final FreeList evaluators = new FreeList();
	private final TabbedPane tabbedPane = new TabbedPane();
	private final TextArea description = new TextArea();
	private final Button run = new Button(translator.i18n("Run..."));

	public CodeRangeEvaluatorPanel() {
		super();
		initUI();
	}

	public CodeRangeEvaluatorPanel(ProjectAnalyzer projectAnalyser) {
		super();
		setProjectAnalyser(projectAnalyser);
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

		add(codeRange, BorderLayout.WEST);
		add(tools, BorderLayout.NORTH);
		add(splitPane, BorderLayout.CENTER);
		add(description, BorderLayout.SOUTH);

		OSGiFrameworkManager.getInstance(CodeAnalysis.class.getName())
				.getContext().addServiceListener(new ServiceListener() {
					@Override
					public void serviceChanged(ServiceEvent event) {
						addEvaluators();
					}
				});
		addEvaluators();
	}

	private void addEvaluators() {
		synchronized (evaluators) {
			evaluators.removeAll();
			List<CodeRangeEvaluatorFactory> evaluatorFactories = CodeRangeEvaluatorManager
					.getAll();
			Hashtable<Object, Object> values = new Hashtable<Object, Object>();
			for (EvaluatorFactory evaluatorFactory : evaluatorFactories) {
				values.put(evaluatorFactory.getName(), evaluatorFactory);
			}
			evaluators.setListData(values);
		}
	}

	public ProjectAnalyzer getProjectAnlayser() {
		return projectAnalyser;
	}

	public void setProjectAnalyser(ProjectAnalyzer projectAnalyser) {
		this.projectAnalyser = projectAnalyser;
		refresh();
	}

	private void refresh() {
		codeRange.setProjectAnalyser(projectAnalyser);
	}

	@Slot
	public void run() {
		CodeRangeEvaluatorFactory evaluatorFactory = (CodeRangeEvaluatorFactory) evaluators
				.getSelectedValue();
		if ((evaluatorFactory == null) || (projectAnalyser == null)) {
			return;
		}
		Analyzer analyzer = projectAnalyser.getAnalyzer(codeRange.getFile());
		Evaluator evaluator = evaluatorFactory.create(analyzer.getLanguage(),
				codeRange.getCodeRange());
		ProgressWindow progress = new ProgressWindow(evaluator);
		progress.run();
		progress.connect("finished", this, "finished", ProgressObservable.class);
	}

	@Slot
	public void finished(ProgressObservable observable) {
		Evaluator evaluator = (Evaluator) observable;
		ReportPane viewer = new ReportPane(evaluator);
		tabbedPane.add(evaluator.getName(), new ScrollPane(viewer,
				ScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
				new TabButton(tabbedPane));
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
