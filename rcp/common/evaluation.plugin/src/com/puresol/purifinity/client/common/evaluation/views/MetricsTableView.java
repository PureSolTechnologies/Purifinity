package com.puresol.purifinity.client.common.evaluation.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
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

import com.puresol.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresol.purifinity.client.common.branding.Exportable;
import com.puresol.purifinity.client.common.branding.Printable;
import com.puresol.purifinity.client.common.evaluation.Activator;
import com.puresol.purifinity.client.common.evaluation.contents.EvaluatorComboViewer;
import com.puresol.purifinity.client.common.evaluation.contents.MetricsTableViewer;
import com.puresol.purifinity.client.common.evaluation.ui.QualityLevelLabel;
import com.puresol.purifinity.client.common.evaluation.ui.SourceCodeQualityLabel;
import com.puresol.purifinity.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.purifinity.client.common.ui.SWTUtils;
import com.puresol.purifinity.client.common.ui.actions.ExportAction;
import com.puresol.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;

public class MetricsTableView extends AbstractPureSolTechnologiesView implements
		ISelectionListener, EvaluationsTarget, ISelectionChangedListener,
		Exportable, Printable {

	private ISelectionService selectionService;
	private Combo evaluatorCombo;
	private EvaluatorComboViewer evaluatorComboViewer;
	private Table table;
	private MetricsTableViewer tableViewer;

	private AnalysisSelection analysisSelection;
	private EvaluatorFactory selectedEvaluator;
	private SourceCodeQualityLabel quality;
	private QualityLevelLabel qualityLevel;

	public MetricsTableView() {
		super(Activator.getDefault());
	}

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
		super.createPartControl(parent);

		parent.setLayout(new FormLayout());

		evaluatorCombo = new Combo(parent, SWT.READ_ONLY);
		FormData fdEvaluatorCombo = new FormData();
		fdEvaluatorCombo.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdEvaluatorCombo.right = new FormAttachment(100,
				-SWTUtils.DEFAULT_MARGIN);
		fdEvaluatorCombo.top = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		evaluatorCombo.setLayoutData(fdEvaluatorCombo);
		evaluatorComboViewer = new EvaluatorComboViewer(evaluatorCombo);
		evaluatorComboViewer.addSelectionChangedListener(this);
		if (evaluatorComboViewer.getNumber() > 0) {
			evaluatorCombo.select(0);
			selectedEvaluator = evaluatorComboViewer.getSelectedEvaluator();
		}

		quality = new SourceCodeQualityLabel(parent, SWT.NONE);
		FormData fdQuality = new FormData();
		fdQuality.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdQuality.right = new FormAttachment(100, -SWTUtils.DEFAULT_MARGIN);
		fdQuality.top = new FormAttachment(evaluatorCombo,
				SWTUtils.DEFAULT_MARGIN);
		quality.setLayoutData(fdQuality);

		qualityLevel = new QualityLevelLabel(parent, SWT.NONE);
		FormData fdQualityLevel = new FormData();
		fdQualityLevel.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdQualityLevel.right = new FormAttachment(100, -SWTUtils.DEFAULT_MARGIN);
		fdQualityLevel.top = new FormAttachment(quality,
				SWTUtils.DEFAULT_MARGIN);
		qualityLevel.setLayoutData(fdQualityLevel);

		table = new Table(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setVisible(true);
		FormData fdTable = new FormData();
		fdTable.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdTable.right = new FormAttachment(100, -SWTUtils.DEFAULT_MARGIN);
		fdTable.top = new FormAttachment(qualityLevel, SWTUtils.DEFAULT_MARGIN);
		fdTable.bottom = new FormAttachment(100, -SWTUtils.DEFAULT_MARGIN);
		table.setLayoutData(fdTable);

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
		if (selection instanceof AnalysisSelection) {
			analysisSelection = (AnalysisSelection) selection;
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

		SourceCodeQuality sourceCodeQuality = null;
		QualityLevel qualityLevel = null;

		EvaluatorStoreFactory factory = EvaluatorStoreFactory.getFactory();
		EvaluatorStore store = factory.createInstance(selectedEvaluator
				.getEvaluatorClass());
		if (path.isFile()) {
			MetricFileResults fileResults = store.readFileResults(path
					.getHashId());
			if (fileResults != null) {
				sourceCodeQuality = fileResults.getSourceQuality();
				qualityLevel = fileResults.getQualityLevel();
			} else {
				sourceCodeQuality = SourceCodeQuality.UNSPECIFIED;
				qualityLevel = null;
			}
		} else {
			MetricDirectoryResults directoryResults = store
					.readDirectoryResults(path.getHashId());
			if (directoryResults != null) {
				sourceCodeQuality = directoryResults.getSourceQuality();
				qualityLevel = directoryResults.getQualityLevel();
			} else {
				sourceCodeQuality = SourceCodeQuality.UNSPECIFIED;
				qualityLevel = null;
			}
		}

		quality.setSourceCodeQuality(sourceCodeQuality);
		this.qualityLevel.setQualityLevel(qualityLevel);
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
