package com.puresol.coding.client.views;

import java.util.ArrayList;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.content.AnalysisRunListContentProvider;
import com.puresol.coding.client.content.AnalysisRunListLabelProvider;
import com.puresol.coding.client.controls.ParserTreeControl;

public class AnalysisRunNavigator extends ViewPart implements
	SelectionListener, ISelectionProvider, ISelectionListener {

    private static final ILog logger = Activator.getDefault().getLog();

    private Analysis analysis;
    private List analysisRunsList;
    private ListViewer analysisRunsViewer;
    private ToolItem refresh;
    private ISelection selection = null;

    private final java.util.List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

    public AnalysisRunNavigator() {
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new BorderLayout(0, 0));

	analysisRunsList = new List(parent, SWT.BORDER);
	analysisRunsList
		.setToolTipText("Refreshs the analysis runs for the currently selected analysis from the analysis store.");
	analysisRunsViewer = new ListViewer(analysisRunsList);

	ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
	toolBar.setToolTipText("Refreshs the analysis runs for the currently selected analysis from the analysis store.");
	toolBar.setLayoutData(BorderLayout.NORTH);

	refresh = new ToolItem(toolBar, SWT.NONE);
	refresh.setToolTipText("Refreshs the analysis runs for the selected analysis from the analysis store.");
	refresh.setText("refresh");
	analysisRunsViewer
		.setContentProvider(new AnalysisRunListContentProvider());
	analysisRunsViewer.setLabelProvider(new AnalysisRunListLabelProvider());
	analysisRunsList.redraw();
	analysisRunsViewer.refresh();
	analysisRunsList.addSelectionListener(this);

	getSite().setSelectionProvider(this);
	getSite().getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);
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
	}
    }

    private void refreshAnalysisRunList() {
	try {
	    analysisRunsViewer.setInput(analysis.getAllRunInformation());
	} catch (AnalysisStoreException e) {
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
	    AnalysisRun analysisRun = analysis.loadAnalysisRun(information.getUUID());
	    setSelection(new AnalysisRunSelection(
		    analysisRun));
	} catch (AnalysisStoreException e) {
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
	} catch (AnalysisStoreException e) {
	    logger.log(new Status(Status.ERROR, ParserTreeControl.class
		    .getName(), "Can not read analysis store!", e));
	}
    }
}
