package com.puresol.html;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import javax.swing.JOptionPane;
import javax.swingx.Application;
import javax.swingx.data.Time;

import org.apache.log4j.Logger;

/**
 * This class does conversions and provide features for XHTML 1.1 standard.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
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

	public static String toHTML(String text) {
		text = text.replaceAll("<", "&lt;");
		text = text.replaceAll(">", "&gt;");
		text = text.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		return text;
	}

	public static String convertWhiteSpaces(final String text) {
		String result = text;
		result = result.replaceAll(" ", "&nbsp;");
		result = result.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		result = result.replaceAll("\n", "<br/>");
		return result;
	}

	public static String convertToEntities(final String text) {
		String result = text;
		result = result.replaceAll("<", "&lt;");
		result = result.replaceAll(">", "&gt;");
		return result;
	}

	public static String convertTSVToTable(final String tsv) {
		String result = convertToEntities(tsv);
		result = result.replace("\n", "</td></tr><tr><td>");
		result = result.replace("\t", "</td><td>");
		return "<table><tr><td>" + result + "</td></tr></table>";
	}
}
