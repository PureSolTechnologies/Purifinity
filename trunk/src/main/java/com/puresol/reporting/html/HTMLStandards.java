/***************************************************************************
 *
 *   HTMLStandards.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.reporting.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.puresol.config.APIInformation;
import com.puresol.gui.Application;
import com.puresol.gui.data.Time;

/**
 * This class does conversions and provide features for XHTML 1.1 standard.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class HTMLStandards {

	private static final Logger logger = Logger.getLogger(HTMLStandards.class);

	public static String getStandardHeader(Class<?> clazz) {
		return getStandardHeader(clazz, "", null, true, null);
	}

	/**
	 * 
	 * @param clazz
	 *            is used to retrieve information from the right resource file.
	 * @param title
	 * @param css
	 * @param inlineCSS
	 * @param favoriteIcon
	 * @return
	 */
	public static String getStandardHeader(Class<?> clazz, String title,
			File css, boolean inlineCSS, File favoriteIcon) {
		String header = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n";
		header += "<html>\n";
		header += "<head>\n";
		if (!title.isEmpty()) {
			header += "<title>" + title + "</title>\n";
		}
		header += getCSS(css, inlineCSS);
		header += getFavIcon(favoriteIcon);
		header += "<meta name=\"author\" content=\""
				+ APIInformation.getPackageAuthor() + "\"/>\n";
		header += "<meta name=\"copyright\" content=\"(c) "
				+ APIInformation.getPackageYears() + " by "
				+ APIInformation.getPackageOwner() + "\"/>\n";
		header += "<meta name=\"generator\" content=\""
				+ Application.getInstance().getApplicationTitle() + " "
				+ Application.getInstance().getApplicationVersion() + "\"/>\n";
		header += "</head>\n";
		header += "<body>\n";
		return header;
	}

	public static String getStandardCopyrightFooter(Class<?> clazz) {
		String footer = "<hr/>\n";
		footer += "<p>(c) " + APIInformation.getPackageYears() + " "
				+ Link.getPureSolTechnolgiesHomePage().toHTML()
				+ "<br/>\n(page created: " + Time.getFullTimeString()
				+ ")</p>\n";
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

	public static String getStandardPageStart(File logo, String altLogo,
			File banner, String altBanner) {
		String html = "<!-- Begin of Standard Page Start -->\n";
		html += "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n";
		html += "<colgroup>\n";
		html += "<col width=\"*\"/>\n";
		html += "<col width=\"346\"/>\n";
		html += "</colgroup>\n";
		html += "<tr>\n";
		html += "<td class=\"title\" align=\"left\" valign=\"top\">\n";
		if (logo != null) {
			html += "<a class=\"title\" href=\""
					+ Link.PURESOL_TECHNOLOGIES_WEBPAGE
					+ "\" target=\"_top\"><img class=\"titleimage\" src=\""
					+ logo + "\" alt=\"" + altLogo + "\" width=\"450\"/></a>\n";
		}
		html += "</td>\n";
		html += "\n";
		html += "<td class=\"titlebanner\" align=\"right\" valign=\"top\">\n";
		if (banner != null) {
			html += "<img class=\"titlebanner\" src=\"" + banner + "\" alt=\""
					+ altBanner + "\" width=\"346\"/>\n";
		}
		html += "</td>\n";
		html += "</tr>\n";
		html += "</table>\n";
		html += "<!-- End of Standard Page Start -->\n";
		return html;
	}

	public static String getStandardPageEnd() {
		String html = "<!-- Begin of Standard Page End -->\n";
		html += "<table class=\"footer\" width=\"100%\">\n";
		html += "<colgroup>\n";
		html += "<col width=\"35%\"/>\n";
		html += "<col width=\"30%\"/>\n";
		html += "\n";
		html += "<col width=\"35%\"/>\n";
		html += "</colgroup>\n";
		html += "<tr>\n";
		html += "<td align=\"left\">\n";
		html += "<a class=\"footer\" href=\"mailto:contact@puresol-technologies.com\">mailto:contact@puresol-technologies.com</a><br/>\n";
		html += "last change: " + Time.getFullTimeString() + "\n";
		html += "</td>\n";
		html += "<td align=\"center\">\n";
		html += "<a class=\"footer\" href=\"copyright.html\" target=\"_blank\">\n";
		html += "<b>Copyright</b>\n";
		html += "</a>\n";
		html += "</td>\n";
		html += "<td align=\"right\">\n";
		html += "</td>\n";
		html += "</tr>\n";
		html += "</table>\n";
		html += "<!-- End of Standard Page End -->\n";
		return html;
	}

	public static String getStandardMainMenu(Link activeLink, Link... links) {
		String html = "<!-- Begin of Standard Main Menu-->\n";
		html += "<table class=\"menubar\" width=\"100%\">\n";
		html += "<tr>\n";
		html += "<td align=\"left\">\n";
		for (Link link : links) {
			if (link == activeLink) {
				html += "<span class=\"activemenuitem\">" + link.getText()
						+ "</span>\n";
			} else {
				html += "<span class=\"menuitem\"><a class=\"menulink\" href=\""
						+ link.getUrl()
						+ "\">"
						+ link.getText()
						+ "</a></span>\n";
			}
		}
		html += "</td>\n";
		html += "<td align=\"right\">\n";
		html += "<!-- right site space for additional content -->\n";
		html += "</tr>\n";
		html += "</table>\n";
		html += "<!-- End of Standard Main Menu-->\n";
		return html;
	}

	public static String getStandardSubMenu(String standardTarget,
			Link activeLink, Link... links) {
		String html = "<!-- Begin of Sub Menu -->\n";
		html += "<div class=\"submenu\">\n";
		for (Link link : links) {
			if (link == activeLink) {
				html += "<div class=\"activesubmenuitem\">" + link.getText()
						+ "</div>\n";
			} else {
				html += "<div class=\"submenuitem\"><a class=\"submenulink\" href=\""
						+ link.getUrl() + "\"";
				if (standardTarget != null) {
					if (!standardTarget.isEmpty()) {
						html += " target=\"" + standardTarget + "\"";
					}
				}
				html += ">" + link.getText() + "</a></div>\n";
			}
		}
		html += "</div>\n";
		html += "<!-- End of Sub Menu -->\n";
		return html;
	}

	public static String getStandardContentPane(String subMenu,
			String pageContent, String rightBar) {
		String html = "<!-- Begin of Content Pane -->\n";
		html += "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n";
		html += "<colgroup>\n";
		html += "<col width=\"19%\"/>\n";
		html += "<col width=\"62%\"/>\n";
		html += "<col width=\"19%\"/>\n";
		html += "</colgroup>\n";
		html += "<tr>\n";
		html += "<td align=\"left\" valign=\"top\">\n";
		html += "<!-- Place for Sub Menu -->\n";
		html += subMenu;
		html += "</td>\n";
		html += "<td class=\"contentcolumn\" align=\"left\" valign=\"top\">\n";
		html += "<!-- Place for Page Content -->\n";
		html += pageContent;
		html += "</td>\n";
		html += "<td align=\"left\" valign=\"top\">\n";
		html += "<!-- Place for Right Bar -->\n";
		html += rightBar;
		html += "</td>\n";
		html += "</tr>\n";
		html += "</table>\n";
		html += "<!-- End of Content Pane -->\n";
		return html;
	}

	public static String getFavIcon(File favIcon) {
		if (favIcon == null) {
			return "";
		}
		if (favIcon.getPath().isEmpty()) {
			return "";
		}
		return "<link rel=\"shortcut icon\" href=\"" + favIcon + "\"/>\n";
	}

	public static String getCSS(File css, boolean inlineCSS) {
		if (inlineCSS) {
			return getInlineCSS(css);
		} else {
			return getExternalCSS(css);
		}
	}

	public static String getCopyright(Class<?> clazz) {
		Application application = Application.getInstance();
		String html = "<b>" + application.getApplicationTitle() + " "
				+ application.getApplicationVersion() + "</b><br/>\n";
		html += "<i>(c) " + APIInformation.getPackageYears() + " "
				+ APIInformation.getPackageOwner() + "<br/>\n";
		html += "author: " + APIInformation.getPackageAuthor() + "<br/>\n";
		html += "bug reports: " + APIInformation.getPackageBugReport();
		return html;
	}

	public static String getInlineCSS(File css) {
		if (css == null) {
			return "";
		}
		try {
			InputStream is = HTMLStandards.class.getResourceAsStream(css
					.getPath());
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
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

	public static String getExternalCSS(File css) {
		if (css == null) {
			return "";
		}
		return "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + css
				+ "\"/>\n";
	}

	public static String convertTSVToTable(final String tsv) {
		String result = Entities.convertToEntities(tsv);
		result = result.replace("\n", "</td></tr><tr><td>");
		result = result.replace("\t", "</td><td>");
		return "<table><tr><td>" + result + "</td></tr></table>";
	}

	public static String convertSourceCodeToHTML(String sourceCode) {
		String sourceCodeHTML = "<tt>\n";
		sourceCodeHTML += sourceCode.replaceAll("\n", "<br/>\n")
				.replaceAll(" ", "&nbsp;")
				.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		sourceCodeHTML += "</tt>\n";
		return sourceCodeHTML;
	}

	public static String convertFlowTextToHTML(String flowText) {
		String flowTextHTML = "<p>";
		flowTextHTML += flowText.replaceAll("\n\n", "</p><p>").replaceAll("\n",
				"<br/>");
		flowTextHTML += "</p>";
		return flowTextHTML;
	}

}
