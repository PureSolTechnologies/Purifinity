package com.puresol.coding.client.views;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.client.Activator;
import com.puresol.coding.client.content.ParserTreeContentProvider;
import com.puresol.coding.client.content.ParserTreeLabelProvider;

public class ParserTreeViewer extends ViewPart implements ISelectionListener {

    private static final ILog log = Activator.getDefault().getLog();

    private Label lblNewLabel;
    private Tree tree;
    private TreeViewer treeViewer;

    public ParserTreeViewer() {
	super();
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new BorderLayout(0, 0));

	lblNewLabel = new Label(parent, SWT.NONE);
	lblNewLabel.setLayoutData(BorderLayout.NORTH);

	tree = new Tree(parent, SWT.BORDER);
	tree.setLayoutData(BorderLayout.CENTER);
	treeViewer = new TreeViewer(tree);
	treeViewer.setContentProvider(new ParserTreeContentProvider());
	treeViewer.setLabelProvider(new ParserTreeLabelProvider());

	getSite().getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);
    }

    @Override
    public void setFocus() {
	tree.setFocus();
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	try {
	    if (selection instanceof AnalysisSelection) {
		AnalysisSelection analysisSelection = (AnalysisSelection) selection;
		ProjectAnalyzer analyzer = analysisSelection.getAnalyzer();
		File sourceFile = analysisSelection.getSourceFile();

		lblNewLabel.setText(analyzer.getName() + ": " + sourceFile);
		AnalyzedFile analyzedFile = analyzer
			.findAnalyzedFile(sourceFile);
		Analysis analysis = analyzer.getAnalysis(analyzedFile);
		if (analysis != null) {
		    treeViewer.setInput(analysis.getParserTree());
		}
	    }
	} catch (IOException e) {
	    log.log(new Status(Status.ERROR, Activator.getDefault().getBundle()
		    .getSymbolicName(), e.getMessage(), e));
	}
    }
}
