package com.puresol.coding.richclient.application.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.puresoltechnologies.coding.richclient.application.dialogs.PickWorkspaceDialog;

@SuppressWarnings("restriction")
public class SwitchWorkspaceHandler {

	@Execute
	public void execute(Shell shell) {
		PickWorkspaceDialog dialog = new PickWorkspaceDialog(true);
		if (dialog.open() == Dialog.OK) {
			MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION
					| SWT.OK);
			messageBox
					.setMessage("This functionality is not implemented, yet!");
			messageBox.open();

		}
	}
}
