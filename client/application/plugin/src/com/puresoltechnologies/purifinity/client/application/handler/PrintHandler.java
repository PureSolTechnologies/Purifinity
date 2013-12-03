package com.puresoltechnologies.purifinity.client.application.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import com.puresoltechnologies.purifinity.client.application.Activator;
import com.puresoltechnologies.purifinity.client.common.branding.Printable;

/**
 * This handler is used to print the currently selected view or editor.
 * 
 * @see http://www.eclipsezone.com/eclipse/forums/t31374.html
 * @see http://www.eclipse.org/forums/index.php/m/638711/
 * 
 * @author Rick-Rainer Ludwig
 */
public class PrintHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow activeWorkbenchWindow = Activator.getDefault()
				.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		IWorkbenchPart part = activePage.getActivePart();
		if (Printable.class.isAssignableFrom(part.getClass())) {
			PrintDialog printDialog = new PrintDialog(
					activeWorkbenchWindow.getShell(), SWT.NONE);
			printDialog.setText("Print '" + part.getTitle() + "'");
			PrinterData printerData = printDialog.open();
			if (printerData != null) {
				Printer printer = new Printer(printerData);
				try {
					Printable printable = (Printable) part;
					printable.print(printer, "Printing " + part.getTitle());
				} finally {
					printer.dispose();
				}
			}
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
		return Printable.class.isAssignableFrom(part.getClass());
	}
}
