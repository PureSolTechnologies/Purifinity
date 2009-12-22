package com.puresol.html;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private static final Logger logger =
	    Logger.getLogger(HTMLStandards.class);

    public static String getStandardHeader() {
	return getStandardHeader("");
    }

    public static String getStandardHeader(String title) {
	String header = "<html>\n";
	header += "<head>\n";
	if (!title.isEmpty()) {
	    header += "<title>" + title + "</title>\n";
	}
	header += getStylesheet();
	header += "</head>\n";
	header += "<body>\n";
	return header;
    }

    public static String getStandardFooter() {
	String footer = "<hr/>\n";
	footer +=
		"(c) by <a href=\"http://www.puresol-technologies.com\">PureSol-Technologies</a>"
			+ Time.getFullTimeString() + "\n";
	footer += "</body>\n";
	footer += "</html>\n";
	return footer;
    }

    public static String getStandardFrameSetHeader() {
	return getStandardFrameSetHeader("");
    }

    public static String getStandardFrameSetHeader(String title) {
	String header = "<html>\n";
	header += "<head>\n";
	if (!title.isEmpty()) {
	    header += "<title>" + title + "</title>\n";
	}
	header += getStylesheet();
	header += "</head>\n";
	return header;
    }

    public static String getStandardFrameSetFooter() {
	return "</html>\n";
    }

    public static String getStylesheet() {
	try {
	    InputStream is =
		    HTMLStandards.class
			    .getResourceAsStream("/config/stylesheet.css");
	    BufferedReader reader =
		    new BufferedReader(new InputStreamReader(is));
	    String html = "<style type=\"text/css\">\n";
	    String line;
	    while ((line = reader.readLine()) != null) {
		html += line + "\n";
	    }
	    html += "</style>\n";
	    return html;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return "";
    }

    public static void openURLWithDefaultBrowser(URI uri) {
	try {
	    Desktop.getDesktop().browse(uri);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    JOptionPane.showConfirmDialog(Application.getInstance(),
		    "Could not open link!", "IO Error",
		    JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
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
