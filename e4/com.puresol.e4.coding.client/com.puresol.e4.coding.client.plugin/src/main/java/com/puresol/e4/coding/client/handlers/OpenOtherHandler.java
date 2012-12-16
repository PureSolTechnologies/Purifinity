package main.java.com.puresol.e4.coding.client.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class OpenOtherHandler {

    @Execute
    public void execute(Shell shell) {
	FileDialog fileDialog = new FileDialog(shell);
	if (fileDialog.open() != null) {
	    MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION
		    | SWT.OK);
	    messageBox
		    .setMessage("This functionality is not implemented, yet!");
	    messageBox.open();
	}
    }
}