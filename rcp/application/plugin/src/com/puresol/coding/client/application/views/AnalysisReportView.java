package com.puresol.coding.client.application.views;

import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.AnalysisProject;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisStoreException;
import com.puresol.coding.analysis.api.AnalyzedCode;
import com.puresol.coding.client.application.Activator;
import com.puresol.coding.client.application.controls.ParserTreeControl;

public class AnalysisReportView extends ViewPart implements ISelectionListener {

	private static final ILog logger = Activator.getDefault().getLog();

	private Text name;
	private Text numAnalyzedFiles;
	private Text numFailedFiles;
	private Table table;

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

		Group grpAnalyzedFiles = new Group(composite, SWT.NONE);
		FormData fd_grpAnalyzedFiles = new FormData();
		fd_grpAnalyzedFiles.top = new FormAttachment(name, 6);
		fd_grpAnalyzedFiles.left = new FormAttachment(name, 0, SWT.LEFT);
		fd_grpAnalyzedFiles.right = new FormAttachment(100, -301);
		grpAnalyzedFiles.setLayoutData(fd_grpAnalyzedFiles);
		grpAnalyzedFiles.setText("Analyzed Files");
		grpAnalyzedFiles.setLayout(new RowLayout(SWT.HORIZONTAL));

		numAnalyzedFiles = new Text(grpAnalyzedFiles, SWT.BORDER);
		numAnalyzedFiles.setLayoutData(new RowData(50, 20));
		numAnalyzedFiles.setEditable(false);

		Label lblAnalyzedFiles = new Label(grpAnalyzedFiles, SWT.NONE);
		lblAnalyzedFiles.setText("Files");

		Group grpFailedFiles = new Group(composite, SWT.NONE);
		FormData fd_grpFailedFiles = new FormData();
		fd_grpFailedFiles.left = new FormAttachment(grpAnalyzedFiles, 6);
		fd_grpFailedFiles.right = new FormAttachment(100, -10);
		fd_grpFailedFiles.top = new FormAttachment(name, 6);
		grpFailedFiles.setLayoutData(fd_grpFailedFiles);
		grpFailedFiles.setText("Failed Files");
		grpFailedFiles.setLayout(new RowLayout(SWT.HORIZONTAL));

		numFailedFiles = new Text(grpFailedFiles, SWT.BORDER);
		numFailedFiles.setEditable(false);
		numFailedFiles.setLayoutData(new RowData(50, 20));

		Label lblFiles = new Label(grpFailedFiles, SWT.NONE);
		lblFiles.setText("Files");

		Group grpAnalyzedFiles_1 = new Group(composite, SWT.NONE);
		grpAnalyzedFiles_1.setText("Analyzed Files");
		grpAnalyzedFiles_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_grpAnalyzedFiles_1 = new FormData();
		fd_grpAnalyzedFiles_1.top = new FormAttachment(grpAnalyzedFiles, 6);
		fd_grpAnalyzedFiles_1.bottom = new FormAttachment(100, -10);
		fd_grpAnalyzedFiles_1.left = new FormAttachment(0, 10);
		fd_grpAnalyzedFiles_1.right = new FormAttachment(100, -10);
		grpAnalyzedFiles_1.setLayoutData(fd_grpAnalyzedFiles_1);

		table = new Table(grpAnalyzedFiles_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

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
			if (selection instanceof AnalysisProjectSelection) {
				AnalysisProjectSelection analysisSelection = (AnalysisProjectSelection) selection;
				AnalysisProject analysis = analysisSelection
						.getAnalysisProject();
				name.setText(analysis.getSettings().getName());
				AnalysisRun lastAnalysisRun = analysis.loadLastAnalysisRun();
				if (lastAnalysisRun != null) {
					java.util.List<AnalyzedCode> analyzedFiles = lastAnalysisRun
							.getAnalyzedCodes();
					numAnalyzedFiles.setText(String.valueOf(analyzedFiles
							.size()));
					List<AnalyzedCode> failedFiles = lastAnalysisRun
							.getFailedCodes();
					numFailedFiles.setText(String.valueOf(String
							.valueOf(failedFiles.size())));
				}
			} else if (selection instanceof AnalysisRunSelection) {
				AnalysisRunSelection analysisRunSelection = (AnalysisRunSelection) selection;
				AnalysisRun analysisRun = analysisRunSelection.getAnalysisRun();
				name.setText(analysisRun.getInformation().getAnalysisProject()
						.getSettings().getName());
				if (analysisRun != null) {
					java.util.List<AnalyzedCode> analyzedFiles = analysisRun
							.getAnalyzedCodes();
					numAnalyzedFiles.setText(String.valueOf(analyzedFiles
							.size()));
					List<AnalyzedCode> failedFiles = analysisRun
							.getFailedCodes();
					numFailedFiles.setText(String.valueOf(String
							.valueOf(failedFiles.size())));
				}
			}
		} catch (AnalysisStoreException e) {
			logger.log(new Status(Status.ERROR, ParserTreeControl.class
					.getName(), "Can not read analysis store!", e));
		}
	}
}
