package com.puresoltechnologies.purifinity.client.common.branding;

import org.eclipse.swt.printing.Printer;

/**
 * This interface is used to implement the ability for printing for view parts.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Printable {

	/**
	 * This method is called for the actual printing. The description of the
	 * pages needs to be handled by the view part itself.
	 * 
	 * @param printer
	 *            is a {@link Printer} object representing the printer to print
	 *            to and the settings for it.
	 * @param printJobName
	 *            is the name of the print job which can be used for progress
	 *            bars and messages.
	 */
	public void print(Printer printer, String printJobName);
}
