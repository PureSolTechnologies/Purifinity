package com.puresol.coding.client.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class OpenAnalysis extends AbstractHandler implements IHandler {

    @Override
    public void addHandlerListener(IHandlerListener handlerListener) {
	// not needed...
    }

    @Override
    public void dispose() {
	// not needed...
    }

    @Override
    public String execute(ExecutionEvent event) throws ExecutionException {
	Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
		.getShell();

	DirectoryDialog fileDialog = new DirectoryDialog(shell, SWT.OPEN);
	fileDialog.setText("Open Analysis");
	fileDialog.open();
	return fileDialog.getFilterPath();
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    @Override
    public boolean isHandled() {
	return true;
    }

    @Override
    public void removeHandlerListener(IHandlerListener handlerListener) {
	// not needed...
    }

}
