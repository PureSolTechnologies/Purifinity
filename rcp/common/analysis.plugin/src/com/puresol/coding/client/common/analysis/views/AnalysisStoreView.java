package com.puresol.coding.client.common.analysis.views;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisProjectInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunInformation;
import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalysisStoreFactory;
import com.puresol.coding.client.common.analysis.Activator;
import com.puresol.coding.client.common.analysis.contents.AnalysisListContentProvider;
import com.puresol.coding.client.common.analysis.contents.AnalysisListLabelProvider;
import com.puresol.coding.client.common.analysis.contents.AnalysisRunListContentProvider;
import com.puresol.coding.client.common.analysis.contents.AnalysisRunListLabelProvider;

/**
 * This view is a view into the analysis store. All analyzes which are available
 * are shown and for each the analysis runs.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalysisStoreView extends ViewPart implements SelectionListener {

	private static final ILog logger = Activator.getDefault().getLog();

	private final AnalysisStore analysisStore = AnalysisStoreFactory
			.getFactory().getInstance();

	private Text runDescription;
	private List analysisList;
	private ListViewer analysisViewer;
	private List analysisRunList;
	private ListViewer analysisRunViewer;
	private Button refresh;
	private Label runName;
	private Tree parserTree;

	private AnalysisProject selectedAnalysis = null;
	private AnalysisRun selectedAnalysisRun = null;

	public AnalysisStoreView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());

		analysisList = new List(parent, SWT.BORDER);
		FormData fdAnalysis = new FormData();
		fdAnalysis.left = new FormAttachment(0, 10);
		fdAnalysis.right = new FormAttachment(0, 160);
		fdAnalysis.top = new FormAttachment(0, 10);
		fdAnalysis.bottom = new FormAttachment(100, -10);
		analysisList.setLayoutData(fdAnalysis);

		analysisRunList = new List(parent, SWT.BORDER);
		FormData fdAnalysisRun = new FormData();
		fdAnalysisRun.left = new FormAttachment(analysisList, 6);
		fdAnalysisRun.right = new FormAttachment(analysisList, 156, SWT.RIGHT);
		fdAnalysisRun.top = new FormAttachment(0, 10);
		fdAnalysisRun.bottom = new FormAttachment(100, -10);
		analysisRunList.setLayoutData(fdAnalysisRun);

		Composite runComposite = new Composite(parent, SWT.NONE);
		runComposite.setLayout(new FormLayout());
		FormData fdComposite = new FormData();
		fdComposite.left = new FormAttachment(0, 322);
		fdComposite.top = new FormAttachment(0, 10);
		fdComposite.bottom = new FormAttachment(100, -10);
		runComposite.setLayoutData(fdComposite);

		runName = new Label(runComposite, SWT.NONE);
		FormData fdRunName = new FormData();
		fdRunName.right = new FormAttachment(0, 252);
		fdRunName.top = new FormAttachment(0, 10);
		fdRunName.left = new FormAttachment(0, 10);
		runName.setLayoutData(fdRunName);
		runName.setText("New Label");

		runDescription = new Text(runComposite, SWT.BORDER | SWT.MULTI);
		FormData fdRunDescription = new FormData();
		fdRunDescription.left = new FormAttachment(runName, 0, SWT.LEFT);
		fdRunDescription.bottom = new FormAttachment(runName, 66, SWT.BOTTOM);
		fdRunDescription.top = new FormAttachment(runName, 6);
		fdRunDescription.right = new FormAttachment(100, -10);
		runDescription.setLayoutData(fdRunDescription);

		parserTree = new Tree(runComposite, SWT.BORDER);
		FormData fd_parserTree = new FormData();
		fd_parserTree.top = new FormAttachment(runDescription, 6);
		fd_parserTree.right = new FormAttachment(runDescription, 0, SWT.RIGHT);
		fd_parserTree.bottom = new FormAttachment(runDescription, 354,
				SWT.BOTTOM);
		fd_parserTree.left = new FormAttachment(runName, 0, SWT.LEFT);
		parserTree.setLayoutData(fd_parserTree);

		refresh = new Button(parent, SWT.NONE);
		fdComposite.right = new FormAttachment(refresh, -6);
		FormData fdRefreshButton = new FormData();
		fdRefreshButton.top = new FormAttachment(0, 10);
		fdRefreshButton.right = new FormAttachment(100, -10);
		refresh.setLayoutData(fdRefreshButton);
		refresh.setText("Refresh");

		analysisViewer = new ListViewer(analysisList);
		analysisViewer.setContentProvider(new AnalysisListContentProvider());
		analysisViewer.setLabelProvider(new AnalysisListLabelProvider());

		analysisRunViewer = new ListViewer(analysisRunList);
		analysisRunViewer
				.setContentProvider(new AnalysisRunListContentProvider());
		analysisRunViewer.setLabelProvider(new AnalysisRunListLabelProvider());

		updateAnalysisList();
		analysisList.addSelectionListener(this);
		analysisRunList.addSelectionListener(this);
		refresh.addSelectionListener(this);
	}

	@Override
	public void setFocus() {
		analysisList.setFocus();
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		if (event.getSource() == refresh) {
			updateAnalysisList();
		} else if (event.getSource() == analysisList) {
			StructuredSelection selection = (StructuredSelection) analysisViewer
					.getSelection();
			AnalysisProjectInformation analysisInformation = (AnalysisProjectInformation) selection
					.getFirstElement();
			updateAnalysisRunList(analysisInformation);
		} else if (event.getSource() == analysisRunList) {
			StructuredSelection analysisSelection = (StructuredSelection) analysisViewer
					.getSelection();
			AnalysisProjectInformation analysisInformation = (AnalysisProjectInformation) analysisSelection
					.getFirstElement();
			StructuredSelection analysisRunSelection = (StructuredSelection) analysisRunViewer
					.getSelection();
			AnalysisRunInformation analysisRunInformation = (AnalysisRunInformation) analysisRunSelection
					.getFirstElement();
			updateRunInformation(analysisRunInformation);
		}
	}

	private void updateAnalysisList() {
		try {
			selectedAnalysis = null;
			updateAnalysisRunList(null);
			analysisViewer.setInput(analysisStore.getAnalysisProjects());
		} catch (AnalysisStoreException e) {
			logger.log(new Status(
					Status.ERROR,
					AnalysisProjectsView.class.getName(),
					"Could not retrieve available analyzes from analysis store!",
					e));
		}
	}

	private void updateAnalysisRunList(AnalysisProjectInformation information) {
		try {
			updateRunInformation(null);
			if (information != null) {
				selectedAnalysis = analysisStore.loadAnalysis(information
						.getUUID());
				selectedAnalysisRun = null;
				AnalysisProject analysis = analysisStore
						.loadAnalysis(information.getUUID());
				analysisRunViewer.setInput(analysis.getAllRunInformation());
			} else {
				selectedAnalysis = null;
				analysisRunList.removeAll();
			}
		} catch (AnalysisStoreException e) {
			logger.log(new Status(
					Status.ERROR,
					AnalysisProjectsView.class.getName(),
					"Could not retrieve available analysis runs from analysis store!",
					e));
		}
	}

	private void updateRunInformation(
			AnalysisRunInformation analysisRunInformation) {
		try {
			if (analysisRunInformation != null) {
				selectedAnalysisRun = selectedAnalysis
						.loadAnalysisRun(analysisRunInformation.getUUID());
				runName.setText(selectedAnalysis.getSettings().getName());
				runDescription.setText(analysisRunInformation.getDescription());
			} else {
				selectedAnalysisRun = null;
				runName.setText("");
				runDescription.setText("");
			}
		} catch (AnalysisStoreException e) {
			logger.log(new Status(
					Status.ERROR,
					AnalysisProjectsView.class.getName(),
					"Could not retrieve available analysis runs from analysis store!",
					e));
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}
}
