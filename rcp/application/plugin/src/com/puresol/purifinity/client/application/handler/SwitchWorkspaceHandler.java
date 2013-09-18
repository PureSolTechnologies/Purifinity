package com.puresol.purifinity.client.application.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

import com.puresol.purifinity.client.application.dialogs.PickWorkspaceDialog;

/**
 * This handler is used to open the {@link PickWorkspaceDialog} for a workspace
 * selection. If this dialog is closed with OK, then a restart takes place and
 * the workspace is changed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SwitchWorkspaceHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		PickWorkspaceDialog dialog = new PickWorkspaceDialog(true);
		if (dialog.open() == PickWorkspaceDialog.OK) {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getWorkbench()
					.restart();
		}
		return null;
	}

}
