package com.puresol.coding.client.views;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
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

public class AnalysisNavigator extends ViewPart implements IJobChangeListener {

    private TreeViewer treeViewer;

    public AnalysisNavigator() {
	Job.getJobManager().addJobChangeListener(this);
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FillLayout(SWT.HORIZONTAL));

	Tree tree = new Tree(parent, SWT.BORDER);
	treeViewer = new TreeViewer(tree);
	treeViewer.setContentProvider(new AnalysisTreeContentProvider());
	treeViewer.setInput(AnalysisNavigatorModel.INSTANCE);
	treeViewer.setLabelProvider(new AnalysisLabelProvider());
	tree.setVisible(true);
    }

    @Override
    public void setFocus() {
	// intentionally left empty
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

}
