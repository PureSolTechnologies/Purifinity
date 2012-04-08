package com.puresol.coding.client.views;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
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
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.UIJob;

import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.client.content.AnalysisListContentProvider;
import com.puresol.coding.client.content.AnalysisListLabelProvider;
import com.puresol.coding.client.wizards.NewAnalysisJob;

/**
 * This view shows a list of all analysis which are opened and the tree of files
 * below the analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisNavigator extends ViewPart implements IJobChangeListener,
	ISelectionProvider, SelectionListener {

    private final AnalysisStore store = AnalysisStoreFactory.getInstance();

    public AnalysisNavigator() {
    }

    private List analyzesList;
    private ListViewer analyzesViewer;
    private ISelection selection = null;

    private final java.util.List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FillLayout(SWT.HORIZONTAL));

	analyzesList = new List(parent, SWT.BORDER);
	analyzesViewer = new ListViewer(analyzesList);
	analyzesViewer.setContentProvider(new AnalysisListContentProvider());
	analyzesViewer.setLabelProvider(new AnalysisListLabelProvider());
	analyzesList.redraw();
	analyzesViewer.refresh();
	analyzesList.addSelectionListener(this);

	updateAnalysisList();
	Job.getJobManager().addJobChangeListener(this);
	getSite().setSelectionProvider(this);
    }

    @Override
    public void setFocus() {
	analyzesList.setFocus();
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
	Job job = event.getJob();
	if (job.getClass().equals(NewAnalysisJob.class)) {
	    updateAnalysisList();
	}
    }

    private void updateAnalysisList() {
	UIJob uiJob = new UIJob("Update Analysis Navigator") {
	    @Override
	    public IStatus runInUIThread(IProgressMonitor monitor) {
		try {
		    analyzesViewer.setInput(store.getAllAnalysisInformation());
		    return Status.OK_STATUS;
		} catch (AnalysisStoreException e) {
		    return new Status(
			    Status.ERROR,
			    AnalysisNavigator.class.getName(),
			    "Could not retrieve available analyzes from analysis store!",
			    e);
		}
	    }
	};
	uiJob.schedule();
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
	if (e.getSource() == analyzesList) {
	    StructuredSelection selection = (StructuredSelection) analyzesViewer
		    .getSelection();
	    AnalysisInformation information = (AnalysisInformation) selection
		    .getFirstElement();
	    setSelection(new AnalysisSelection(information));
	}
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
	// intentionally left blank.
    }

}
