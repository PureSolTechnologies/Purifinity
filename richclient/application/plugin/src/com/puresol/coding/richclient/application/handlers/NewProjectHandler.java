package com.puresol.coding.richclient.application.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class NewProjectHandler {

	@Execute
	public void execute(Shell shell) {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION
				| SWT.OK);
		messageBox.setMessage("This functionality is not implemented, yet!");
		messageBox.open();
	}
}