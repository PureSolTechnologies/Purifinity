package com.puresol.gui.coding.analysis;

import javax.i18n4java.Translator;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.Button;
import javax.swingx.ToolBar;
import javax.swingx.connect.Slot;
import javax.swingx.progress.ProgressWindow;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.duplication.DuplicationScanner;

public class DuplicationScannerPanel extends BorderLayoutWidget {

	private static final long serialVersionUID = -268447243059164084L;

	private static final Translator translator = Translator
			.getTranslator(DuplicationScannerPanel.class);

	private ProjectAnalyzer project = null;
	private Button search = null;
	private DuplicationBrowser duplicationBrowser = null;
	private DuplicationScanner scanner = null;

	public DuplicationScannerPanel() {
		super();
		initUI();
	}

	public DuplicationScannerPanel(ProjectAnalyzer project) {
		super();
		initUI();
		setProjectAnalyser(project);
	}

	private void initUI() {
		ToolBar tools = new ToolBar();
		tools.add(search = new Button(translator.i18n("Search...")));
		search.connect("start", this, "search");
		setNorth(tools);
		setCenter(duplicationBrowser = new DuplicationBrowser());
	}

	public void setProjectAnalyser(ProjectAnalyzer project) {
		this.project = project;
	}

	@Slot
	public void search() {
		scanner = new DuplicationScanner(project);
		ProgressWindow progress = new ProgressWindow(scanner);
		progress.connect("finished", this, "refresh");
		progress.run();
	}

	@Slot
	public void refresh() {
		duplicationBrowser.setDuplications(scanner.getDuplications());
	}
}
