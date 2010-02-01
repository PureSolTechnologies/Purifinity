/***************************************************************************
 *
 *   HTMLStandards.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swingx.Application;
import javax.swingx.config.APIConfig;
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
	return getStandardHeader("", "", true);
    }

    public static String getStandardHeader(String title, String css,
	    boolean inlineCSS) {
	String header = "<html>\n";
	header += "<head>\n";
	if (!title.isEmpty()) {
	    header += "<title>" + title + "</title>\n";
	}
	header += getCSS(css, inlineCSS);
	header += "</head>\n";
	header += "<body>\n";
	return header;
    }

    public static String getStandardCopyrightFooter() {
	String footer = "<hr/>\n";
	footer +=
		"<p>(c) " + APIConfig.PACKAGE_YEARS + " "
			+ Link.getPureSolTechnolgiesHomePage().toHTML()
			+ "<br/>(page created: "
			+ Time.getFullTimeString() + ")</p>\n";
	footer += "</body>\n";
	footer += "</html>\n";
	return footer;
    }

    public static String getStandardFooter() {
	String footer = "</body>\n";
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
	header += "</head>\n";
	return header;
    }

    public static String getStandardFrameSetFooter() {
	return "</html>\n";
    }

    public static String getCSS(String css, boolean inlineCSS) {
	if (inlineCSS) {
	    return getInlineCSS(css);
	} else {
	    return getExternalCSS(css);
	}
    }

    public static String getCopyright() {
	Application application = Application.getInstance();
	String html =
		"<b>" + application.getApplicationTitle() + " "
			+ application.getApplicationVersion()
			+ "</b><br/>";
	html +=
		"<i>(c) " + APIConfig.PACKAGE_YEARS + " "
			+ APIConfig.PACKAGE_OWNER + "<br/>";
	html += "author: " + APIConfig.PACKAGE_AUTHOR + "<br/>";
	html += "bug reports: " + APIConfig.PACKAGE_BUGREPORT;
	return html;
    }

    public static String getInlineCSS(String css) {
	if (css.isEmpty()) {
	    return "";
	}
	try {
	    InputStream is = HTMLStandards.class.getResourceAsStream(css);
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

    public static String getExternalCSS(String css) {
	if (css.isEmpty()) {
	    return "";
	}
	return "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + css
		+ "\">";
    }

    public static String convertTSVToTable(final String tsv) {
	String result = Entities.convertToEntities(tsv);
	result = result.replace("\n", "</td></tr><tr><td>");
	result = result.replace("\t", "</td><td>");
	return "<table><tr><td>" + result + "</td></tr></table>";
    }

    public static String convertSourceCodeToHTML(String sourceCode) {
	String sourceCodeHTML = "<tt>";
	sourceCodeHTML +=
		sourceCode.replaceAll("\n", "<br/>").replaceAll(" ",
			"&nbsp;").replaceAll("\t",
			"&nbsp;&nbsp;&nbsp;&nbsp;");
	sourceCodeHTML += "</tt>";
	return sourceCodeHTML;
    }

}
