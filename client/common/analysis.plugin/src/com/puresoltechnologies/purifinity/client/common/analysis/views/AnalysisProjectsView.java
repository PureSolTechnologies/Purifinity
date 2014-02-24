package com.puresoltechnologies.purifinity.client.common.analysis.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
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
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.progress.UIJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.client.common.analysis.Activator;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.AnalysisProjectsTableViewer;
import com.puresoltechnologies.purifinity.client.common.analysis.handlers.NewAnalysisProjectHandler;
import com.puresoltechnologies.purifinity.client.common.analysis.jobs.AnalysisJob;
import com.puresoltechnologies.purifinity.client.common.branding.ClientImages;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.Refreshable;
import com.puresoltechnologies.purifinity.client.common.ui.parts.DatabaseTarget;
import com.puresoltechnologies.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;
import com.puresoltechnologies.purifinity.framework.analysis.impl.AnalysisProjectImpl;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreFactory;

/**
 * This view shows a list of all analysis which are opened and the tree of files
 * below the analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisProjectsView extends AbstractPureSolTechnologiesView
		implements IJobChangeListener, ISelectionProvider, SelectionListener,
		Refreshable, DatabaseTarget {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalysisProjectsView.class);

	private Table projectsTable;
	private TableViewer analysisProjectsViewer;
	private AnalysisProjectSelection selection = null;
	private ToolItem addProject;
	private ToolItem editProject;
	private ToolItem deleteProject;
	private ToolItem unselectProject;

	private final java.util.List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

	private RefreshAction refreshAction = null;

	private boolean enabled = true;
	private boolean availableDatabase = true;

	public AnalysisProjectsView() {
		super(Activator.getDefault());
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		parent.setLayout(new FormLayout());
		projectsTable = new Table(parent, SWT.BORDER);
		projectsTable.addSelectionListener(this);
		FormData fd_analyzesList = new FormData();
		fd_analyzesList.bottom = new FormAttachment(100);
		fd_analyzesList.left = new FormAttachment(0);
		projectsTable.setLayoutData(fd_analyzesList);
		projectsTable.setHeaderVisible(true);
		projectsTable.setLinesVisible(true);
		analysisProjectsViewer = new AnalysisProjectsTableViewer(projectsTable);

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

		addProject = new ToolItem(toolBar, SWT.NONE);
		addProject.setText("Add...");
		addProject.setImage(ClientImages
				.getImage(ClientImages.ANALYSIS_ADD_16x16));
		addProject.addSelectionListener(this);

		editProject = new ToolItem(toolBar, SWT.NONE);
		editProject.setText("Edit...");
		editProject.setImage(ClientImages
				.getImage(ClientImages.ANALYSIS_EDIT_16x16));
		editProject.addSelectionListener(this);

		deleteProject = new ToolItem(toolBar, SWT.NONE);
		deleteProject.setText("Delete...");
		deleteProject.setImage(ClientImages
				.getImage(ClientImages.ANALYSIS_DELETE_16x16));
		deleteProject.addSelectionListener(this);

		unselectProject = new ToolItem(toolBar, SWT.NONE);
		unselectProject.setText("Unselect");
		unselectProject.addSelectionListener(this);

		Job.getJobManager().addJobChangeListener(this);
		getSite().setSelectionProvider(this);

		initializeToolBar();

		refresh();
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
		projectsTable.setFocus();
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
		Class<? extends Job> jobClass = job.getClass();
		if (jobClass.equals(AnalysisJob.class)) {
			refresh();
		}
	}

	@Override
	public void refresh() {
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
		AnalysisProjectSelection newSelection = (AnalysisProjectSelection) selection;
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
		if (event.getSource() == projectsTable) {
			processProjectSelection();
		} else if (event.getSource() == addProject) {
			addProject();
		} else if (event.getSource() == editProject) {
			editProject();
		} else if (event.getSource() == deleteProject) {
			deleteProject();
		} else if (event.getSource() == unselectProject) {
			unselectProject();
		}
	}

	private void addProject() {
		try {
			IHandlerService handlerService = (IHandlerService) getSite()
					.getService(IHandlerService.class);
			handlerService.executeCommand(
					NewAnalysisProjectHandler.class.getName(), null);
		} catch (ExecutionException | NotDefinedException | NotEnabledException
				| NotHandledException e) {
			logger.error("Could not run new analysis!", e);
		}
	}

	private void editProject() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}

	private void deleteProject() {
		final StructuredSelection selection = (StructuredSelection) analysisProjectsViewer
				.getSelection();
		if (!selection.isEmpty()) {
			if (MessageDialog
					.openQuestion(getSite().getShell(), "Delete?",
							"Do you really want to delete analysis the selected analysis project(s)?")) {
				Job job = new Job("Analysis Project Removal") {

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						try {
							Iterator<?> iterator = selection.iterator();
							while (iterator.hasNext()) {
								AnalysisProject information = (AnalysisProject) iterator
										.next();
								AnalysisStore store = AnalysisStoreFactory
										.getFactory().getInstance();
								if (store != null) {
									store.removeAnalysisProject(information
											.getInformation().getUUID());
									refresh();
								}
							}
							return Status.OK_STATUS;
						} catch (AnalysisStoreException e) {
							logger.error(
									"Could not retrieve analysis from analysis store!",
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

	private void unselectProject() {
		analysisProjectsViewer.setSelection(null);
		setSelection(new AnalysisProjectSelection(null));
	}

	private void refreshAnalysisProjectList() {
		try {
			List<AnalysisProject> analysisProjects = new ArrayList<>();
			AnalysisStore store = AnalysisStoreFactory.getFactory()
					.getInstance();
			if (store != null) {
				List<AnalysisProjectInformation> allAnalysisProjectInformation = store
						.readAllAnalysisProjectInformation();
				for (AnalysisProjectInformation information : allAnalysisProjectInformation) {
					AnalysisProjectSettings settings = store
							.readAnalysisProjectSettings(information.getUUID());
					AnalysisProjectImpl newItem = new AnalysisProjectImpl(
							information.getUUID(),
							information.getCreationTime(), settings);
					analysisProjects.add(newItem);
				}
			} else {
				logger.warn("No analysis store is available.");
				return;
			}
			analysisProjectsViewer.setInput(analysisProjects);
			processProjectSelection();
			updateEnabledState();
		} catch (AnalysisStoreException e) {
			logger.error(
					"Could not retrieve list of analyzes from analysis store!",
					e);
		}
	}

	private void processProjectSelection() {
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

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		updateEnabledState();
	}

	private void updateEnabledState() {
		projectsTable.setEnabled(enabled && availableDatabase);
		refreshAction.setEnabled(enabled && availableDatabase);
		addProject.setEnabled(enabled && availableDatabase);
		editProject.setEnabled(enabled && availableDatabase
				&& (selection != null)
				&& (selection.getAnalysisProject() != null));
		deleteProject.setEnabled(enabled && availableDatabase
				&& (selection != null)
				&& (selection.getAnalysisProject() != null));
	}

	@Override
	public void setDatabaseAvailable(boolean available) {
		availableDatabase = available;
		if (available) {
			refresh();
		}
		updateEnabledState();
	}
}
