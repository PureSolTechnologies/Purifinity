package com.puresol.coding.client.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class AnalysisReport extends ViewPart {
    private Text text;

    public AnalysisReport() {
    }

    @Override
    public void createPartControl(Composite parent) {
	parent.setLayout(new FillLayout(SWT.HORIZONTAL));

	text = new Text(parent, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
    }

    @Override
    public void setFocus() {
	text.setFocus();
    }

}
