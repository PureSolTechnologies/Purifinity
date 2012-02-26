package com.puresol.gui.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;

/**
 * This GUI element is for selecting files and code ranges from a project
 * analyzer. Signals bind these selections to outer actions.
 * 
 * This element uses AnalyzedFileChooser for file selection.
 * 
 * @see AnalyzedFileChooser
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeRangeChooser extends JPanel implements TreeSelectionListener,
	ListSelectionListener {

    private static final long serialVersionUID = 7855693564694783199L;

    private ProjectAnalyzer projectAnalyser = null;

    private final AnalyzedFileChooser fileChooser = new AnalyzedFileChooser();
    private final JList codeRangeList = new JList();

    private final List<TreeSelectionListener> treeListener = new ArrayList<TreeSelectionListener>();
    private final List<ListSelectionListener> listListener = new ArrayList<ListSelectionListener>();

    public CodeRangeChooser() {
	super();
	initUI();
    }

    public CodeRangeChooser(ProjectAnalyzer projectAnalyser) {
	super();
	this.projectAnalyser = projectAnalyser;
	initUI();
    }

    private void initUI() {
	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	fileChooser.setBorder(new TitledBorder("Analyzed Files"));
	fileChooser.addTreeSelectionListener(this);

	codeRangeList.addListSelectionListener(this);

	JScrollPane codeRangeScroller = new JScrollPane(codeRangeList);
	codeRangeScroller.setBorder(new TitledBorder("Analyzable Code Ranges"));

	JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true,
		new JScrollPane(fileChooser), codeRangeScroller);
	add(splitPane);
    }

    public ProjectAnalyzer getProjectAnlayser() {
	return projectAnalyser;
    }

    public void setProjectAnalyser(ProjectAnalyzer projectAnalyser) {
	this.projectAnalyser = projectAnalyser;
	fileChooser.setProjectAnalyzer(projectAnalyser);
	codeRangeList.removeAll();
    }

    public AnalyzedFile getAnalyzedFile() {
	return fileChooser.getAnalyzedFile();
    }

    public CodeRange getCodeRange() {
	return (CodeRange) codeRangeList.getSelectedValue();
    }

    void fileSelected(AnalyzedFile file) {
	Analysis analysis = projectAnalyser.getAnalysis(file);
	if (analysis != null) {
	    java.util.List<CodeRange> codeRanges = analysis
		    .getAnalyzableCodeRanges();
	    Collections.sort(codeRanges);
	    codeRangeList.setListData(new Vector<CodeRange>(codeRanges));
	}
    }

    public void addTreeListener(TreeSelectionListener listener) {
	treeListener.add(listener);
    }

    public void removeTreeListener(TreeSelectionListener listener) {
	treeListener.remove(listener);
    }

    public void addListListener(ListSelectionListener listener) {
	listListener.add(listener);
    }

    public void removeListListener(ListSelectionListener listener) {
	listListener.remove(listener);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
	fileSelected(fileChooser.getAnalyzedFile());
	for (TreeSelectionListener listener : treeListener) {
	    listener.valueChanged(e);
	}
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
	for (ListSelectionListener listener : listListener) {
	    listener.valueChanged(e);
	}
    }
}
