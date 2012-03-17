package com.puresol.coding.client.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;

public class SourceNavigator extends ViewPart {

    private Tree tree;

    public SourceNavigator() {
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FillLayout(SWT.HORIZONTAL));

	tree = new Tree(parent, SWT.BORDER);
	// TODO Auto-generated method stub
    }

    @Override
    public void setFocus() {
	tree.setFocus();
    }

}
