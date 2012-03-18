package com.puresol.coding.client.views;

import java.io.File;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;

public class AnalysisReport extends ViewPart implements ISelectionListener {
    private Text text;
    private Text text_1;
    private Text text_2;

    public AnalysisReport() {
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FormLayout());

	text = new Text(parent, SWT.BORDER | SWT.CENTER);
	FormData fd_text = new FormData();
	fd_text.right = new FormAttachment(100, -10);
	fd_text.top = new FormAttachment(0, 10);
	fd_text.left = new FormAttachment(0, 10);
	text.setLayoutData(fd_text);
	text.setEditable(false);
	text.setFont(SWTResourceManager.getFont("Sans", 16, SWT.NORMAL));

	Group grpAnalyzedFiles = new Group(parent, SWT.NONE);
	FormData fd_grpAnalyzedFiles = new FormData();
	fd_grpAnalyzedFiles.right = new FormAttachment(100, -10);
	fd_grpAnalyzedFiles.top = new FormAttachment(0, 53);
	fd_grpAnalyzedFiles.left = new FormAttachment(0, 10);
	grpAnalyzedFiles.setLayoutData(fd_grpAnalyzedFiles);
	grpAnalyzedFiles.setText("Analyzed Files");
	grpAnalyzedFiles.setLayout(new RowLayout(SWT.HORIZONTAL));

	text_1 = new Text(grpAnalyzedFiles, SWT.BORDER);
	text_1.setLayoutData(new RowData(50, 20));
	text_1.setEditable(false);

	Label lblAnalyzedFiles = new Label(grpAnalyzedFiles, SWT.NONE);
	lblAnalyzedFiles.setText("Files");

	Group grpFailedFiles = new Group(parent, SWT.NONE);
	FormData fd_grpFailedFiles = new FormData();
	fd_grpFailedFiles.right = new FormAttachment(100, -10);
	fd_grpFailedFiles.top = new FormAttachment(0, 120);
	fd_grpFailedFiles.left = new FormAttachment(0, 10);
	grpFailedFiles.setLayoutData(fd_grpFailedFiles);
	grpFailedFiles.setText("Failed Files");
	grpFailedFiles.setLayout(new RowLayout(SWT.HORIZONTAL));

	text_2 = new Text(grpFailedFiles, SWT.BORDER);
	text_2.setEditable(false);
	text_2.setLayoutData(new RowData(50, 20));

	Label lblFiles = new Label(grpFailedFiles, SWT.NONE);
	lblFiles.setText("Files");
	getSite().getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);
    }

    @Override
    public void setFocus() {
	text.setFocus();
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	if (selection instanceof AnalysisSelection) {
	    AnalysisSelection analysisSelection = (AnalysisSelection) selection;
	    ProjectAnalyzer analyzer = analysisSelection.getAnalyzer();
	    text.setText(analyzer.getName());
	    java.util.List<AnalyzedFile> analyzedFiles = analyzer
		    .getAnalyzedFiles();
	    text_1.setText(String.valueOf(analyzedFiles.size()));
	    List<File> failedFiles = analyzer.getFailedFiles();
	    text_2.setText(String.valueOf(String.valueOf(failedFiles.size())));
	}
    }
}
