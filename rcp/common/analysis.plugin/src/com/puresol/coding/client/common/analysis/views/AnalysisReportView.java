package com.puresol.coding.client.common.analysis.views;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.client.common.analysis.Activator;
import com.puresol.coding.client.common.analysis.contents.AnalyzedFilesTableViewer;
import com.puresol.coding.client.common.analysis.contents.FailedFilesTableViewer;
import com.puresol.coding.client.common.analysis.controls.ParserTreeControl;
import com.puresol.coding.client.common.branding.Printable;

public class AnalysisReportView extends ViewPart implements ISelectionListener,
		Printable {

	private static final ILog logger = Activator.getDefault().getLog();

	private Text name;
	private Table analyzedTable;
	private AnalyzedFilesTableViewer analyzedTableViewer;
	private FailedFilesTableViewer failedTableViewer;
	private Table failedTable;

	public AnalysisReportView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		composite.setLayout(new FormLayout());

		name = new Text(composite, SWT.BORDER | SWT.CENTER);
		FormData fd_text = new FormData();
		fd_text.left = new FormAttachment(0, 10);
		fd_text.right = new FormAttachment(100, -10);
		fd_text.top = new FormAttachment(0, 10);
		name.setLayoutData(fd_text);
		name.setEditable(false);

		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.top = new FormAttachment(name, 6);
		fd_tabFolder.bottom = new FormAttachment(100, -10);
		fd_tabFolder.right = new FormAttachment(name, 0, SWT.RIGHT);
		fd_tabFolder.left = new FormAttachment(name, 0, SWT.LEFT);
		tabFolder.setLayoutData(fd_tabFolder);

		TabItem analyzedTab = new TabItem(tabFolder, SWT.NONE);
		analyzedTab.setText("Analyzed Files");

		Composite analyzedComposite = new Composite(tabFolder, SWT.NONE);
		analyzedTab.setControl(analyzedComposite);
		analyzedComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		analyzedTable = new Table(analyzedComposite, SWT.BORDER
				| SWT.FULL_SELECTION);
		analyzedTable.setHeaderVisible(true);
		analyzedTable.setLinesVisible(true);
		analyzedTableViewer = new AnalyzedFilesTableViewer(analyzedTable);

		TabItem failedTab = new TabItem(tabFolder, SWT.NONE);
		failedTab.setText("Files without Analysis");

		Composite failedTabComposite = new Composite(tabFolder, SWT.NONE);
		failedTab.setControl(failedTabComposite);
		failedTabComposite.setLayout(new FillLayout(SWT.HORIZONTAL));

		failedTable = new Table(failedTabComposite, SWT.BORDER
				| SWT.FULL_SELECTION);
		failedTable.setHeaderVisible(true);
		failedTable.setLinesVisible(true);
		failedTableViewer = new FailedFilesTableViewer(failedTable);

		getSite().getWorkbenchWindow().getSelectionService()
				.addSelectionListener(this);
	}

	@Override
	public void setFocus() {
		name.setFocus();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		try {
			AnalysisRun analysisRun = null;
			AnalysisProject analysis = null;
			if (selection instanceof AnalysisProjectSelection) {
				AnalysisProjectSelection analysisSelection = (AnalysisProjectSelection) selection;
				analysis = analysisSelection.getAnalysisProject();
				analysisRun = analysis.loadLastAnalysisRun();
			} else if (selection instanceof AnalysisRunSelection) {
				AnalysisRunSelection analysisRunSelection = (AnalysisRunSelection) selection;
				analysisRun = analysisRunSelection.getAnalysisRun();
				analysis = analysisRun.getInformation().getAnalysisProject();
			}
			if (analysis != null) {
				name.setText(analysis.getSettings().getName());
				if (analysisRun != null) {
					analyzedTableViewer
							.setInput(analysisRun.getAnalyzedCodes());
					failedTableViewer.setInput(analysisRun.getFailedCodes());
				}
			}
		} catch (AnalysisStoreException e) {
			logger.log(new Status(Status.ERROR, ParserTreeControl.class
					.getName(), "Can not read analysis store!", e));
		}
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}
}
