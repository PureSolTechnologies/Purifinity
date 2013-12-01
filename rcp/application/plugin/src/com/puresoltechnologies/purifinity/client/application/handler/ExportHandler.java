package com.puresoltechnologies.purifinity.client.application.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import com.puresoltechnologies.purifinity.client.application.Activator;
import com.puresoltechnologies.purifinity.client.common.branding.Exportable;

public class ExportHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow activeWorkbenchWindow = Activator.getDefault()
				.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		IWorkbenchPart part = activePage.getActivePart();
		if (Exportable.class.isAssignableFrom(part.getClass())) {
			Exportable exportable = (Exportable) part;
			exportable.export();
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		IWorkbench workbench = Activator.getDefault().getWorkbench();
		IWorkbenchWindow activeWorkbenchWindow = workbench
				.getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		IWorkbenchPart part = activePage.getActivePart();
		return Exportable.class.isAssignableFrom(part.getClass());
	}
}
