package com.puresol.coding.client.views;

import java.util.Collection;
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

import swing2swt.layout.BorderLayout;

import com.puresol.coding.client.Activator;
import com.puresol.coding.lang.api.ProgrammingLanguage;

public class SupportedLanguages extends ViewPart implements SelectionListener {

	private Text text;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new BorderLayout(0, 0));

		text = new Text(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.CANCEL | SWT.MULTI);
		text.setEditable(false);

		Button btnRefresh = new Button(parent, SWT.NONE);
		btnRefresh.setLayoutData(BorderLayout.NORTH);
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
			Collection<ServiceReference<ProgrammingLanguage>> allServiceReferences = bundleContext
					.getServiceReferences(ProgrammingLanguage.class, null);
			StringBuilder stringBuilder = new StringBuilder(
					"All Available Services (" + new Date().toString()
							+ "):\n\n");
			for (ServiceReference<ProgrammingLanguage> serviceReference : allServiceReferences) {
				ProgrammingLanguage service = bundleContext
						.getService(serviceReference);
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
