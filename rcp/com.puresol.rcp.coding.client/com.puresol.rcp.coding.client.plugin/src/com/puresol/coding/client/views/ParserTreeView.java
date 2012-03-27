package com.puresol.coding.client.views;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.controls.ParserTreeControl;

public class ParserTreeView extends ViewPart implements ISelectionListener {

    private static final ILog log = Activator.getDefault().getLog();

    private ParserTreeControl viewer;

    public ParserTreeView() {
	super();
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FillLayout());
	viewer = new ParserTreeControl(parent);
	getSite().getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);
    }

    @Override
    public void setFocus() {
	viewer.setFocus();
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	try {
	    if (selection instanceof AnalysisSelection) {
		AnalysisSelection analysisSelection = (AnalysisSelection) selection;
		ProjectAnalyzer analyzer = analysisSelection.getAnalyzer();
		File sourceFile = analysisSelection.getSourceFile();

		AnalyzedFile analyzedFile = analyzer
			.findAnalyzedFile(sourceFile);
		viewer.setContentAndUpdateContent(analyzedFile, analyzer);
	    }
	} catch (IOException e) {
	    log.log(new Status(Status.ERROR, Activator.getDefault().getBundle()
		    .getSymbolicName(), e.getMessage(), e));
	}
    }
}
