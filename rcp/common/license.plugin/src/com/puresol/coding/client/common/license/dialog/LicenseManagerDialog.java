package com.puresol.coding.client.common.license.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.puresol.coding.client.common.license.content.LicensesTableViewer;
import com.puresol.license.api.LicenseManagerClient;
import com.puresol.license.api.LicenseManagerClientFactory;

/**
 * This dialog shows a list of all installed licenses and provides a mechanism
 * to a new license files.
 * 
 * @author Rick-Rainer Ludwig
 */
public class LicenseManagerDialog extends Dialog {

	private final LicenseManagerClientFactory factory = LicenseManagerClientFactory
			.getFactory();
	private final LicenseManagerClient licenseManagerClient = factory
			.createLicenseManagerClient();

	private Table licenseTable;
	private LicensesTableViewer tableViewer;
	private Text licenseText;

	public LicenseManagerDialog(Shell shell) {
		super(shell);
		setShellStyle(SWT.TITLE | SWT.APPLICATION_MODAL);
	}

	@Override
	protected final void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.CLOSE_ID,
				IDialogConstants.CLOSE_LABEL, true);
	}

	@Override
	protected final void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.CLOSE_ID) {
			close();
		}
	}

	@Override
	protected final void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("License Manager");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite leftSite = new Composite(container, SWT.NONE);
		leftSite.setLayout(new RowLayout(SWT.VERTICAL));

		licenseTable = new Table(leftSite, SWT.BORDER);
		tableViewer = new LicensesTableViewer(licenseTable);

		Button addButton = new Button(leftSite, SWT.NONE);
		addButton.setText("Add License...");

		Composite rightSite = new Composite(container, SWT.NONE);
		rightSite.setLayout(new FillLayout());
		licenseText = new Text(rightSite, SWT.BORDER | SWT.MULTI);
		licenseText.setText("License text goes here...");

		refresh();

		return container;
	}

	private void refresh() {
		LicenseManagerClientFactory factory = LicenseManagerClientFactory
				.getFactory();
		LicenseManagerClient licenseManagerClient = factory
				.createLicenseManagerClient();
		tableViewer.setInput(licenseManagerClient.getInstalledLicenses());
	}
}
