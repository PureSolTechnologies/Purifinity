package com.puresol.gui.coding.analysis;

import javax.swingx.BorderLayoutWidget;
import javax.swingx.TabbedPane;

import com.puresol.coding.ProjectAnalyser;
import com.puresol.coding.analysis.CoCoMo;
import com.puresol.coding.analysis.ProjectStatistics;
import com.puresol.gui.coding.CodeViewer;

public class ProjectSummaryViewer extends BorderLayoutWidget {

    /**
     * 
     */
    private static final long serialVersionUID = 6698435471544803926L;
    private ProjectAnalyser analyser = null;
    private TabbedPane tabbedPane = null;
    private CodeViewer cocomoViewer = null;

    public ProjectSummaryViewer() {
	initUI();
    }

    public ProjectSummaryViewer(ProjectAnalyser analyser) {
	setProjectAnalyser(analyser);
	initUI();
    }

    private void initUI() {
	setCenter(tabbedPane = new TabbedPane());
	tabbedPane.add("CoCoMo", cocomoViewer = new CodeViewer());
    }

    public void setProjectAnalyser(ProjectAnalyser analyser) {
	this.analyser = analyser;
	refresh();
    }

    public void refresh() {
	ProjectStatistics stat = new ProjectStatistics(analyser);
	CoCoMo cocomo = new CoCoMo(stat.getSLOC());
	cocomoViewer.setText(cocomo.toReport());

    }
}
