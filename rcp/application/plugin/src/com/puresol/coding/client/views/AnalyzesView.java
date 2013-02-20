package com.puresol.coding.client.views;

import java.util.ArrayList;

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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.UIJob;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.ClientImages;
import com.puresol.coding.client.commands.NewAnalysis;
import com.puresol.coding.client.content.AnalysisListContentProvider;
import com.puresol.coding.client.content.AnalysisListLabelProvider;
import com.puresol.coding.client.jobs.NewAnalysisJob;

/**
 * This view shows a list of all analysis which are opened and the tree of files
 * below the analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyzesView extends ViewPart implements IJobChangeListener,
		ISelectionProvider, SelectionListener {

	private static final ILog logger = Activator.getDefault().getLog();

	public static final String ID = "com.puresol.coding.client.AnalyzesView";

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

	public AnalyzesView() {
		super();
	}

	private Table analyzesList;
	private TableViewer analyzesViewer;
	private ISelection selection = null;
	private ToolItem refresh;
	private ToolItem addAnalysis;
	private ToolItem editAnalysis;
	private ToolItem deleteAnalysis;

	private final java.util.List<ISelectionChangedListener> listeners = new ArrayList<ISelectionChangedListener>();

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new BorderLayout(0, 0));

		analyzesList = new Table(parent, SWT.BORDER);
		analyzesViewer = new TableViewer(analyzesList);

		ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.RIGHT);
		toolBar.setToolTipText("Refreshs the list of available analyzes.");
		toolBar.setLayoutData(BorderLayout.NORTH);

		refresh = new ToolItem(toolBar, SWT.NONE);
		refresh.setText("Refresh");
		analyzesViewer.setContentProvider(new AnalysisListContentProvider());
		analyzesViewer.setLabelProvider(new AnalysisListLabelProvider());
		analyzesList.redraw();
		analyzesViewer.refresh();
		analyzesList.addSelectionListener(this);

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
				refreshAnalysisList();
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
		if (event.getSource() == analyzesList) {
			processAnalysisSelection();
		} else if (event.getSource() == refresh) {
			refreshAnalysisList();
		} else if (event.getSource() == addAnalysis) {
			addAnalysis();
		} else if (event.getSource() == editAnalysis) {
			editAnalysis();
		} else if (event.getSource() == deleteAnalysis) {
			deleteAnalysis();
		}
	}

	private void editAnalysis() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}

	private void addAnalysis() {
		try {
			IHandlerService handlerService = (IHandlerService) getSite()
					.getService(IHandlerService.class);
			handlerService.executeCommand(NewAnalysis.ID, null);
		} catch (ExecutionException e) {
			logger.log(new Status(Status.ERROR, AnalyzesView.class.getName(),
					"Could not run new analysis!", e));
		} catch (NotDefinedException e) {
			logger.log(new Status(Status.ERROR, AnalyzesView.class.getName(),
					"Could not run new analysis!", e));
		} catch (NotEnabledException e) {
			logger.log(new Status(Status.ERROR, AnalyzesView.class.getName(),
					"Could not run new analysis!", e));
		} catch (NotHandledException e) {
			logger.log(new Status(Status.ERROR, AnalyzesView.class.getName(),
					"Could not run new analysis!", e));
		}
	}

	private void deleteAnalysis() {
		try {
			StructuredSelection selection = (StructuredSelection) analyzesViewer
					.getSelection();
			AnalysisInformation information = (AnalysisInformation) selection
					.getFirstElement();
			if (MessageDialog.openQuestion(
					getSite().getShell(),
					"Delete?",
					"Do you really want to delete analysis '"
							+ information.getName() + "'?")) {
				store.removeAnalysis(information.getUUID());
				refreshAnalysisList();
			}
		} catch (ModuleStoreException e) {
			logger.log(new Status(Status.ERROR, AnalyzesView.class.getName(),
					"Could not retrieve analysis from analysis store!", e));
		}
	}

	private void refreshAnalysisList() {
		try {
			if (!analyzesList.isDisposed()) {
				analyzesViewer.setInput(store.getAllAnalysisInformation());
			}
		} catch (ModuleStoreException e) {
			logger.log(new Status(Status.ERROR, AnalyzesView.class.getName(),
					"Could not retrieve list of analyzes from analysis store!",
					e));
		}
	}

	private void processAnalysisSelection() {
		try {
			StructuredSelection selection = (StructuredSelection) analyzesViewer
					.getSelection();
			AnalysisInformation information = (AnalysisInformation) selection
					.getFirstElement();
			setSelection(new AnalysisSelection(store.loadAnalysis(information
					.getUUID())));
		} catch (ModuleStoreException e) {
			logger.log(new Status(Status.ERROR, AnalyzesView.class.getName(),
					"Could not retrieve analysis from analysis store!", e));
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// intentionally left blank.
	}
}
