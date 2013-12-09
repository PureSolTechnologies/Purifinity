package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IToolBarManager;
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
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.client.common.analysis.editors.FileAnalysisEditor;
import com.puresoltechnologies.purifinity.client.common.analysis.editors.FileAnalysisEditorInput;
import com.puresoltechnologies.purifinity.client.common.analysis.editors.NotAnalyzedEditor;
import com.puresoltechnologies.purifinity.client.common.analysis.editors.NotAnalyzedEditorInput;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisProjectSelection;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisRunSelection;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.EvaluationFileTreeViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.EvaluatorComboViewer;
import com.puresoltechnologies.purifinity.client.common.ui.actions.PartSettingsCapability;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;

public class EvaluationFileTreeView extends AbstractPureSolTechnologiesView
		implements ISelectionListener, IDoubleClickListener,
		ISelectionProvider, SelectionListener, PartSettingsCapability {

	private AnalysisProject analysis;
	private AnalysisRun analysisRun;
	private Tree fileTree;
	private EvaluationFileTreeViewer fileTreeViewer;
	private AnalysisSelection fileAnalysisSelection;
	private final List<ISelectionChangedListener> selectionChangedListener = new ArrayList<ISelectionChangedListener>();
	private HashIdFileTree lastSelection;

	private Combo evaluatorCombo;
	private EvaluatorComboViewer comboViewer;

	public EvaluationFileTreeView() {
		super(Activator.getDefault());
	}

	@Override
	public void dispose() {
		getSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		parent.setLayout(new FormLayout());

		Label label = new Label(parent, SWT.NONE);
		label.setText("Evaluator selection for quality labels:");
		FormData fdLabel = new FormData();
		fdLabel.top = new FormAttachment(0, 5);
		fdLabel.left = new FormAttachment(0, 5);
		fdLabel.right = new FormAttachment(100, -5);
		label.setLayoutData(fdLabel);

		evaluatorCombo = new Combo(parent, SWT.READ_ONLY);
		FormData fdCombo = new FormData();
		fdCombo.top = new FormAttachment(label, 5);
		fdCombo.left = new FormAttachment(0, 5);
		fdCombo.right = new FormAttachment(100, -5);
		evaluatorCombo.setLayoutData(fdCombo);
		comboViewer = new EvaluatorComboViewer(evaluatorCombo);
		evaluatorCombo.select(0);
		evaluatorCombo.addSelectionListener(this);

		fileTree = new Tree(parent, SWT.BORDER);
		FormData fdFileTree = new FormData();
		fdFileTree.top = new FormAttachment(evaluatorCombo, 5);
		fdFileTree.left = new FormAttachment(0, 5);
		fdFileTree.right = new FormAttachment(100, -5);
		fdFileTree.bottom = new FormAttachment(100, -5);
		fileTree.setLayoutData(fdFileTree);
		fileTree.setHeaderVisible(true);
		fileTree.setLinesVisible(true);
		fileTree.addSelectionListener(this);

		TreeColumn fileColumn = new TreeColumn(fileTree, SWT.LEFT);
		fileColumn.setText("FS Object");
		fileColumn.setAlignment(SWT.LEFT);
		fileColumn.setWidth(250);

		TreeColumn qualityColumn = new TreeColumn(fileTree, SWT.RIGHT);
		qualityColumn.setText("Quality Level");
		qualityColumn.setAlignment(SWT.LEFT);
		qualityColumn.setWidth(50);

		fileTreeViewer = new EvaluationFileTreeViewer(fileTree);
		fileTreeViewer.setEvaluator(comboViewer.getSelectedEvaluator());

		IWorkbenchPartSite site = getSite();
		site.getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this);
		site.setSelectionProvider(this);
		fileTreeViewer.addDoubleClickListener(this);
		initializeToolBar();
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(new ShowSettingsAction(this));
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
		fileAnalysisSelection = new AnalysisSelection(analysis, analysisRun,
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
			// TODO What do we do here? Can we open a meaningful window?
			// DirectoryAnalysisEditorInput directoryAnalysisEditorInput = new
			// DirectoryAnalysisEditorInput(
			// firstElement, analysisRun);
			// getSite().getPage().openEditor(directoryAnalysisEditorInput,
			// DirectoryAnalysisEditor.class.getName());
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
	public ISelection getSelection() {
		return fileAnalysisSelection;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListener.remove(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		fileAnalysisSelection = (AnalysisSelection) selection;
		for (ISelectionChangedListener listener : selectionChangedListener) {
			listener.selectionChanged(new SelectionChangedEvent(this,
					fileAnalysisSelection));
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == fileTree) {
			processFileTreeSelection();
		} else if (e.getSource() == evaluatorCombo) {
			processEvaluatorSelection();
		}
	}

	private void processEvaluatorSelection() {
		fileTreeViewer.setEvaluator(comboViewer.getSelectedEvaluator());
	}

	private void processFileTreeSelection() {
		TreeSelection selection = (TreeSelection) fileTreeViewer.getSelection();
		Object first = selection.getFirstElement();
		if (first != null) {
			if (first.getClass().equals(HashIdFileTree.class)) {
				HashIdFileTree firstElement = (HashIdFileTree) first;
				if (!firstElement.equals(lastSelection)) {
					AnalysisSelection fileAnalysisSelection = new AnalysisSelection(
							analysis, analysisRun, firstElement);
					setSelection(fileAnalysisSelection);
					lastSelection = firstElement;
				}
			} else if (first.getClass().equals(String.class)) {
				HashIdFileTree firstElement = analysisRun.getFileTree();
				AnalysisSelection fileAnalysisSelection = new AnalysisSelection(
						analysis, analysisRun, firstElement);
				setSelection(fileAnalysisSelection);
				lastSelection = firstElement;
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

	@Override
	public void showSettings() {
		// TODO Auto-generated method stub

	}

	@Override
	public void applySettings() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeSettings() {
		// TODO Auto-generated method stub

	}

}
