package com.puresol.purifinity.client.application.views;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.purifinity.client.application.Activator;

public class OsgiDebugView extends ViewPart implements SelectionListener {
	private Text text;

	@Override
	public void createPartControl(Composite parent) {

		text = new Text(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.CANCEL | SWT.MULTI);
		text.setEditable(false);

		Button btnRefresh = new Button(parent, SWT.NONE);
		btnRefresh.setText("Refresh");
		btnRefresh.addSelectionListener(this);
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		try {
			BundleContext bundleContext = Activator.getDefault().getBundle()
					.getBundleContext();
			ServiceReference<?>[] allServiceReferences = bundleContext
					.getAllServiceReferences(null, null);
			StringBuilder stringBuilder = new StringBuilder(
					"All Available Services (" + new Date().toString()
							+ "):\n\n");
			for (ServiceReference<?> serviceReference : allServiceReferences) {
				Object service = bundleContext.getService(serviceReference);
				stringBuilder.append(service.getClass().getName() + "\n");
				bundleContext.ungetService(serviceReference);
			}
			text.setText(stringBuilder.toString());
		} catch (InvalidSyntaxException e1) {
			text.setText(e1.getMessage());
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

}
