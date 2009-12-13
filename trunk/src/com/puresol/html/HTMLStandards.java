package com.puresol.html;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javax.swing.JOptionPane;
import javax.swingx.Application;
import javax.swingx.data.Time;

import org.apache.log4j.Logger;

public class HTMLStandards {

	private static final Logger logger = Logger.getLogger(HTMLStandards.class);

	public static String getStandardHeader() {
		return "<html><body>";
	}

	public static String getStandardFooter() {
		String footer = "<hr/>";
		footer += "(c) by <a href=\"http://www.puresol-technologies.com\">PureSol-Technologies</a>"
				+ Time.getFullTimeString();
		footer += "</body></html>";
		return footer;
	}

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
