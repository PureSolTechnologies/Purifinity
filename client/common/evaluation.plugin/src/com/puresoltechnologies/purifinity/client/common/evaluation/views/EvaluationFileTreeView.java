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
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
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

	private AnalysisProject analysisProject = null;
	private AnalysisRun analysisRun = null;
	private AnalysisFileTree analysisFileTree = null;

	private AnalysisSelection selection;
	private AnalysisFileTree lastFileTreeSelection;

	private final List<ISelectionChangedListener> selectionChangedListener = new ArrayList<ISelectionChangedListener>();

	private Tree fileTree;
	private EvaluationFileTreeViewer fileTreeViewer;
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
			AnalysisProjectSelection analysisProjectSelection = (AnalysisProjectSelection) selection;
			if (hasSelectionChanged(analysisProjectSelection)) {
				analysisProject = analysisProjectSelection.getAnalysisProject();
				analysisRun = null;
				analysisFileTree = null;
				refresh();
			}
		} else if (selection instanceof AnalysisRunSelection) {
			AnalysisRunSelection analysisRunSelection = (AnalysisRunSelection) selection;
			if (hasSelectionChanged(analysisRunSelection)) {
				analysisProject = analysisRunSelection.getAnalysisProject();
				analysisRun = analysisRunSelection.getAnalysisRun();
				analysisFileTree = analysisRun == null ? null : analysisRun
						.getFileTree();
				refresh();
			}
		} else if (selection instanceof AnalysisSelection) {
			AnalysisSelection analysisSelection = (AnalysisSelection) selection;
			if (hasSelectionChanged(analysisSelection)) {
				analysisProject = analysisSelection.getAnalysisProject();
				analysisRun = analysisSelection.getAnalysisRun();
				analysisFileTree = analysisSelection.getFileTreeNode();
				refresh();
			}
		} else {
			return;
		}
	}

	private boolean hasSelectionChanged(
			AnalysisProjectSelection analysisProjectSelection) {
		return hasChanged(analysisProjectSelection.getAnalysisProject());
	}

	private boolean hasChanged(AnalysisProject newAnalysisProject) {
		if ((analysisProject == null) && (newAnalysisProject == null)) {
			return false;
		}
		if (((analysisProject == null) && (newAnalysisProject != null)) || //
				((analysisProject != null) && (newAnalysisProject == null))) {
			return true;
		}
		if (!analysisProject.getInformation().getUUID()
				.equals(newAnalysisProject.getInformation().getUUID())) {
			return true;
		}
		return false;
	}

	private boolean hasSelectionChanged(
			AnalysisRunSelection analysisRunSelection) {
		if (hasChanged(analysisRunSelection.getAnalysisProject())) {
			return true;
		}
		return hasChanged(analysisRunSelection.getAnalysisRun());
	}

	private boolean hasChanged(AnalysisRun newAnalysisRun) {
		if ((analysisRun == null) && (newAnalysisRun == null)) {
			return false;
		}
		if (((analysisRun == null) && (newAnalysisRun != null)) || //
				((analysisRun != null) && (newAnalysisRun == null))) {
			return true;
		}
		if (!analysisRun.getInformation().getUUID()
				.equals(newAnalysisRun.getInformation().getUUID())) {
			return true;
		}
		return false;
	}

	private boolean hasSelectionChanged(AnalysisSelection analysisSelection) {
		if (hasChanged(analysisSelection.getAnalysisProject())) {
			return true;
		}
		if (hasChanged(analysisSelection.getAnalysisRun())) {
			return true;
		}
		return hasChanged(analysisSelection.getFileTreeNode());
	}

	private boolean hasChanged(AnalysisFileTree newFileTreeNode) {
		if ((analysisFileTree == null) && (newFileTreeNode == null)) {
			return false;
		}
		if (((analysisFileTree == null) && (newFileTreeNode != null)) || //
				((analysisFileTree != null) && (newFileTreeNode == null))) {
			return true;
		}
		if (!analysisFileTree.getHashId().equals(newFileTreeNode.getHashId())) {
			return true;
		}
		return false;
	}

	private void refresh() {
		setSelection(new AnalysisSelection(analysisProject, analysisRun,
				analysisFileTree));
		fileTreeViewer.setInput(analysisRun);
		if (analysisRun != null) {
			fileTreeViewer.setSelection(selection.getFileTreeNode());
			fileTree.redraw();
			fileTreeViewer.refresh();
		}
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

	private void processDoubleClickOnFileTree(TreeSelection treeSelection)
			throws PartInitException {
		AnalysisFileTree firstElement = (AnalysisFileTree) treeSelection
				.getFirstElement();
		AnalysisProject analysisProject = selection.getAnalysisProject();
		AnalysisRun analysisRun = selection.getAnalysisRun();
		selection = new AnalysisSelection(analysisProject, analysisRun,
				firstElement);
		AnalysisInformation analyzedCode = analysisRun
				.findAnalyzedCode(firstElement.getPathFile(false).getPath());
		if (analyzedCode != null) {
			FileAnalysisEditorInput fileAnalysisEditorInput = new FileAnalysisEditorInput(
					analysisRun, analyzedCode);
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
		return selection;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListener.remove(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		AnalysisSelection newSelection = (AnalysisSelection) selection;
		if (!newSelection.equals(this.selection)) {
			this.selection = newSelection;
			for (ISelectionChangedListener listener : selectionChangedListener) {
				listener.selectionChanged(new SelectionChangedEvent(this,
						getSelection()));
			}
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
		TreeSelection treeSelection = (TreeSelection) fileTreeViewer
				.getSelection();
		Object first = treeSelection.getFirstElement();
		if (first != null) {
			AnalysisProject analysisProject = selection.getAnalysisProject();
			AnalysisRun analysisRun = selection.getAnalysisRun();
			if (first.getClass().equals(AnalysisFileTree.class)) {
				AnalysisFileTree firstElement = (AnalysisFileTree) first;
				if (!firstElement.equals(lastFileTreeSelection)) {
					AnalysisSelection fileAnalysisSelection = new AnalysisSelection(
							analysisProject, analysisRun, firstElement);
					setSelection(fileAnalysisSelection);
					lastFileTreeSelection = firstElement;
				}
			} else if (first.getClass().equals(String.class)) {
				AnalysisFileTree firstElement = analysisRun.getFileTree();
				AnalysisSelection fileAnalysisSelection = new AnalysisSelection(
						analysisProject, analysisRun, firstElement);
				setSelection(fileAnalysisSelection);
				lastFileTreeSelection = firstElement;
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
