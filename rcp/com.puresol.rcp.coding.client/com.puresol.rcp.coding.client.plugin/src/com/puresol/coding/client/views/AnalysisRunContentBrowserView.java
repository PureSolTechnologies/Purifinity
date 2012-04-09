package com.puresol.coding.client.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.client.content.AnalysisContentTreeContentProvider;
import com.puresol.coding.client.content.AnalysisContentTreeLabelProvider;

public class AnalysisRunContentBrowserView extends ViewPart implements
	ISelectionListener {

    public static final String ID = "com.puresol.coding.client.AnalysisRunContentBrowserView";
    private Analysis analysis;
    private AnalysisRun analysisRun;
    private Tree fileTree;
    private TreeViewer fileTreeViewer;
    private AnalysisContentTreeLabelProvider labelProvider;

    public AnalysisRunContentBrowserView() {
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new BorderLayout(0, 0));

	fileTree = new Tree(parent, SWT.BORDER);
	fileTreeViewer = new TreeViewer(fileTree);
	fileTreeViewer
		.setContentProvider(new AnalysisContentTreeContentProvider());
	labelProvider = new AnalysisContentTreeLabelProvider();
	fileTreeViewer.setLabelProvider(labelProvider);

	getSite().getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);
    }

    @Override
    public void setFocus() {
	fileTree.setFocus();
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	if (selection instanceof AnalysisSelection) {
	    AnalysisSelection analysisSelection = (AnalysisSelection) selection;
	    analysis = analysisSelection.getAnalysis();
	} else if (selection instanceof AnalysisRunSelection) {
	    AnalysisRunSelection analysisRunSelection = (AnalysisRunSelection) selection;
	    analysisRun = analysisRunSelection.getAnalysisRun();
	    labelProvider.setAnalysisRun(analysisRun);
	    fileTreeViewer.setInput(analysisRun.getFileTree());
	}
    }

}
