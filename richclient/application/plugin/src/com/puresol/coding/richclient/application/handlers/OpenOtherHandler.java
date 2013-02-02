package com.puresol.coding.richclient.application.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class OpenOtherHandler {

	@Inject
	private EPartService partService;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
			MWindow window) {
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