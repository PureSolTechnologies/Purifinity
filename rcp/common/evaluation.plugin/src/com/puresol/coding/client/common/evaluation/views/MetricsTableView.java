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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.evaluation.contents.MetricsTableViewer;
import com.puresol.coding.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;

public class MetricsTableView extends ViewPart implements Refreshable,
	ISelectionListener, IJobChangeListener, EvaluationsTarget {

    private FileAnalysisSelection analysisSelection;

    private Label label;
    private MetricsTableViewer viewer;

    public MetricsTableView() {
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
	    label = new Label(container, SWT.BORDER);
	    FormData fd_text = new FormData();
	    fd_text.top = new FormAttachment(0, 10);
	    fd_text.left = new FormAttachment(0, 10);
	    fd_text.bottom = new FormAttachment(0, 32);
	    fd_text.right = new FormAttachment(100, -10);
	    label.setLayoutData(fd_text);
	}

	IWorkbenchPartSite site = getSite();
	site.getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);

	Table table = new Table(container, SWT.NONE);
	table.setLinesVisible(true);
	table.setHeaderVisible(true);
	FormData fd_areaMap = new FormData();
	fd_areaMap.top = new FormAttachment(label, 6);
	fd_areaMap.left = new FormAttachment(label, 0, SWT.LEFT);
	fd_areaMap.bottom = new FormAttachment(100, -10);
	fd_areaMap.right = new FormAttachment(100, -10);
	table.setLayoutData(fd_areaMap);

	viewer = new MetricsTableViewer(table);

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
	    updateEvaluation();
	}
    }

    private void updateEvaluation() {
	HashIdFileTree path = analysisSelection.getHashIdFile();
	showEvaluation(path);
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
		showEvaluation(directory);
	    } else {
		showEvaluation(null);
	    }
	} else {
	    showEvaluation(path);
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
	label.setText(path.getPathFile(false).getPath());
	viewer.setInput(path);
    }
}
