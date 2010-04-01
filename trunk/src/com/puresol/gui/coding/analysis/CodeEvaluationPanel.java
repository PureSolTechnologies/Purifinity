package com.puresol.gui.coding.analysis;

import javax.i18n4j.Translator;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.Button;
import javax.swingx.ToolBar;
import javax.swingx.connect.Slot;
import javax.swingx.progress.ProgressWindow;

import com.puresol.coding.analysis.CodeEvaluationSystem;
import com.puresol.coding.analysis.ProjectAnalyser;

public class CodeEvaluationPanel extends BorderLayoutWidget {

    private static final long serialVersionUID = -268447243059164084L;

    private static final Translator translator =
	    Translator.getTranslator(DuplicationScannerPanel.class);

    private ProjectAnalyser project = null;
    private Button search = null;
    private CodeEvaluationSystem codeEvaluationSystem = null;

    public CodeEvaluationPanel() {
	super();
	initUI();
    }

    public CodeEvaluationPanel(ProjectAnalyser project) {
	super();
	initUI();
	setProjectAnalyser(project);
    }

    private void initUI() {
	ToolBar tools = new ToolBar();
	tools.add(search = new Button(translator.i18n("Search...")));
	search.connect("start", this, "search");
	setNorth(tools);
    }

    public void setProjectAnalyser(ProjectAnalyser project) {
	this.project = project;
    }

    @Slot
    public void search() {
	codeEvaluationSystem = new CodeEvaluationSystem(project);
	ProgressWindow progress = new ProgressWindow(codeEvaluationSystem);
	progress.connect("finished", this, "refresh");
	progress.run();
    }

    @Slot
    public void refresh() {
	
    }
}
