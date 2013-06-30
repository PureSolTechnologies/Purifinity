package com.puresol.purifinity.client.common.license.command;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

import com.puresol.purifinity.client.common.license.Activator;
import com.puresol.purifinity.client.common.license.dialog.LicenseManagerDialog;

public class LicenseManagerCommand implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		LicenseManagerDialog dialog = new LicenseManagerDialog(Activator
				.getDefault().getWorkbench().getActiveWorkbenchWindow()
				.getShell());
		dialog.setBlockOnOpen(true);
		dialog.open();
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
		// TODO Auto-generated method stub

	}

}
