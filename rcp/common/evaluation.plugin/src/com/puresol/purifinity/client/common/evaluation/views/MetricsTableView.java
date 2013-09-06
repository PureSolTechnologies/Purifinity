package com.puresol.purifinity.client.common.evaluation.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

import com.puresol.purifinity.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.purifinity.client.common.branding.Exportable;
import com.puresol.purifinity.client.common.branding.Printable;
import com.puresol.purifinity.client.common.evaluation.contents.EvaluatorComboViewer;
import com.puresol.purifinity.client.common.evaluation.contents.MetricsTableViewer;
import com.puresol.purifinity.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.purifinity.client.common.ui.actions.ExportAction;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorFactory;

public class MetricsTableView extends ViewPart implements ISelectionListener,
		EvaluationsTarget, ISelectionChangedListener, Exportable, Printable {

	private ISelectionService selectionService;
	private Combo evaluatorCombo;
	private EvaluatorComboViewer evaluatorComboViewer;
	private ScrolledComposite scrolledComposite;
	private Table table;
	private MetricsTableViewer tableViewer;

	private FileAnalysisSelection analysisSelection;
	private EvaluatorFactory selectedEvaluator;

	@Override
	public void dispose() {
		evaluatorComboViewer.dispose();
		selectionService.removeSelectionListener(this);
		super.dispose();
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());

		evaluatorCombo = new Combo(parent, SWT.READ_ONLY);
		FormData fdEvaluatorCombo = new FormData();
		fdEvaluatorCombo.left = new FormAttachment(0, 10);
		fdEvaluatorCombo.right = new FormAttachment(100, -10);
		fdEvaluatorCombo.top = new FormAttachment(0, 10);
		evaluatorCombo.setLayoutData(fdEvaluatorCombo);
		evaluatorComboViewer = new EvaluatorComboViewer(evaluatorCombo);
		evaluatorComboViewer.addSelectionChangedListener(this);
		if (evaluatorComboViewer.getNumber() > 0) {
			evaluatorCombo.select(0);
			selectedEvaluator = evaluatorComboViewer.getSelectedEvaluator();
		}

		scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		FormData fdScrolledComposite = new FormData();
		fdScrolledComposite.left = new FormAttachment(0, 10);
		fdScrolledComposite.right = new FormAttachment(100, -10);
		fdScrolledComposite.top = new FormAttachment(evaluatorCombo, 10);
		fdScrolledComposite.bottom = new FormAttachment(100, -10);
		scrolledComposite.setLayoutData(fdScrolledComposite);

		table = new Table(scrolledComposite, SWT.BORDER);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setVisible(true);
		scrolledComposite.setContent(table);

		IWorkbenchPartSite site = getSite();
		IWorkbenchWindow workbenchWindow = site.getWorkbenchWindow();
		selectionService = workbenchWindow.getSelectionService();
		selectionService.addSelectionListener(this);

		table.setSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(new ExportAction(this));
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		evaluatorCombo.setFocus();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof FileAnalysisSelection) {
			analysisSelection = (FileAnalysisSelection) selection;
			updateEvaluation();
		}
	}

	private void updateEvaluation() {
		HashIdFileTree path = analysisSelection.getFileTreeNode();
		showEvaluation(path);
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		tableViewer = new MetricsTableViewer(table, selectedEvaluator);
		tableViewer.setInput(path);

		table.setSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.layout(true, true);
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		selectedEvaluator = evaluatorComboViewer.getSelectedEvaluator();
		if (analysisSelection != null) {
			showEvaluation(analysisSelection.getFileTreeNode());
		}
	}

	@Override
	public void print(Printer printer, String printJobName) {
		// TODO Auto-generated method stub
	}

	@Override
	public void export() {
		// TODO Auto-generated method stub
	}
}
