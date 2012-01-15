package com.puresol.coding.client.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.PlatformUI;

import com.puresol.coding.client.dialogs.PickWorkspaceDialog;

public class SwitchWorkspace implements IHandler {

    @Override
    public void addHandlerListener(IHandlerListener handlerListener) {
	// not needed...
    }

    @Override
    public void dispose() {
	// not needed...
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
	PickWorkspaceDialog dialog = new PickWorkspaceDialog(true);
	if (dialog.open() == PickWorkspaceDialog.OK) {
	    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getWorkbench()
		    .restart();
	}
	return null;
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
