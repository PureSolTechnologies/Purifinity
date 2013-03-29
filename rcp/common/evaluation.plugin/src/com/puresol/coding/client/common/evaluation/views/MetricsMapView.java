package com.puresol.coding.client.common.evaluation.views;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.evaluation.utils.EvaluationTool;
import com.puresol.coding.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.client.common.ui.components.AreaMapComponent;
import com.puresol.coding.client.common.ui.components.AreaMapData;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluators;

public class MetricsMapView extends ViewPart implements Refreshable,
	ISelectionListener, IJobChangeListener, EvaluationsTarget {

    private FileAnalysisSelection analysisSelection;
    private EvaluatorFactory metricSelection;

    private Text text;
    private AreaMapComponent areaMap;

    public MetricsMapView() {
    }

    /**
     * Create contents of the view part.
     * 
     * @param parent
     */
    @Override
    public void createPartControl(Composite parent) {
	Composite container = new Composite(parent, SWT.NONE);
	container.setLayout(new FormLayout());
	{
	    text = new Text(container, SWT.BORDER);
	    FormData fd_text = new FormData();
	    fd_text.top = new FormAttachment(0, 10);
	    fd_text.left = new FormAttachment(0, 10);
	    fd_text.bottom = new FormAttachment(0, 32);
	    fd_text.right = new FormAttachment(100, -10);
	    text.setLayoutData(fd_text);
	    text.setEditable(false);
	}

	IWorkbenchPartSite site = getSite();
	site.getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);

	areaMap = new AreaMapComponent(container, SWT.NONE);
	FormData fd_areaMap = new FormData();
	fd_areaMap.top = new FormAttachment(text, 6);
	fd_areaMap.left = new FormAttachment(text, 0, SWT.LEFT);
	fd_areaMap.bottom = new FormAttachment(100, -10);
	fd_areaMap.right = new FormAttachment(100, -10);
	areaMap.setLayoutData(fd_areaMap);

	initializeToolBar();
	initializeMenu();
    }

    /**
     * Initialize the toolbar.
     */
    private void initializeToolBar() {
	IToolBarManager toolbarManager = getViewSite().getActionBars()
		.getToolBarManager();
	toolbarManager.add(new RefreshAction(this));
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
	// Set the focus
    }

    @Override
    public void refresh() {
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	if (selection instanceof FileAnalysisSelection) {
	    analysisSelection = (FileAnalysisSelection) selection;
	    if (metricSelection != null) {
		updateEvaluation();
	    }
	} else if (selection instanceof MetricSelection) {
	    metricSelection = ((MetricSelection) selection).getMetric();
	    if (analysisSelection != null) {
		updateEvaluation();
	    }
	}
    }

    private void updateEvaluation() {
	AnalysisRun analysisRun = analysisSelection.getAnalysisRun();
	HashIdFileTree path = analysisSelection.getHashIdFile();

	EvaluatorFactory evaluatorFactory = Evaluators.createInstance()
		.getAllMetrics().get(0);

	if (path.isFile()) {
	    path = path.getParent();
	}
	EvaluationTool.showEvaluationAsynchronous(this, evaluatorFactory,
		analysisRun, path);
    }

    private void show(HashIdFileTree directory) {
	text.setText(directory.getPathFile(true).getPath());
    }

    @Override
    public void aboutToRun(IJobChangeEvent event) {
	// intentionally left empty
    }

    @Override
    public void awake(IJobChangeEvent event) {
	// intentionally left empty
    }

    @Override
    public void done(IJobChangeEvent event) {
	HashIdFileTree path = analysisSelection.getHashIdFile();
	if (path.isFile()) {
	    HashIdFileTree directory = path.getParent();
	    if (directory != null) {
		show(directory);
	    } else {
		show(null);
	    }
	} else {
	    show(path);
	}
    }

    @Override
    public void running(IJobChangeEvent event) {
	// intentionally left empty
    }

    @Override
    public void scheduled(IJobChangeEvent event) {
	// intentionally left empty
    }

    @Override
    public void sleeping(IJobChangeEvent event) {
	// intentionally left empty
    }

    @Override
    public void showEvaluation(HashIdFileTree path) {
	text.setText(path.getPathFile(false).getPath());
	AreaMapData data = calculateMapData(path);
	areaMap.setData(data);
    }

    private AreaMapData calculateMapData(HashIdFileTree path) {
	AreaMapData c11 = new AreaMapData("Child11", 1.0);
	AreaMapData c12 = new AreaMapData("Child12", 5.0);

	AreaMapData c1 = new AreaMapData("Child1", 1.0, c11, c12);

	AreaMapData c21 = new AreaMapData("Child21", 1.0);
	AreaMapData c22 = new AreaMapData("Child22", 2.0);
	AreaMapData c23 = new AreaMapData("Child23", 3.0);
	AreaMapData c24 = new AreaMapData("Child24", 5.0);
	AreaMapData c25 = new AreaMapData("Child25", 8.0);

	AreaMapData c2 = new AreaMapData("Child2", 2.0, c21, c22, c23, c24, c25);
	AreaMapData c3 = new AreaMapData("Child3", 3.0);
	AreaMapData root = new AreaMapData("Parent", 6.0, c1, c2, c3);

	return root;
    }
}
