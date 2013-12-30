package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import org.eclipse.core.runtime.Status;
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

import com.puresoltechnologies.purifinity.analysis.domain.HashIdFileTree;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.branding.Exportable;
import com.puresoltechnologies.purifinity.client.common.branding.Printable;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.EvaluatorComboViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.MetricsTableViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.ui.QualityLevelLabel;
import com.puresoltechnologies.purifinity.client.common.evaluation.ui.SourceCodeQualityLabel;
import com.puresoltechnologies.purifinity.client.common.evaluation.utils.EvaluationsTarget;
import com.puresoltechnologies.purifinity.client.common.ui.SWTUtils;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ExportAction;
import com.puresoltechnologies.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricFileResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorFactory;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;

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
			try {
				updateEvaluation();
			} catch (EvaluationStoreException e) {
				Activator activator = Activator.getDefault();
				activator.getLog().log(
						new Status(Status.ERROR, activator.getBundle()
								.getSymbolicName(),
								"Could not handle new selection.", e));
			}
		}
	}

	private void updateEvaluation() throws EvaluationStoreException {
		HashIdFileTree path = analysisSelection.getFileTreeNode();
		showEvaluation(path);
	}

	@Override
	public void showEvaluation(HashIdFileTree path)
			throws EvaluationStoreException {
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
			try {
				showEvaluation(analysisSelection.getFileTreeNode());
			} catch (EvaluationStoreException e) {
				Activator activator = Activator.getDefault();
				activator.getLog().log(
						new Status(Status.ERROR, activator.getBundle()
								.getSymbolicName(),
								"Could not handle new selection.", e));
			}
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
