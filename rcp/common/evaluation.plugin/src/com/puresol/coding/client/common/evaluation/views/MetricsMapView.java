package com.puresol.coding.client.common.evaluation.views;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.evaluation.jobs.EvaluationJob;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.utils.HashId;

public class MetricsMapView extends ViewPart implements Refreshable,
	ISelectionListener, IJobChangeListener {

    private FileAnalysisSelection analysisSelection;
    private Text text;

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
	container.setLayout(new FillLayout(SWT.HORIZONTAL));
	{
	    text = new Text(container, SWT.BORDER);
	    text.setEditable(false);
	}

	IWorkbenchPartSite site = getSite();
	site.getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);

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
	    HashIdFileTree path = analysisSelection.getHashIdFile();
	    HashId hashId = path.getHashId();

	    EvaluatorStoreFactory factory = EvaluatorStoreFactory.getFactory();
	    EvaluatorStore store = factory.createInstance(SLOCEvaluator.class);
	    if (path.isFile()) {
		if (!store.hasFileResults(hashId)) {
		    runEvaluation(analysisSelection);
		} else {
		    HashIdFileTree directory = path.getParent();
		    if (directory != null) {
			show(directory);
		    } else {
			show(null);
		    }
		}
	    } else {
		if (!store.hasDirectoryResults(hashId)) {
		    runEvaluation(analysisSelection);
		} else {
		    show(path);
		}
	    }
	}
    }

    private void show(HashIdFileTree directory) {
	text.setText(directory.getPathFile(true).getPath());
    }

    private void runEvaluation(FileAnalysisSelection analysisSelection) {
	EvaluationJob job = new EvaluationJob(
		analysisSelection.getAnalysisRun());
	job.addJobChangeListener(this);
	job.schedule();
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
}
