package com.puresol.coding.client.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.UIJob;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.client.content.AnalysisLabelProvider;
import com.puresol.coding.client.content.AnalysisNavigatorModel;
import com.puresol.coding.client.content.AnalysisTreeContentProvider;

public class AnalysisNavigator extends ViewPart implements IJobChangeListener,
	ISelectionProvider {

    private Tree tree;
    private TreeViewer treeViewer;

    private final List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

    public AnalysisNavigator() {
	Job.getJobManager().addJobChangeListener(this);
	getSite().setSelectionProvider(this);
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FillLayout(SWT.HORIZONTAL));

	tree = new Tree(parent, SWT.BORDER);
	treeViewer = new TreeViewer(tree);
	treeViewer.setContentProvider(new AnalysisTreeContentProvider());
	treeViewer.setInput(AnalysisNavigatorModel.INSTANCE);
	treeViewer.setLabelProvider(new AnalysisLabelProvider());
	tree.setVisible(true);
    }

    @Override
    public void setFocus() {
	tree.setFocus();
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
	if (job.getClass().equals(ProjectAnalyzer.class)) {
	    ProjectAnalyzer analyzer = (ProjectAnalyzer) job;
	    AnalysisNavigatorModel.INSTANCE.addAnalysis(analyzer);
	    UIJob refresh = new UIJob("Refresh Analysis Navigator") {

		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
		    treeViewer.refresh();
		    return Status.OK_STATUS;
		}
	    };
	    refresh.schedule();
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
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
	listeners.add(listener);
    }

    @Override
    public ISelection getSelection() {
	return null;
    }

    @Override
    public void removeSelectionChangedListener(
	    ISelectionChangedListener listener) {
	listeners.remove(listener);
    }

    @Override
    public void setSelection(ISelection selection) {
	// TODO Auto-generated method stub
    }

}
