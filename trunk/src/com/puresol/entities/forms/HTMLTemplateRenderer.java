/***************************************************************************
 *
 *   HTMLTemplateRenderer.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.entities.forms;

import java.io.IOException;
import java.io.Writer;


public class HTMLTemplateRenderer {

	static public void generateHTML(Writer out,
			TemplateInformation information, boolean withValues) {
		try {
			out.write("<h2> Template: " + information.getName() + "</h2>\n");
			out.write("<table>\n");
			for (int index = 0; index < information.getInputCount(); index++) {
				TemplateElement element = information.get(index);
				generateHTML(out, element, withValues);
			}
			out.write("</table>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public void generateHTML(Writer out, TemplateElement element,
			boolean withValues) {
		try {
			out.write("<tr>\n");
			out.write("<td>" + element.getName());
			if (element.isOptional()) {
				out.write("<sup>opt</sup>\n");
			}
			String readonly = "";
			if (element.isAutomatic()) {
				out.write("<sub>auto</sub>\n");
				readonly = "readonly=\"readonly\"";
			}
			out.write("</td>\n");
			String value = "";
			if (withValues) {
				value = "value=\"" + element.getValue() + "\"";
			}
			out.write("<td>");
			if (element.getType().equals(boolean.class)
					|| element.getType().equals(Boolean.class)) {
				String checked = "";
				if ((Boolean) element.getValue()) {
					checked = "checked=\"checked\"";
				}
				out.write("<input type=\"checkbox\" name=\"" + element.getIdString()
						+ "\" " + checked + " " + readonly + " />");
			} else {
				out.write("<input name=\"" + element.getIdString() + "\" " + value
						+ " " + readonly + " />");
			}
			out.write("</td>\n");
			out.write("</tr>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
