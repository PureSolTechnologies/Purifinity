/***************************************************************************
 *
 *   CodeRangeAnalysisBrowser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.gui.coding.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import javax.i18n4j.Translator;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swingx.BorderLayoutWidget;
import javax.swingx.Button;
import javax.swingx.FreeList;
import javax.swingx.HTMLTextPane;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.ToolBar;
import javax.swingx.connect.Slot;
import javax.swingx.progress.ProgressWindow;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProjectAnalyser;
import com.puresol.coding.analysis.MetricsCalculator;
import com.puresol.coding.analysis.QualityLevel;
import com.puresol.coding.analysis.reports.HTMLAnalysisReport;

public class MetricsBrowser extends BorderLayoutWidget {

    private static final long serialVersionUID = 3469716304984536673L;

    private static final Translator translator =
	    Translator.getTranslator(MetricsBrowser.class);

    private ProjectAnalyser project = null;
    private Button search = null;
    private MetricsCalculator metrics = null;
    private FreeList fileList = null;
    private FreeList codeRangeList = null;
    private HTMLTextPane results = null;
    private Hashtable<CodeRange, HTMLAnalysisReport> reports =
	    new Hashtable<CodeRange, HTMLAnalysisReport>();

    public MetricsBrowser() {
	super();
	initUI();
    }

    public MetricsBrowser(ProjectAnalyser project) {
	super();
	initUI();
	setProjectAnalyser(project);
    }

    private void initUI() {
	ToolBar tools = new ToolBar();
	tools.add(search = new Button(translator.i18n("Calculate...")));
	search.connect("start", this, "search");
	setNorth(tools);

	Panel listsPanel = new Panel();
	listsPanel.setLayout(new BoxLayout(listsPanel, BoxLayout.Y_AXIS));

	fileList = new FreeList();
	fileList.connect("indexChanged", this, "showFile", int.class);
	ScrollPane fileScroller = new ScrollPane(fileList);
	fileScroller.setBorder(new TitledBorder(translator
		.i18n("Source Files")));
	listsPanel.add(fileScroller);

	codeRangeList = new FreeList();
	codeRangeList.connect("indexChanged", this, "showCodeRange",
		int.class);
	ScrollPane codeRangeScroller = new ScrollPane(codeRangeList);
	codeRangeScroller.setBorder(new TitledBorder(translator
		.i18n("Modules")));
	listsPanel.add(codeRangeScroller);

	setWest(listsPanel);
	results = new HTMLTextPane();
	setCenter(new ScrollPane(results));
    }

    public void setProjectAnalyser(ProjectAnalyser project) {
	this.project = project;
    }

    @Slot
    public void search() {
	metrics = new MetricsCalculator(project);
	ProgressWindow progress = new ProgressWindow(metrics);
	progress.connect("finished", this, "refresh");
	progress.run();
    }

    @Slot
    public void refresh() {
	codeRangeList.removeAll();
	fileList.removeAll();
	if (project == null) {
	    return;
	}
	ArrayList<File> files = project.getFiles();
	if (files == null) {
	    return;
	}
	if (metrics == null) {
	    return;
	}
	ArrayList<String> htmls = new ArrayList<String>();
	Collections.sort(files);
	for (File file : files) {
	    ArrayList<CodeRange> ranges = project.getCodeRanges(file);
	    for (CodeRange range : ranges) {
		calculateReport(range);
	    }
	    QualityLevel quality = metrics.getQualityLevel(file);
	    String html = "<html><body>";
	    if (quality == QualityLevel.HIGH) {
		html += "<table width=\"100%\" bgcolor=\"#00ff00\">";
	    } else if (quality == QualityLevel.MEDIUM) {
		html += "<table width=\"100%\" bgcolor=\"#ffff00\">";
	    } else {
		html += "<table width=\"100%\" bgcolor=\"#ff0000\">";
	    }
	    html +=
		    "<tr><td>" + file.getPath()
			    + "</td></tr></table></body></html>";
	    htmls.add(html);

	}
	fileList.setListData(new ArrayList<Object>(htmls),
		new ArrayList<Object>(files));
    }

    private void updateCodeRanges(File file) {
	codeRangeList.removeAll();
	if (project == null) {
	    return;
	}
	ArrayList<CodeRange> ranges = project.getCodeRanges(file);
	if (ranges == null) {
	    return;
	}
	int index = 0;
	Hashtable<Object, Object> listData =
		new Hashtable<Object, Object>();
	for (CodeRange range : ranges) {
	    index++;
	    String html = "<html><body>";
	    QualityLevel quality =
		    metrics.getMetrics(range).getQualityLevel();
	    if (quality == QualityLevel.HIGH) {
		html += "<table width=\"100%\" bgcolor=\"#00ff00\">";
	    } else if (quality == QualityLevel.MEDIUM) {
		html += "<table width=\"100%\" bgcolor=\"#ffff00\">";
	    } else {
		html += "<table width=\"100%\" bgcolor=\"#ff0000\">";
	    }
	    html +=
		    "<tr><td>" + index + ": "
			    + range.getType().getIdentifier() + ":"
			    + range.getName()
			    + "</td></tr></table></body></html>";
	    listData.put(html, range);
	}
	codeRangeList.setListData(listData);
    }

    private void calculateReport(CodeRange codeRange) {
	reports.put(codeRange, new HTMLAnalysisReport(metrics
		.getMetrics(codeRange)));
    }

    @Slot
    public void showFile(int index) {
	results.setText("");
	File file = (File) fileList.getSelectedValue();
	updateCodeRanges(file);
    }

    @Slot
    public void showCodeRange(int index) {
	CodeRange codeRange = (CodeRange) codeRangeList.getSelectedValue();
	if (codeRange == null) {
	    return;
	}
	results.setText(reports.get(codeRange).getReport());
    }
}
