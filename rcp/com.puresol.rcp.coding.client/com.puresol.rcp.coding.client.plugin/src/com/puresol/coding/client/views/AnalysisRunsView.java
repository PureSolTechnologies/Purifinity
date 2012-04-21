package com.puresol.coding.client.views;

import java.util.ArrayList;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.DirectoryStoreException;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.ClientImages;
import com.puresol.coding.client.content.AnalysisRunListContentProvider;
import com.puresol.coding.client.content.AnalysisRunListLabelProvider;
import com.puresol.coding.client.controls.ParserTreeControl;
import com.puresol.coding.client.jobs.EvaluationJob;

public class AnalysisRunsView extends ViewPart implements SelectionListener,
	ISelectionProvider, ISelectionListener {

    private static final ILog logger = Activator.getDefault().getLog();

    public static final String ID = "com.puresol.coding.client.AnalysisRunsView";

    private final ImageRegistry imageRegistry = Activator.getDefault()
	    .getImageRegistry();
    private final Image databaseRefreshImage = imageRegistry
	    .get(ClientImages.DATABASE_REFRESH_16x16);
    private final Image analysisRunAddImage = imageRegistry
	    .get(ClientImages.ANALYSIS_RUN_ADD_16x16);
    private final Image analysisRunEditImage = imageRegistry
	    .get(ClientImages.ANALYSIS_RUN_EDIT_16x16);
    private final Image analysisRunDeleteImage = imageRegistry
	    .get(ClientImages.ANALYSIS_RUN_DELETE_16x16);

    private Analysis analysis;
    private Table analysisRunsList;
    private TableViewer analysisRunsViewer;
    private ToolItem refresh;
    private ToolItem addAnalysisRun;
    private ToolItem editAnalysisRun;
    private ToolItem deleteAnalysisRun;

    private ISelection selection = null;

    private final java.util.List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

    public AnalysisRunsView() {
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new BorderLayout(0, 0));

	analysisRunsList = new Table(parent, SWT.BORDER);
	analysisRunsList
		.setToolTipText("Refreshs the analysis runs for the currently selected analysis from the analysis store.");
	analysisRunsViewer = new TableViewer(analysisRunsList);

	ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
	toolBar.setToolTipText("Refreshs the analysis runs for the currently selected analysis from the analysis store.");
	toolBar.setLayoutData(BorderLayout.NORTH);

	refresh = new ToolItem(toolBar, SWT.NONE);
	refresh.setToolTipText("Refreshs the analysis runs for the selected analysis from the analysis store.");
	refresh.setText("Refresh");
	refresh.setImage(databaseRefreshImage);

	addAnalysisRun = new ToolItem(toolBar, SWT.NONE);
	addAnalysisRun.setText("Add...");
	addAnalysisRun.setImage(analysisRunAddImage);
	addAnalysisRun.addSelectionListener(this);

	editAnalysisRun = new ToolItem(toolBar, SWT.NONE);
	editAnalysisRun.setText("Edit...");
	editAnalysisRun.setImage(analysisRunEditImage);
	editAnalysisRun.addSelectionListener(this);

	deleteAnalysisRun = new ToolItem(toolBar, SWT.NONE);
	deleteAnalysisRun.setText("Delete...");
	deleteAnalysisRun.setImage(analysisRunDeleteImage);
	deleteAnalysisRun.addSelectionListener(this);

	analysisRunsViewer
		.setContentProvider(new AnalysisRunListContentProvider());
	analysisRunsViewer.setLabelProvider(new AnalysisRunListLabelProvider());
	analysisRunsList.redraw();
	analysisRunsViewer.refresh();
	analysisRunsList.addSelectionListener(this);

	getSite().setSelectionProvider(this);
	getSite().getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);
	setContentDescription("All available analysis runs for the selected analysis");
    }

    @Override
    public void setFocus() {
	analysisRunsList.setFocus();
    }

    @Override
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
	listeners.add(listener);
    }

    @Override
    public ISelection getSelection() {
	return selection;
    }

    @Override
    public void removeSelectionChangedListener(
	    ISelectionChangedListener listener) {
	listeners.remove(listener);
    }

    @Override
    public void setSelection(ISelection selection) {
	this.selection = selection;
	for (ISelectionChangedListener listener : listeners) {
	    listener.selectionChanged(new SelectionChangedEvent(this,
		    getSelection()));
	}
    }

    @Override
    public void widgetSelected(SelectionEvent event) {
	if (event.getSource() == analysisRunsList) {
	    processAnalysisRunSelection();
	} else if (event.getSource() == refresh) {
	    refreshAnalysisRunList();
	} else if (event.getSource() == addAnalysisRun) {
	    addAnalysisRun();
	} else if (event.getSource() == editAnalysisRun) {
	    editAnalysisRun();
	} else if (event.getSource() == deleteAnalysisRun) {
	    deleteAnalysisRun();
	}
    }

    private void addAnalysisRun() {
	try {
	    AnalysisRun analysisRun = analysis.runAnalysis();
	    EvaluationJob evaluationJob = new EvaluationJob(analysisRun);
	    evaluationJob.schedule();
	    refreshAnalysisRunList();
	} catch (DirectoryStoreException e) {
	    logger.log(new Status(Status.ERROR, AnalysisRunsView.class
		    .getName(), "Could not start new analysis run!", e));
	}
    }

    private void editAnalysisRun() {
	MessageDialog.openInformation(getSite().getShell(), "Not implemented",
		"This functionality is not implemented, yet!");
    }

    private void deleteAnalysisRun() {
	try {
	    StructuredSelection selection = (StructuredSelection) analysisRunsViewer
		    .getSelection();
	    AnalysisRunInformation information = (AnalysisRunInformation) selection
		    .getFirstElement();
	    if (MessageDialog.openQuestion(
		    getSite().getShell(),
		    "Delete?",
		    "Do you really want to delete analysis '"
			    + information.getTime() + "'?")) {
		analysis.removeAnalysisRun(information.getUUID());
		refreshAnalysisRunList();
	    }
	} catch (DirectoryStoreException e) {
	    logger.log(new Status(Status.ERROR, ParserTreeControl.class
		    .getName(), "Can not read analysis runs from store!", e));
	}
    }

    private void refreshAnalysisRunList() {
	try {
	    analysisRunsViewer.setInput(analysis.getAllRunInformation());
	} catch (DirectoryStoreException e) {
	    logger.log(new Status(Status.ERROR, ParserTreeControl.class
		    .getName(), "Can not read analysis runs from store!", e));
	}
    }

    private void processAnalysisRunSelection() {
	try {
	    StructuredSelection selection = (StructuredSelection) analysisRunsViewer
		    .getSelection();
	    AnalysisRunInformation information = (AnalysisRunInformation) selection
		    .getFirstElement();
	    AnalysisRun analysisRun = analysis.loadAnalysisRun(information
		    .getUUID());
	    setSelection(new AnalysisRunSelection(analysisRun));
	} catch (DirectoryStoreException e) {
	    logger.log(new Status(Status.ERROR, ParserTreeControl.class
		    .getName(), "Can not read analysis runs from store!", e));
	}
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
	// intentionally left blank.
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	if (selection instanceof AnalysisSelection) {
	    processGlobalAnalysisSelection(selection);
	}
    }

    private void processGlobalAnalysisSelection(ISelection selection) {
	try {
	    AnalysisSelection analysisSelection = (AnalysisSelection) selection;
	    analysis = analysisSelection.getAnalysis();
	    analysisRunsViewer.setInput(analysis.getAllRunInformation());
	} catch (DirectoryStoreException e) {
	    logger.log(new Status(Status.ERROR, ParserTreeControl.class
		    .getName(), "Can not read analysis store!", e));
	}
    }
}
