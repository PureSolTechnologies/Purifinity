package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.io.File;

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

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.gui.TabButton;

public class CodeRangeEvaluatorPanel extends Panel {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Translator translator = Translator
			.getTranslator(CodeRangeEvaluatorPanel.class);

	private ProjectAnalyzer projectAnalyser = null;

	private final EvaluatorChooser evaluators = new EvaluatorChooser();
	private final TabbedPane tabbedPane = new TabbedPane();
	private final Button run = new Button(translator.i18n("Run..."));
	private File file;
	private CodeRange codeRange;

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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public CodeRange getCodeRange() {
		return codeRange;
	}

	public void setCodeRange(CodeRange codeRange) {
		this.codeRange = codeRange;
	}

	@Slot
	public void run() {
		CodeRangeEvaluatorFactory evaluatorFactory = (CodeRangeEvaluatorFactory) evaluators
				.getSelectedValue();
		if ((evaluatorFactory == null) || (projectAnalyser == null)) {
			return;
		}
		if ((file == null) || (codeRange == null)) {
			return;
		}
		Analyzer analyzer = projectAnalyser.getAnalyzer(file);
		Evaluator evaluator = evaluatorFactory.create(analyzer.getLanguage(),
				codeRange);
		ProgressWindow progress = new ProgressWindow(evaluator);
		progress.connect("finished", this, "finished", ProgressObservable.class);
		progress.run();
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
