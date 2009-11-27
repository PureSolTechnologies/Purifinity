package com.puresol.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

/**
 * This class defines some methods for standard creator facilities for JSP web
 * page generator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JspCreator {

	private String title = "";
	private JspWriter out = null;

	public JspCreator(String title, JspWriter out) {
		this.title = title;
		this.out = out;
	}

	public void printTitle() throws IOException {
		out.print(getTitle());
	}

	public String getTitle() {
		return title;
	}

	public void createHeader() throws IOException {
		/*
		 * Header...
		 */
		out
				.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"DTD/xhtml1-transitional.dtd\">");
		out
				.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");
		out.println("<head>");
		out
				.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		out.println("<title>User Overview</title>");
		out.println("</head>");
		/*
		 * Creating desktop...
		 */
		out.println("<body bgcolor=\"black\">");
		out.println("<br/>");
		out
				.println("<table width=\"99%\" align=\"center\" bgcolor=\"darkred\" cellspacing=\"5\" cellpadding=\"5\">");
		out.println("<tr><td><h1><font color=\"white\">" + getTitle()
				+ "</font></h1></td></tr>");
		out.println("<tr><td>");
		/*
		 * Desktop...
		 */
		out
				.println("<table width=\"99%\" align=\"center\" bgcolor=\"white\" cellspacing=\"5\" cellpadding=\"5\">");
		out.println("<tr><td>");
	}

	public void createFooter() throws IOException {
		/*
		 * Closing desktop...
		 */
		out.println("</td></tr>");
		out.println("</table>");
		/*
		 * Closing desktop...
		 */
		out.println("</td></tr>");
		out.println("</table>");
		/*
		 * Footer...
		 */
		out.println("<br/>");
		out.println("</body>");
		out.println("</html>");
	}
}
