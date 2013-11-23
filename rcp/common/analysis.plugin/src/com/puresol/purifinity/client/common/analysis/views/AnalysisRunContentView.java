package com.puresol.purifinity.client.common.analysis.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;

import com.puresol.purifinity.client.common.analysis.Activator;
import com.puresol.purifinity.client.common.analysis.contents.AnalysisRunContentTreeViewer;
import com.puresol.purifinity.client.common.analysis.editors.DirectoryAnalysisEditor;
import com.puresol.purifinity.client.common.analysis.editors.DirectoryAnalysisEditorInput;
import com.puresol.purifinity.client.common.analysis.editors.FileAnalysisEditor;
import com.puresol.purifinity.client.common.analysis.editors.FileAnalysisEditorInput;
import com.puresol.purifinity.client.common.analysis.editors.NotAnalyzedEditor;
import com.puresol.purifinity.client.common.analysis.editors.NotAnalyzedEditorInput;
import com.puresol.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;
import com.puresol.purifinity.coding.analysis.api.AnalysisProject;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;

public class AnalysisRunContentView extends AbstractPureSolTechnologiesView
		implements ISelectionListener, IDoubleClickListener,
		ISelectionProvider, SelectionListener {

	private AnalysisProject analysis;
	private AnalysisRun analysisRun;
	private Tree fileTree;
	private AnalysisRunContentTreeViewer fileTreeViewer;
	private AnalysisSelection analysisSelection;
	private final List<ISelectionChangedListener> selectionChangedListener = new ArrayList<ISelectionChangedListener>();
	private HashIdFileTree lastSelection;

	public AnalysisRunContentView() {
		super(Activator.getDefault());
	}

	@Override
	public void dispose() {
		IWorkbenchPartSite site = getSite();
		site.getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());

		fileTree = new Tree(composite, SWT.BORDER);
		fileTreeViewer = new AnalysisRunContentTreeViewer(fileTree);
		fileTree.setHeaderVisible(true);
		fileTree.setEnabled(true);
		fileTree.setVisible(true);
		fileTree.addSelectionListener(this);

		IWorkbenchPartSite site = getSite();
		site.getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this);
		site.setSelectionProvider(this);
		fileTreeViewer.addDoubleClickListener(this);
	}

	@Override
	public void setFocus() {
		fileTree.setFocus();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part == this) {
			return;
		}
		if (selection instanceof AnalysisProjectSelection) {
			AnalysisProjectSelection analysisSelection = (AnalysisProjectSelection) selection;
			analysis = analysisSelection.getAnalysisProject();
		} else if (selection instanceof AnalysisRunSelection) {
			AnalysisRunSelection analysisRunSelection = (AnalysisRunSelection) selection;
			setAnalysisRun(analysisRunSelection.getAnalysisRun());
		} else if (selection instanceof AnalysisSelection) {
			AnalysisSelection analysisSelection = (AnalysisSelection) selection;
			analysis = analysisSelection.getAnalysis();
			setAnalysisRun(analysisSelection.getAnalysisRun());
			fileTreeViewer.setSelection(analysisSelection.getFileTreeNode());
		}
	}

	private void setAnalysisRun(AnalysisRun analysisRun) {
		this.analysisRun = analysisRun;
		fileTreeViewer.setInput(analysisRun);
		fileTree.redraw();
		fileTreeViewer.refresh();
	}

	@Override
	public void doubleClick(DoubleClickEvent event) {
		try {
			if (event.getSource() == fileTreeViewer) {
				TreeSelection selection = (TreeSelection) fileTreeViewer
						.getSelection();
				if (!selection.getFirstElement().getClass()
						.equals(String.class)) {
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
		analysisSelection = new AnalysisSelection(analysis, analysisRun,
				firstElement);
		AnalyzedCode analyzedCode = analysisRun.findAnalyzedCode(firstElement
				.getPathFile(false).getPath());
		if (analyzedCode != null) {
			FileAnalysisEditorInput fileAnalysisEditorInput = new FileAnalysisEditorInput(
					analyzedCode, analysisRun);
			getSite().getPage().openEditor(fileAnalysisEditorInput,
					FileAnalysisEditor.class.getName());
		} else if (!analysisRun.getFailedFiles().contains(
				firstElement.getPathFile(false))) {
			DirectoryAnalysisEditorInput directoryAnalysisEditorInput = new DirectoryAnalysisEditorInput(
					firstElement, analysisRun);
			getSite().getPage().openEditor(directoryAnalysisEditorInput,
					DirectoryAnalysisEditor.class.getName());
		} else {
			NotAnalyzedEditorInput notAnalyzedEditorInput = new NotAnalyzedEditorInput(
					firstElement.getPathFile(false), analysisRun);
			getSite().getPage().openEditor(notAnalyzedEditorInput,
					NotAnalyzedEditor.class.getName());
		}
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListener.add(listener);
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListener.remove(listener);
	}

	@Override
	public ISelection getSelection() {
		return analysisSelection;
	}

	@Override
	public void setSelection(ISelection selection) {
		analysisSelection = (AnalysisSelection) selection;
		for (ISelectionChangedListener listener : selectionChangedListener) {
			listener.selectionChanged(new SelectionChangedEvent(this,
					analysisSelection));
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == fileTree) {
			TreeSelection selection = (TreeSelection) fileTreeViewer
					.getSelection();
			Object first = selection.getFirstElement();
			if (first != null) {
				if (first.getClass().equals(HashIdFileTree.class)) {
					HashIdFileTree firstElement = (HashIdFileTree) first;
					if (!firstElement.equals(lastSelection)) {
						AnalysisSelection analysisSelection = new AnalysisSelection(
								analysis, analysisRun, firstElement);
						setSelection(analysisSelection);
						lastSelection = firstElement;
					}
				} else if (first.getClass().equals(String.class)) {
					HashIdFileTree firstElement = analysisRun.getFileTree();
					AnalysisSelection analysisSelection = new AnalysisSelection(
							analysis, analysisRun, firstElement);
					setSelection(analysisSelection);
					lastSelection = firstElement;
				}
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

}
