package com.puresol.coding.client.application.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.PrintDialog;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.printing.PrinterData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;

import com.puresol.coding.client.application.Activator;
import com.puresol.coding.client.common.branding.Printable;

/**
 * @see http://www.eclipsezone.com/eclipse/forums/t31374.html
 * @see http://www.eclipse.org/forums/index.php/m/638711/
 * @author Rick-Rainer Ludwig
 * 
 */
public class PrintHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// not needed
	}

	@Override
	public void dispose() {
		// not needed
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = Activator.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getShell();
		PrintDialog printDialog = new PrintDialog(shell, SWT.NONE);
		printDialog.setText("Print");
		PrinterData printerData = printDialog.open();
		if (!(printerData == null)) {
			Printer p = new Printer(printerData);
			p.startJob("PrintJob");
			p.startPage();
			Rectangle trim = p.computeTrim(0, 0, 0, 0);
			Point dpi = p.getDPI();
			int leftMargin = dpi.x + trim.x;
			int topMargin = dpi.y / 2 + trim.y;
			GC gc = new GC(p);
			Font font = gc.getFont();
			String printText = "Hallo!";
			Point extent = gc.stringExtent(printText);
			gc.drawString(printText, leftMargin, topMargin
					+ font.getFontData()[0].getHeight());
			p.endPage();
			gc.dispose();
			p.endJob();
			p.dispose();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		IWorkbenchPart part = Activator.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActivePart();
		return Printable.class.isAssignableFrom(part.getClass());
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// not needed
	}

}
