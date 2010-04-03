package com.puresol.utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javax.swing.JOptionPane;
import javax.swingx.Application;

import org.apache.log4j.Logger;

public class Browser {
	private static final Logger logger = Logger.getLogger(Browser.class);

	public static void openURLWithDefaultBrowser(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			JOptionPane.showConfirmDialog(Application.getInstance(),
					"Could not open link!", "IO Error", JOptionPane.OK_OPTION,
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
