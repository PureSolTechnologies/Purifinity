package com.puresoltechnologies.purifinity.client.common.server.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.puresoltechnologies.purifinity.client.common.server.Activator;
import com.puresoltechnologies.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;

public class ServerMonitorView extends AbstractPureSolTechnologiesView {

	public ServerMonitorView() {
		super(Activator.getDefault());
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());

		Text text = new Text(composite, SWT.NONE);
		text.setText("Server Monitor...");

		super.createPartControl(parent);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
