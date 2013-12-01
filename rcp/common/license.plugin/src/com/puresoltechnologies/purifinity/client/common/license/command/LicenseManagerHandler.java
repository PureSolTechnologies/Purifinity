package com.puresoltechnologies.purifinity.client.common.license.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.puresoltechnologies.purifinity.client.common.license.Activator;
import com.puresoltechnologies.purifinity.client.common.license.dialog.LicenseManagerDialog;

/**
 * This handler is used to open the {@link LicenseManagerDialog}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LicenseManagerHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		LicenseManagerDialog dialog = new LicenseManagerDialog(Activator
				.getDefault().getWorkbench().getActiveWorkbenchWindow()
				.getShell());
		dialog.setBlockOnOpen(true);
		dialog.open();
		return null;
	}

}
