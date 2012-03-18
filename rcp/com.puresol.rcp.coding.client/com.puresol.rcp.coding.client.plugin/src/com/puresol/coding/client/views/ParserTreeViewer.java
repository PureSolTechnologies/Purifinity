package com.puresol.coding.client.views;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import swing2swt.layout.BorderLayout;

public class ParserTreeViewer extends ViewPart implements ISelectionListener {

    private Label lblNewLabel;
    private Tree tree;

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

	getSite().getWorkbenchWindow().getSelectionService()
		.addSelectionListener(this);
    }

    @Override
    public void setFocus() {
	tree.setFocus();
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	if (selection instanceof AnalysisSelection) {
	    AnalysisSelection analysisSelection = (AnalysisSelection) selection;
	    lblNewLabel.setText(analysisSelection.getAnalyzer().getName()
		    + ": " + analysisSelection.getSourceFile());
	}
    }

}
