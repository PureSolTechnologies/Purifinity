package com.puresoltechnologies.purifinity.client.common.server.views;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.puresoltechnologies.purifinity.client.common.server.Activator;
import com.puresoltechnologies.purifinity.client.common.server.PurifinityServerClientFactory;
import com.puresoltechnologies.purifinity.client.common.ui.views.AbstractPureSolTechnologiesView;
import com.puresoltechnologies.purifinity.server.purifinityserver.client.PurifinityServerClient;

public class ServerJobsStatusView extends AbstractPureSolTechnologiesView {

	public ServerJobsStatusView() {
		super(Activator.getDefault());
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());

		Button refreshButton = new Button(composite, SWT.NONE);
		refreshButton.setText("Refresh");
		final Text text = new Text(composite, SWT.NONE);

		refreshButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				PurifinityServerClient client = PurifinityServerClientFactory
						.getInstance();
				try {
					text.setText(client.getServerStatus().getStatusMessage());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		super.createPartControl(parent);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
