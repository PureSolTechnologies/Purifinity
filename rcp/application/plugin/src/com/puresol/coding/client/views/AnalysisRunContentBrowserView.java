package com.puresol.coding.client.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.content.AnalysisContentTreeContentProvider;
import com.puresol.coding.client.content.AnalysisContentTreeLabelProvider;
import com.puresol.coding.client.editors.DirectoryAnalysisEditor;
import com.puresol.coding.client.editors.DirectoryAnalysisEditorInput;
import com.puresol.coding.client.editors.FileAnalysisEditor;
import com.puresol.coding.client.editors.FileAnalysisEditorInput;
import com.puresol.coding.client.editors.NotAnalyzedEditor;
import com.puresol.coding.client.editors.NotAnalyzedEditorInput;

public class AnalysisRunContentBrowserView extends ViewPart implements
	ISelectionListener, IDoubleClickListener, ISelectionProvider {

    public static final String ID = "com.puresol.coding.client.AnalysisRunContentBrowserView";
    private Analysis analysis;
    private AnalysisRun analysisRun;
    private Tree fileTree;
    private TreeViewer fileTreeViewer;
    private FileAnalysisSelection fileAnalysis;
    private AnalysisContentTreeLabelProvider labelProvider;
    private final List<ISelectionChangedListener> selectionChangedListener = new ArrayList<ISelectionChangedListener>();

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
	getSite().setSelectionProvider(this);
	fileTreeViewer.addDoubleClickListener(this);
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

    @Override
    public void doubleClick(DoubleClickEvent event) {
	try {
	    if (event.getSource() == fileTreeViewer) {
		TreeSelection selection = (TreeSelection) fileTreeViewer
			.getSelection();
		if (selection.getFirstElement().getClass().equals(String.class)) {

		} else {
		    processDoubleClickOnFileTree(selection);
		}
	    }
	} catch (PartInitException e) {
	    throw new RuntimeException(e);
	}
    }

    private void processDoubleClickOnFileTree(TreeSelection selection)
	    throws PartInitException {
	HashIdFileTree firstElement = (HashIdFileTree) selection
		.getFirstElement();
	fileAnalysis = new FileAnalysisSelection(analysis, analysisRun,
		firstElement.getPathFile(false));
	AnalyzedCode analyzedCode = analysisRun.findAnalyzedCode(firstElement
		.getPathFile(false));
	if (analyzedCode != null) {
	    FileAnalysisEditorInput fileAnalysisEditorInput = new FileAnalysisEditorInput(
		    analyzedCode, analysisRun);
	    getSite().getPage().openEditor(fileAnalysisEditorInput,
		    FileAnalysisEditor.ID);
	} else {
	    if (!analysisRun.getFailedCodeLocations().contains(
		    firstElement.getPathFile(false))) {
		DirectoryAnalysisEditorInput directoryAnalysisEditorInput = new DirectoryAnalysisEditorInput(
			firstElement, analysisRun);
		getSite().getPage().openEditor(directoryAnalysisEditorInput,
			DirectoryAnalysisEditor.ID);
	    } else {
		NotAnalyzedEditorInput notAnalyzedEditorInput = new NotAnalyzedEditorInput(
			firstElement.getPathFile(false), analysisRun);
		getSite().getPage().openEditor(notAnalyzedEditorInput,
			NotAnalyzedEditor.ID);
	    }
	}
    }

    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
	selectionChangedListener.add(listener);
    }

    @Override
    public ISelection getSelection() {
	return fileAnalysis;
    }

    @Override
    public void removeSelectionChangedListener(
	    ISelectionChangedListener listener) {
	selectionChangedListener.remove(listener);
    }

    @Override
    public void setSelection(ISelection selection) {
	fileAnalysis = (FileAnalysisSelection) selection;
	for (ISelectionChangedListener listener : selectionChangedListener) {
	    listener.selectionChanged(new SelectionChangedEvent(this,
		    fileAnalysis));
	}
    }

}
