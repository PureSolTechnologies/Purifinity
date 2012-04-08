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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.content.AnalysisRunListContentProvider;
import com.puresol.coding.client.content.AnalysisRunListLabelProvider;
import com.puresol.coding.client.controls.ParserTreeControl;

public class AnalysisRunNavigator extends ViewPart implements
	SelectionListener, ISelectionProvider, ISelectionListener {

    private static final ILog logger = Activator.getDefault().getLog();

    private final AnalysisStore store = AnalysisStoreFactory.getInstance();

    public AnalysisRunNavigator() {
    }

    private List analysisRunsList;
    private ListViewer analysisRunsViewer;
    private ISelection selection = null;

    private final java.util.List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FillLayout(SWT.HORIZONTAL));

	analysisRunsList = new List(parent, SWT.BORDER);
	analysisRunsViewer = new ListViewer(analysisRunsList);
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
    public void widgetSelected(SelectionEvent e) {
	if (e.getSource() == analysisRunsList) {
	    StructuredSelection selection = (StructuredSelection) analysisRunsViewer
		    .getSelection();
	    AnalysisRunInformation information = (AnalysisRunInformation) selection
		    .getFirstElement();
	    setSelection(new AnalysisRunSelection(information));
	}
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
	// intentionally left blank.
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	try {
	    if (selection instanceof AnalysisSelection) {
		AnalysisSelection analysisSelection = (AnalysisSelection) selection;
		AnalysisInformation analysisInformation = analysisSelection
			.getInformation();
		Analysis analysis = store.loadAnalysis(analysisInformation
			.getUUID());
		analysisRunsViewer.setInput(analysis.getAllRunInformation());
	    }
	} catch (AnalysisStoreException e) {
	    logger.log(new Status(Status.ERROR, ParserTreeControl.class
		    .getName(), "Can not read analysis store!", e));
	}
    }

}
