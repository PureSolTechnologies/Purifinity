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

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

import javax.i18n4j.Translator;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swingx.FreeList;
import javax.swingx.List;
import javax.swingx.Panel;
import javax.swingx.ScrollPane;
import javax.swingx.connect.Slot;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.gui.coding.CodeRangeViewer;

public class CodeRangeBrowser extends Panel {

    private static final long serialVersionUID = 3469716304984536673L;

    private static final Translator translator =
	    Translator.getTranslator(CodeRangeBrowser.class);

    private ProjectAnalyser project = null;
    private List fileList = null;
    private FreeList codeRangeList = null;
    private CodeRangeViewer codeRangeViewer = null;

    public CodeRangeBrowser() {
	super();
	initUI();
    }

    public CodeRangeBrowser(ProjectAnalyser project) {
	super();
	initUI();
	setProjectAnalyser(project);
    }

    private void initUI() {
	setLayout(new BorderLayout());

	Panel listsPanel = new Panel();
	listsPanel.setLayout(new BoxLayout(listsPanel, BoxLayout.Y_AXIS));

	fileList = new List();
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

	add(listsPanel, BorderLayout.WEST);
	add(codeRangeViewer = new CodeRangeViewer(), BorderLayout.CENTER);
    }

    public void setProjectAnalyser(ProjectAnalyser project) {
	this.project = project;
	refresh();
    }

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
	Collections.sort(files);
	fileList.setListData(new Vector<File>(files));
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
	    String entry =
		    index + ": " + range.getType().getIdentifier() + ":"
			    + range.getName();
	    listData.put(entry, range);
	}
	codeRangeList.setListData(listData);
    }

    @Slot
    public void showFile(int index) {
	codeRangeViewer.setCodeRange(null);
	File file = (File) fileList.getSelectedValue();
	updateCodeRanges(file);
    }

    @Slot
    public void showCodeRange(int index) {
	CodeRange codeRange = (CodeRange) codeRangeList.getSelectedValue();
	if (codeRange == null) {
	    return;
	}
	codeRangeViewer.setCodeRange(codeRange);
    }
}
