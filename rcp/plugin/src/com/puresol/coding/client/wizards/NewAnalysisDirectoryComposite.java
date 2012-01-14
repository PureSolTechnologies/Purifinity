package com.puresol.coding.client.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class NewAnalysisDirectoryComposite extends Composite {
    private final Text text;

    public NewAnalysisDirectoryComposite(Composite parent, int style) {
	super(parent, style);
	setLayout(new FormLayout());

	text = new Text(this, SWT.BORDER);
	FormData fd_text = new FormData();
	fd_text.right = new FormAttachment(100, -180);
	fd_text.top = new FormAttachment(0, 41);
	text.setLayoutData(fd_text);

	Button btnBrowse = new Button(this, SWT.NONE);
	btnBrowse.addSelectionListener(new SelectionAdapter() {
	    @Override
	    public void widgetSelected(SelectionEvent e) {
		Shell shell = PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getShell();
		DirectoryDialog dialog = new DirectoryDialog(shell);
		dialog.setText("Select Source Directory...");
		dialog.open();
	    }
	});
	FormData fd_btnNewButton = new FormData();
	fd_btnNewButton.top = new FormAttachment(0, 41);
	fd_btnNewButton.right = new FormAttachment(100, -75);
	btnBrowse.setLayoutData(fd_btnNewButton);
	btnBrowse.setText("Browse...");

	Label lblNewLabel = new Label(this, SWT.NONE);
	fd_text.left = new FormAttachment(lblNewLabel, 19);
	FormData fd_lblNewLabel = new FormData();
	fd_lblNewLabel.top = new FormAttachment(0, 41);
	fd_lblNewLabel.left = new FormAttachment(0, 65);
	lblNewLabel.setLayoutData(fd_lblNewLabel);
	lblNewLabel.setText("Source Directory:");
    }
}
