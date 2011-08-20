/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.printing;

import java.awt.Dimension;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.i18n4java.Translator;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.puresol.gui.Application;

/**
 * This object is a fascade for printer handling. Due to a wish to limit the
 * need for resources the object is design in singleton pattern. The object
 * lazely creates an instance of itself to work with.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PrinterSupport {

	private static final Logger logger = Logger.getLogger(PrinterSupport.class);
	private static final Translator translator = Translator
			.getTranslator(PrinterSupport.class);

	/**
	 * This is the general attribute set for keeping the current settings.
	 */
	private PrintRequestAttributeSet aset;
	/**
	 * The printer job to be used.
	 */
	private PrinterJob pj;
	/**
	 * This is the list of printer services available on the system.
	 */
	private PrintService[] printServices;
	/**
	 * This is the default printing resolution.
	 */
	private final int DEFAULT_DPI = 600;

	/**
	 * This is the variable keeping the one and only instance of this object.
	 */
	static private volatile PrinterSupport instance = null;

	/**
	 * This is the standard constructor without any arguments. The standard
	 * printer settings and the printer services are read from the system.
	 */
	private PrinterSupport() {
		aset = new HashPrintRequestAttributeSet();
		aset.add(new PrinterResolution(600, 600, PrinterResolution.DPI));
		aset.add(Chromaticity.MONOCHROME);
		pj = PrinterJob.getPrinterJob();
		printServices = PrinterJob.lookupPrintServices();
		if (printServices.length > 0) {
			try {
				logger.info("selected printer: " + printServices[0]);
				pj.setPrintService(printServices[0]);
			} catch (PrinterException pe) {
				System.err.println(pe);
			}
		}
	}

	/**
	 * This is the method to get a reference to the PrinterSupport instance
	 * which is created only once. This is the only way to get access to this
	 * class, because this class was designed in singleton pattern.
	 * 
	 * @return The reference to the PrinterSupport class is returned.
	 */
	static public PrinterSupport getInstance() {
		if (instance == null) {
			synchronized (PrinterSupport.class) {
				if (instance == null) {
					instance = new PrinterSupport();
				}
			}
		}
		return instance;
	}

	/**
	 * This method checks for the availability of printing service.
	 * 
	 * @return True is returned if printing is possible on the current system.
	 */
	static public boolean isAvailable() {
		return (getInstance().printServices.length > 0);
	}

	/**
	 * This method opens the page setup dialog for changing the page attributes
	 * like borders.
	 * 
	 * @param parent
	 *            is the parent windows calling the page dialog.
	 */
	static public void pageSetup() {
		getInstance().showPageSetup();
	}

	/**
	 * This method opens the page setup dialog for changing the page attributes
	 * like borders.
	 * 
	 * @param parent
	 *            is the parent windows calling the page dialog.
	 */
	private void showPageSetup() {
		if (printServices.length > 0) {
			pj.pageDialog(aset);
		} else {
			JOptionPane.showMessageDialog(Application.getInstance(),
					translator.i18n("No printing services available."),
					translator.i18n("Error"), JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}
	}

	/**
	 * This method is for setting the page renderer. Every object with a
	 * Printable interface can be used.
	 * 
	 * @param renderer
	 *            is the rendering object with Printable interface to be used to
	 *            render the page.
	 */
	static public void setRenderer(Printable renderer) {
		getInstance().pj.setPrintable(renderer);
	}

	/**
	 * This method starts the printer settings dialog. The printer can be
	 * selected and also the printing attributes can be changed.
	 * 
	 * @param parent
	 *            is the calling parent windows.
	 * @return True is returned in the case that the setting of the printer was
	 *         successful.
	 */
	static public boolean printerSetup() {
		return getInstance().showPrinterSetup();
	}

	/**
	 * This method starts the printer settings dialog. The printer can be
	 * selected and also the printing attributes can be changed.
	 * 
	 * @param parent
	 *            is the calling parent windows.
	 * @return True is returned in the case that the setting of the printer was
	 *         successful.
	 */
	private boolean showPrinterSetup() {
		if (printServices.length > 0) {
			return pj.printDialog(aset);
		} else {
			JOptionPane.showMessageDialog(Application.getInstance(),
					translator.i18n("No printing services available."),
					translator.i18n("Error"), JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}
		return false;
	}

	/**
	 * This method starts the printing process itself.
	 * 
	 * @param parent
	 *            is the calling parent window.
	 */
	static public void print() {
		getInstance().performPrint();
	}

	/**
	 * This method starts the printing process itself.
	 * 
	 * @param parent
	 *            is the calling parent window.
	 */
	private void performPrint() {
		if (printServices.length > 0) {
			try {
				pj.print(aset);
			} catch (PrinterException pe) {
				System.err.println(pe);
			}
		} else {
			JOptionPane.showMessageDialog(Application.getInstance(),
					translator.i18n("No printing services available."),
					translator.i18n("Error"), JOptionPane.ERROR_MESSAGE
							+ JOptionPane.OK_OPTION);
		}
	}

	/**
	 * This method checks for the availability of color printing. This method
	 * can be used to tell the render to render in color or black and white,
	 * 
	 * @return True is returned if the print out shall be in monochrome (black
	 *         and white) format.
	 */
	static public boolean isMonochrome() {
		return getInstance().monochrome();
	}

	/**
	 * This method checks for the availability of color printing. This method
	 * can be used to tell the render to render in color or black and white,
	 * 
	 * @return True is returned if the print out shall be in monochrome (black
	 *         and white) format.
	 */
	private boolean monochrome() {
		Chromaticity cc = (Chromaticity) aset.get(Chromaticity.class);
		if (cc == null) {
			// no chromaticity entry. therefore, is monochrome printer.
			System.out.println("No chromaticity: Monochrome printing");
			return true;
		}
		boolean monochrome = cc.getValue() == Chromaticity.MONOCHROME
				.getValue();
		if (monochrome) {
			System.out.println("Monochrome printing");
		} else {
			System.out.println("Color printing");
		}
		return monochrome;
	}

	/**
	 * This method checks out the current set printer resolution.
	 * 
	 * @return A Dimension object is returned containing the resolution in
	 *         horizontal and vertical direction.
	 */
	static public Dimension getResolution() {
		return getInstance().resolution();
	}

	/**
	 * This method checks out the current set printer resolution.
	 * 
	 * @return A Dimension object is returned containing the resolution in
	 *         horizontal and vertical direction.
	 */
	private Dimension resolution() {
		Dimension res = new Dimension();
		PrinterResolution pr = (PrinterResolution) aset
				.get(PrinterResolution.class);
		if (pr == null) {
			System.out.println("no resolution... Setting to default.");
			res.setSize(DEFAULT_DPI, DEFAULT_DPI);
		} else {
			res.setSize(pr.getCrossFeedResolution(PrinterResolution.DPI),
					pr.getFeedResolution(PrinterResolution.DPI));
		}
		System.out.println("resolution: " + res.getWidth() + "x"
				+ res.getHeight());
		return res;
	}
}
