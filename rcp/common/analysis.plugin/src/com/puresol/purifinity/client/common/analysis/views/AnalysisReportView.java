package com.puresol.purifinity.client.common.analysis.views;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.puresol.purifinity.client.common.analysis.Activator;
import com.puresol.purifinity.client.common.analysis.contents.AnalyzedFilesTableViewer;
import com.puresol.purifinity.client.common.analysis.contents.FailedFilesTableViewer;
import com.puresol.purifinity.client.common.analysis.controls.ParserTreeControl;
import com.puresol.purifinity.client.common.analysis.dialogs.AnalysisInformationDialog;
import com.puresol.purifinity.coding.analysis.api.AnalysisProject;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;

public class AnalysisReportView extends ViewPart implements ISelectionListener,
		IDoubleClickListener, CloseWindowListener {

	private static final ILog logger = Activator.getDefault().getLog();

	private Text name;
	private Table analyzedTable;
	private AnalyzedFilesTableViewer analyzedTableViewer;
	private FailedFilesTableViewer failedTableViewer;
	private Table failedTable;
	private Text totalFiles;
	private Text analyzedFiles;
	private Text unanalyzedFiles;
	private Text errorFiles;

	private AnalysisInformationDialog informationDialog;

	@Override
	public void dispose() {
		getSite().getWorkbenchWindow().getSelectionService()
				.removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(parent,
						"com.puresol.purifinity.client.common.analysis.plugin.analysisReportView");

		Composite composite = new Composite(parent, SWT.NONE);

		composite.setLayout(new FormLayout());

		name = new Text(composite, SWT.BORDER | SWT.CENTER);
		FormData fd_text = new FormData();
		fd_text.left = new FormAttachment(0, 10);
		fd_text.right = new FormAttachment(100, -10);
		fd_text.top = new FormAttachment(0, 10);
		name.setLayoutData(fd_text);
		name.setEditable(false);

		Composite numbers = new Composite(composite, SWT.BORDER);
		numbers.setLayout(new GridLayout(4, true));
		FormData fd_numbers = new FormData();
		fd_numbers.top = new FormAttachment(name, 10);
		fd_numbers.right = new FormAttachment(name, 0, SWT.RIGHT);
		fd_numbers.left = new FormAttachment(name, 0, SWT.LEFT);
		numbers.setLayoutData(fd_numbers);

		new Label(numbers, SWT.NONE).setText("#Total Files");
		new Label(numbers, SWT.NONE).setText("#Analyzed Files");
		new Label(numbers, SWT.NONE).setText("#Unanalyzed Files");
		new Label(numbers, SWT.NONE).setText("#Files with Error");

		totalFiles = new Text(numbers, SWT.BORDER | SWT.READ_ONLY);
		analyzedFiles = new Text(numbers, SWT.BORDER | SWT.READ_ONLY);
		unanalyzedFiles = new Text(numbers, SWT.BORDER | SWT.READ_ONLY);
		errorFiles = new Text(numbers, SWT.BORDER | SWT.READ_ONLY);

		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.top = new FormAttachment(numbers, 10);
		fd_tabFolder.bottom = new FormAttachment(100, -10);
		fd_tabFolder.right = new FormAttachment(numbers, 0, SWT.RIGHT);
		fd_tabFolder.left = new FormAttachment(numbers, 0, SWT.LEFT);
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
		analyzedTableViewer.addDoubleClickListener(this);

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
		failedTableViewer.addDoubleClickListener(this);

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
							.setInput(analysisRun.getAnalyzedFiles());
					failedTableViewer.setInput(analysisRun.getFailedFiles());
					int analyzedCodesSize = analysisRun.getAnalyzedFiles()
							.size();
					int failedCodesSize = analysisRun.getFailedFiles().size();
					totalFiles.setText(String.valueOf(analyzedCodesSize
							+ failedCodesSize));
					analyzedFiles.setText(String.valueOf(analyzedCodesSize));
					unanalyzedFiles.setText(String.valueOf(failedCodesSize));
					int errors = 0;
					for (AnalyzedCode analyzedCode : analysisRun
							.getFailedFiles()) {
						if (analyzedCode.wasError()) {
							errors++;
						}
					}
					errorFiles.setText(String.valueOf(errors));
				}
			}
		} catch (AnalysisStoreException e) {
			logger.log(new Status(Status.ERROR, ParserTreeControl.class
					.getName(), "Can not read analysis store!", e));
		}
	}

	@Override
	public void doubleClick(DoubleClickEvent event) {
		AnalyzedCode analyzedCode = null;
		if (event.getSource() == analyzedTableViewer) {
			analyzedCode = analyzedTableViewer.getSelectedAnalyzedCode();
		} else if (event.getSource() == failedTableViewer) {
			analyzedCode = failedTableViewer.getSelectedAnalyzedCode();
		}
		if (analyzedCode != null) {
			if (informationDialog == null) {
				informationDialog = new AnalysisInformationDialog(this,
						analyzedCode);
				informationDialog.addCloseListener(this);
				informationDialog.open();
			} else {
				informationDialog.setAnalyzedCode(analyzedCode);
			}

		}
	}

	@Override
	public void close(WindowEvent event) {
		informationDialog.close();
		informationDialog = null;
	}
}
