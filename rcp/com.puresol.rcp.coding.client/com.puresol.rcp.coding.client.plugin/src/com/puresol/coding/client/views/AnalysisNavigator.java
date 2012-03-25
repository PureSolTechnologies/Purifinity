package com.puresol.coding.client.views;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.UIJob;

import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.content.AnalysisLabelProvider;
import com.puresol.coding.client.content.AnalysisNavigatorModel;
import com.puresol.coding.client.content.AnalysisNavigatorTreeNodeElement;
import com.puresol.coding.client.content.AnalysisTreeContentProvider;
import com.puresol.coding.client.editors.DirectoryAnalysisEditorInput;
import com.puresol.coding.client.editors.EditorIds;
import com.puresol.coding.client.editors.FileAnalysisEditorInput;
import com.puresol.coding.client.editors.NotAnalyzedEditorInput;

/**
 * This view shows a list of all analysis which are opened and the tree of files
 * below the analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisNavigator extends ViewPart implements IJobChangeListener,
	ISelectionProvider, SelectionListener, IDoubleClickListener {

    private static final ILog logger = Activator.getDefault().getLog();

    public AnalysisNavigator() {
    }

    private Tree tree;
    private TreeViewer treeViewer;
    private ISelection selection = null;

    private final List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FillLayout(SWT.HORIZONTAL));

	tree = new Tree(parent, SWT.BORDER);
	treeViewer = new TreeViewer(tree);
	treeViewer.addDoubleClickListener(this);
	treeViewer.setContentProvider(new AnalysisTreeContentProvider());
	treeViewer.setInput(AnalysisNavigatorModel.INSTANCE);
	treeViewer.setLabelProvider(new AnalysisLabelProvider());
	tree.setVisible(true);
	tree.addSelectionListener(this);

	Job.getJobManager().addJobChangeListener(this);
	getSite().setSelectionProvider(this);
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
	if (e.getSource().equals(tree)) {
	    TreeItem[] treeItems = tree.getSelection();
	    if (treeItems.length > 0) {
		AnalysisSelection analysisSelection = createAnalysisNavigatorSelection(treeItems[0]);
		setSelection(analysisSelection);
	    }
	}
    }

    /**
     * This method takes the tree items of the tree selection and creates a
     * navigator selection.
     * 
     * @param treeItems
     * @return
     */
    private AnalysisSelection createAnalysisNavigatorSelection(TreeItem treeItem) {
	AnalysisNavigatorTreeNodeElement analyzer = (AnalysisNavigatorTreeNodeElement) treeItem
		.getData();
	AnalysisSelection analysisSelection = new AnalysisSelection(
		analyzer.getAnalyser(), analyzer.getSourceFile().getPathFile(
			false));
	return analysisSelection;
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
	// intentionally left blank.
    }

    /**
     * This method is implemened for IDoubleClickListener and is used to open a
     * file editor.
     */
    @Override
    public void doubleClick(DoubleClickEvent event) {
	try {
	    if (event.getSource() == treeViewer) {
		TreeItem[] treeItems = tree.getSelection();
		if (treeItems.length > 0) {
		    AnalysisSelection analysisSelection = createAnalysisNavigatorSelection(treeItems[0]);
		    ProjectAnalyzer analyzer = analysisSelection.getAnalyzer();
		    File sourceFile = analysisSelection.getSourceFile();

		    File originalFile = new File(
			    analyzer.getProjectDirectory(),
			    sourceFile.getPath());
		    if (!originalFile.exists()) {
			logger.log(new Status(Status.ERROR,
				AnalysisNavigator.class.getName(),
				"Could not find original file: '"
					+ originalFile.getPath() + "'"));
			return;
		    }
		    IWorkbenchPage page = getSite().getPage();
		    if (originalFile.isFile()) {
			AnalyzedFile analyzedFile = analyzer
				.findAnalyzedFile(sourceFile);
			if (analyzedFile != null) {
			    Analysis analysis = analyzer
				    .getAnalysis(analyzedFile);
			    page.openEditor(new FileAnalysisEditorInput(
				    analyzedFile, analysis),
				    EditorIds.FILE_ANALYSIS_EDITOR_ID);
			} else {
			    page.openEditor(new NotAnalyzedEditorInput(
				    originalFile),
				    EditorIds.NOT_ANALYZED_ANALYSIS_EDITOR_ID);
			}
		    } else {
			page.openEditor(new DirectoryAnalysisEditorInput(
				originalFile, analyzer),
				EditorIds.DIRECTORY_ANALYSIS_EDITOR_ID);
		    }
		}
	    }
	} catch (PartInitException e) {
	    logger.log(new Status(Status.ERROR, AnalysisNavigator.class
		    .getName(), e.getMessage(), e));
	}
    }

}
