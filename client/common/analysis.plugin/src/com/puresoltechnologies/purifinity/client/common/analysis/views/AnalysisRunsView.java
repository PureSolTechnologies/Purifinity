package com.puresoltechnologies.purifinity.client.common.analysis.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.progress.UIJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.AnalysisRunListContentProvider;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.AnalysisRunListLabelProvider;
import com.puresoltechnologies.purifinity.client.common.analysis.jobs.AnalysisJob;
import com.puresoltechnologies.purifinity.client.common.branding.ClientImages;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.Refreshable;
import com.puresoltechnologies.purifinity.client.common.ui.parts.DatabaseTarget;
import com.puresoltechnologies.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.client.analysisservice.AnalysisStoreClient;

/**
 * This view contains a list of all analysis runs. Additionally, it allows the
 * triggering of new runs, editing of run (comments) and deleting them.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AnalysisRunsView extends AbstractPureSolTechnologiesView implements
		SelectionListener, ISelectionProvider, ISelectionListener,
		IJobChangeListener, Refreshable, DatabaseTarget {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisReportView.class);

	private Table analysisRunsTable;
	private TableViewer analysisRunsViewer;
	private ToolItem addAnalysisRun;
	private ToolItem editAnalysisRun;
	private ToolItem deleteAnalysisRun;
	private RefreshAction refreshAction;

	private AnalysisProject analysisProject = null;
	private AnalysisRunSelection selection = null;
	private boolean enabled = true;
	private boolean availableDatabase = true;

	private final java.util.List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

	public AnalysisRunsView() {
		super(Activator.getDefault());
	}

	@Override
	public void dispose() {
		Job.getJobManager().removeJobChangeListener(this);
		getSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		parent.setLayout(new FormLayout());

		analysisRunsTable = new Table(parent, SWT.BORDER | SWT.MULTI);
		analysisRunsTable.setHeaderVisible(false);
		FormData fd_analysisRunsTable = new FormData();
		fd_analysisRunsTable.bottom = new FormAttachment(100);
		fd_analysisRunsTable.left = new FormAttachment(0);
		analysisRunsTable.setLayoutData(fd_analysisRunsTable);
		analysisRunsTable
				.setToolTipText("Refreshs the analysis runs for the currently selected analysis from the analysis store.");
		analysisRunsTable.setLinesVisible(true);
		analysisRunsTable.setHeaderVisible(true);
		analysisRunsViewer = new TableViewer(analysisRunsTable);

		ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
		fd_analysisRunsTable.top = new FormAttachment(toolBar, 6);
		fd_analysisRunsTable.right = new FormAttachment(toolBar, 0, SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.left = new FormAttachment(0);
		fd_toolBar.right = new FormAttachment(100);
		fd_toolBar.bottom = new FormAttachment(0, 24);
		fd_toolBar.top = new FormAttachment(0);
		toolBar.setLayoutData(fd_toolBar);
		toolBar.setToolTipText("Refreshs the analysis runs for the currently selected analysis from the analysis store.");

		addAnalysisRun = new ToolItem(toolBar, SWT.NONE);
		addAnalysisRun.setText("Add...");
		addAnalysisRun.setImage(ClientImages
				.getImage(ClientImages.ANALYSIS_RUN_ADD_16x16));
		addAnalysisRun.addSelectionListener(this);

		editAnalysisRun = new ToolItem(toolBar, SWT.NONE);
		editAnalysisRun.setText("Edit...");
		editAnalysisRun.setImage(ClientImages
				.getImage(ClientImages.ANALYSIS_RUN_EDIT_16x16));
		editAnalysisRun.addSelectionListener(this);

		deleteAnalysisRun = new ToolItem(toolBar, SWT.NONE);
		deleteAnalysisRun.setText("Delete...");
		deleteAnalysisRun.setImage(ClientImages
				.getImage(ClientImages.ANALYSIS_RUN_DELETE_16x16));
		deleteAnalysisRun.addSelectionListener(this);

		analysisRunsViewer
				.setContentProvider(new AnalysisRunListContentProvider());
		analysisRunsViewer.setLabelProvider(new AnalysisRunListLabelProvider());
		analysisRunsTable.redraw();
		analysisRunsViewer.refresh();
		analysisRunsTable.addSelectionListener(this);

		getSite().setSelectionProvider(this);
		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this);
		Job.getJobManager().addJobChangeListener(this);

		initializeToolBar();
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		refreshAction = new RefreshAction(this);
		toolbarManager.add(refreshAction);
	}

	@Override
	public void setFocus() {
		analysisRunsTable.setFocus();
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
		AnalysisRunSelection newSelection = (AnalysisRunSelection) selection;
		if (!newSelection.equals(this.selection)) {
			this.selection = newSelection;
			updateEnabledState();
			for (ISelectionChangedListener listener : listeners) {
				listener.selectionChanged(new SelectionChangedEvent(this,
						getSelection()));
			}
		}
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		if (event.getSource() == analysisRunsTable) {
			processAnalysisRunSelection();
		} else if (event.getSource() == addAnalysisRun) {
			addAnalysisRun();
		} else if (event.getSource() == editAnalysisRun) {
			editAnalysisRun();
		} else if (event.getSource() == deleteAnalysisRun) {
			deleteAnalysisRun();
		}
	}

	private void addAnalysisRun() {
		AnalysisJob job = new AnalysisJob(analysisProject.getInformation(),
				analysisProject.getSettings());
		job.schedule();
	}

	private void editAnalysisRun() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}

	private void deleteAnalysisRun() {
		final StructuredSelection selection = (StructuredSelection) analysisRunsViewer
				.getSelection();
		if (!selection.isEmpty()) {
			if (MessageDialog
					.openQuestion(getSite().getShell(), "Delete?",
							"Do you really want to delete the selected analysis run(s)?")) {
				Job job = new Job("Analysis Project Removal") {

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						try {
							@SuppressWarnings("unchecked")
							Iterator<AnalysisRunInformation> iterator = selection
									.iterator();
							while (iterator.hasNext()) {
								AnalysisRunInformation analysisRun = iterator
										.next();
								AnalysisStoreClient analysisStore = AnalysisStoreClient
										.getInstance();
								analysisStore.removeAnalysisRun(
										analysisRun.getProjectUUID(),
										analysisRun.getRunUUID());
							}
							refresh();
							return Status.OK_STATUS;
						} catch (AnalysisStoreException e) {
							logger.error(
									"Could not remove analysis run from analysis store!",
									e);
							return new Status(Status.ERROR,
									Activator.getDefault().getBundle()
											.getSymbolicName(),
									"Could not delete projects.");
						}
					}
				};
				job.schedule();
			}
		}
	}

	private void refreshAnalysisRunList() {
		try {
			if (analysisProject != null) {
				AnalysisStoreClient analysisStore = AnalysisStoreClient
						.getInstance();
				List<AnalysisRunInformation> allRunInformation = analysisStore
						.readAllRunInformation(analysisProject.getInformation()
								.getUUID());
				analysisRunsViewer.setInput(allRunInformation);
			}
			processAnalysisRunSelection();
			updateEnabledState();
		} catch (AnalysisStoreException e) {
			logger.error("Can not read analysis runs from store!", e);
		}
	}

	private void processAnalysisRunSelection() {
		try {
			StructuredSelection selection = (StructuredSelection) analysisRunsViewer
					.getSelection();
			AnalysisRunInformation information = (AnalysisRunInformation) selection
					.getFirstElement();
			AnalysisRun analysisRun = null;
			if (information != null) {
				AnalysisStoreClient analysisStore = AnalysisStoreClient
						.getInstance();
				analysisRun = analysisStore.readAnalysisRun(
						information.getProjectUUID(), information.getRunUUID());
			}
			setSelection(new AnalysisRunSelection(analysisProject, analysisRun));
		} catch (AnalysisStoreException e) {
			logger.error("Can not read analysis runs from store!", e);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// intentionally left blank.
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part == this) {
			return;
		}
		if (selection instanceof AnalysisProjectSelection) {
			processAnalysisProjectSelection((AnalysisProjectSelection) selection);
		}
	}

	private void processAnalysisProjectSelection(
			AnalysisProjectSelection newSelection) {
		try {
			AnalysisProject newAnalysisProject = newSelection
					.getAnalysisProject();
			if (hasSelectionChanged(newAnalysisProject)) {
				analysisProject = newAnalysisProject;
				if (analysisProject != null) {
					AnalysisStoreClient analysisStore = AnalysisStoreClient
							.getInstance();
					analysisRunsViewer.setInput(analysisStore
							.readAllRunInformation(analysisProject
									.getInformation().getUUID()));
					setSelection(new AnalysisRunSelection(analysisProject, null));
				} else {
					analysisRunsViewer.setInput(null);
					setSelection(new AnalysisRunSelection(null, null));
				}
			}
		} catch (AnalysisStoreException e) {
			logger.error("Can not read analysis store!", e);
		}
	}

	private boolean hasSelectionChanged(AnalysisProject newAnalysisProject) {
		if ((analysisProject == null) && (newAnalysisProject == null)) {
			return false;
		}
		if (((analysisProject == null) && (newAnalysisProject != null))
				|| ((analysisProject != null) && (newAnalysisProject == null))) {
			return true;
		}
		if (!analysisProject.getInformation().getUUID()
				.equals(newAnalysisProject.getInformation().getUUID())) {
			return true;
		}
		return false;
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
			refresh();
		}
	}

	@Override
	public void refresh() {
		UIJob uiJob = new UIJob("Update Analysis Runs") {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				refreshAnalysisRunList();
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

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		updateEnabledState();
	}

	@Override
	public void setDatabaseAvailable(boolean available) {
		availableDatabase = available;
		updateEnabledState();
	}

	private void updateEnabledState() {
		analysisRunsTable.setEnabled(enabled && availableDatabase
				&& (analysisProject != null));
		refreshAction.setEnabled(enabled && availableDatabase
				&& (analysisProject != null));
		addAnalysisRun.setEnabled(enabled && availableDatabase
				&& (analysisProject != null));
		editAnalysisRun.setEnabled(enabled && availableDatabase
				&& (analysisProject != null) && (selection != null)
				&& (selection.getAnalysisRun() != null));
		deleteAnalysisRun.setEnabled(enabled && availableDatabase
				&& (analysisProject != null) && (selection != null)
				&& (selection.getAnalysisRun() != null));
	}
}
