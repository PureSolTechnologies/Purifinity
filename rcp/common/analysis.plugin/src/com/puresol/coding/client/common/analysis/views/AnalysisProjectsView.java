package com.puresol.coding.client.common.analysis.views;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
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
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.UIJob;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisProjectInformation;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.client.common.analysis.Activator;
import com.puresol.coding.client.common.analysis.contents.AnalysisProjectsTableViewer;
import com.puresol.coding.client.common.analysis.handlers.NewAnalysisProjectHandler;
import com.puresol.coding.client.common.analysis.jobs.AnalysisJob;
import com.puresol.coding.client.common.ui.ClientImages;

/**
 * This view shows a list of all analysis which are opened and the tree of files
 * below the analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisProjectsView extends ViewPart implements
		IJobChangeListener, ISelectionProvider, SelectionListener {

	private static final ILog logger = Activator.getDefault().getLog();

	private final ImageRegistry imageRegistry = Activator.getDefault()
			.getImageRegistry();
	private final Image databaseRefreshImage = imageRegistry
			.get(ClientImages.DATABASE_REFRESH_16x16);
	private final Image analysisAddImage = imageRegistry
			.get(ClientImages.ANALYSIS_ADD_16x16);
	private final Image analysisEditImage = imageRegistry
			.get(ClientImages.ANALYSIS_EDIT_16x16);
	private final Image analysisDeleteImage = imageRegistry
			.get(ClientImages.ANALYSIS_DELETE_16x16);
	private final AnalysisStore store = AnalysisStoreFactory.getFactory()
			.getInstance();

	public AnalysisProjectsView() {
		super();
	}

	private Table analysisProjectsTable;
	private TableViewer analysisProjectsViewer;
	private ISelection selection = null;
	private ToolItem refresh;
	private ToolItem addAnalysis;
	private ToolItem editAnalysis;
	private ToolItem deleteAnalysis;

	private final java.util.List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		analysisProjectsTable = new Table(parent, SWT.BORDER);
		FormData fd_analyzesList = new FormData();
		fd_analyzesList.bottom = new FormAttachment(100);
		fd_analyzesList.left = new FormAttachment(0);
		analysisProjectsTable.setLayoutData(fd_analyzesList);
		analysisProjectsTable.setHeaderVisible(true);
		analysisProjectsTable.setLinesVisible(true);
		analysisProjectsViewer = new AnalysisProjectsTableViewer(
				analysisProjectsTable);

		ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
		fd_analyzesList.top = new FormAttachment(toolBar, 6);
		fd_analyzesList.right = new FormAttachment(toolBar, 0, SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.left = new FormAttachment(0);
		fd_toolBar.right = new FormAttachment(100);
		fd_toolBar.bottom = new FormAttachment(0, 24);
		fd_toolBar.top = new FormAttachment(0);
		toolBar.setLayoutData(fd_toolBar);
		toolBar.setToolTipText("Refreshs the list of available analyzes.");

		refresh = new ToolItem(toolBar, SWT.NONE);
		refresh.setText("Refresh");
		analysisProjectsTable.addSelectionListener(this);

		updateAnalysisList();
		refresh.addSelectionListener(this);
		refresh.setImage(databaseRefreshImage);

		addAnalysis = new ToolItem(toolBar, SWT.NONE);
		addAnalysis.setText("Add...");
		addAnalysis.setImage(analysisAddImage);
		addAnalysis.addSelectionListener(this);

		editAnalysis = new ToolItem(toolBar, SWT.NONE);
		editAnalysis.setText("Edit...");
		editAnalysis.setImage(analysisEditImage);
		editAnalysis.addSelectionListener(this);

		deleteAnalysis = new ToolItem(toolBar, SWT.NONE);
		deleteAnalysis.setText("Delete...");
		deleteAnalysis.setImage(analysisDeleteImage);
		deleteAnalysis.addSelectionListener(this);

		Job.getJobManager().addJobChangeListener(this);
		getSite().setSelectionProvider(this);
		setContentDescription("All available analyzes from analysis store");
	}

	@Override
	public void setFocus() {
		analysisProjectsTable.setFocus();
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
		if (job.getClass().equals(AnalysisJob.class)) {
			updateAnalysisList();
		}
	}

	private void updateAnalysisList() {
		UIJob uiJob = new UIJob("Update Analysis Projects") {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				refreshAnalysisProjectList();
				return Status.OK_STATUS;
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
	public void widgetSelected(SelectionEvent event) {
		if (event.getSource() == analysisProjectsTable) {
			processAnalysisProjectSelection();
		} else if (event.getSource() == refresh) {
			refreshAnalysisProjectList();
		} else if (event.getSource() == addAnalysis) {
			addAnalysisProject();
		} else if (event.getSource() == editAnalysis) {
			editAnalysisProject();
		} else if (event.getSource() == deleteAnalysis) {
			deleteAnalysisProject();
		}
	}

	private void editAnalysisProject() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}

	private void addAnalysisProject() {
		try {
			IHandlerService handlerService = (IHandlerService) getSite()
					.getService(IHandlerService.class);
			handlerService.executeCommand(
					NewAnalysisProjectHandler.class.getName(), null);
		} catch (ExecutionException e) {
			logger.log(new Status(Status.ERROR, AnalysisProjectsView.class
					.getName(), "Could not run new analysis!", e));
		} catch (NotDefinedException e) {
			logger.log(new Status(Status.ERROR, AnalysisProjectsView.class
					.getName(), "Could not run new analysis!", e));
		} catch (NotEnabledException e) {
			logger.log(new Status(Status.ERROR, AnalysisProjectsView.class
					.getName(), "Could not run new analysis!", e));
		} catch (NotHandledException e) {
			logger.log(new Status(Status.ERROR, AnalysisProjectsView.class
					.getName(), "Could not run new analysis!", e));
		}
	}

	private void deleteAnalysisProject() {
		try {
			StructuredSelection selection = (StructuredSelection) analysisProjectsViewer
					.getSelection();
			if (MessageDialog
					.openQuestion(getSite().getShell(), "Delete?",
							"Do you really want to delete analysis the selected analysis project(s)?")) {
				Iterator<?> iterator = selection.iterator();
				while (iterator.hasNext()) {
					AnalysisProjectInformation information = (AnalysisProjectInformation) iterator
							.next();
					store.removeAnalysis(information.getUUID());
					refreshAnalysisProjectList();
				}
			}
		} catch (AnalysisStoreException e) {
			logger.log(new Status(Status.ERROR, AnalysisProjectsView.class
					.getName(),
					"Could not retrieve analysis from analysis store!", e));
		}
	}

	private void refreshAnalysisProjectList() {
		try {
			if (!analysisProjectsTable.isDisposed()) {
				analysisProjectsViewer.setInput(store.getAnalysisProjects());
			}
		} catch (AnalysisStoreException e) {
			logger.log(new Status(Status.ERROR, AnalysisProjectsView.class
					.getName(),
					"Could not retrieve list of analyzes from analysis store!",
					e));
		}
	}

	private void processAnalysisProjectSelection() {
		StructuredSelection selection = (StructuredSelection) analysisProjectsViewer
				.getSelection();
		AnalysisProject analysisProject = (AnalysisProject) selection
				.getFirstElement();
		setSelection(new AnalysisProjectSelection(analysisProject));
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// intentionally left blank.
	}
}
