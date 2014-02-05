package com.puresoltechnologies.purifinity.client.common.analysis.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.puresoltechnologies.purifinity.client.common.ui.parts.DatabaseUserInterface;
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
		Refreshable, DatabaseUserInterface {

	private static final ILog logger = Activator.getDefault().getLog();

	private Table analysisProjectsTable;
	private TableViewer analysisProjectsViewer;
	private ISelection selection = null;
	private ToolItem addProject;
	private ToolItem editProject;
	private ToolItem deleteProject;

	private final java.util.List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

	private Composite parent = null;
	private RefreshAction refreshAction = null;

	private boolean enabled = true;
	private boolean availableDatabase = true;

	public AnalysisProjectsView() {
		super(Activator.getDefault());
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		this.parent = parent;
		parent.setLayout(new FormLayout());
		analysisProjectsTable = new Table(parent, SWT.BORDER);
		analysisProjectsTable.addSelectionListener(this);
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
		if (parent != null) {
			parent.setFocus();
		}
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
		updateEnabledState();
		for (ISelectionChangedListener listener : listeners) {
			listener.selectionChanged(new SelectionChangedEvent(this,
					getSelection()));
		}
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		if (event.getSource() == analysisProjectsTable) {
			processAnalysisProjectSelection();
		} else if (event.getSource() == addProject) {
			addAnalysisProject();
		} else if (event.getSource() == editProject) {
			editAnalysisProject();
		} else if (event.getSource() == deleteProject) {
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
		} catch (ExecutionException | NotDefinedException | NotEnabledException
				| NotHandledException e) {
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
					AnalysisProject information = (AnalysisProject) iterator
							.next();
					AnalysisStore store = AnalysisStoreFactory.getFactory()
							.getInstance();
					if (store != null) {
						store.removeAnalysisProject(information
								.getInformation().getUUID());
						refreshAnalysisProjectList();
					}
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
				AnalysisStore store = AnalysisStoreFactory.getFactory()
						.getInstance();
				if (store != null) {
					List<AnalysisProjectInformation> allAnalysisProjectInformation = store
							.readAllAnalysisProjectInformation();
					List<AnalysisProject> analysisProjects = new ArrayList<>();
					for (AnalysisProjectInformation information : allAnalysisProjectInformation) {
						AnalysisProjectSettings settings = store
								.readAnalysisProjectSettings(information
										.getUUID());
						analysisProjects.add(new AnalysisProjectImpl(
								information.getUUID(), information
										.getCreationTime(), settings));
					}
					analysisProjectsViewer.setInput(analysisProjects);
				}
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

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		updateEnabledState();
	}

	private void updateEnabledState() {
		analysisProjectsTable.setEnabled(enabled && availableDatabase);
		refreshAction.setEnabled(enabled && availableDatabase);
		addProject.setEnabled(enabled && availableDatabase);
		editProject.setEnabled(enabled && availableDatabase
				&& (selection != null));
		deleteProject.setEnabled(enabled && availableDatabase
				&& (selection != null));
	}

	@Override
	public void setDatabaseAvailable(boolean available) {
		availableDatabase = available;
		updateEnabledState();
	}
}
