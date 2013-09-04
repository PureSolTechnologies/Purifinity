package com.puresol.purifinity.client.application;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.IntroPart;

public class PurifinityIntroPart extends IntroPart {
	private Text text;

	@Override
	public void standbyStateChanged(boolean standby) {
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		text = new Text(composite, SWT.BORDER);
		text.setEditable(false);
		text.setText("Welcome to Purifinity!\n\nIt is the tool No. 1 for source code analysis.");
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}
}
