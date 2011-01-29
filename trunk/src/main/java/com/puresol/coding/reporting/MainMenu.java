package com.puresol.coding.reporting;

import java.io.File;

import javax.i18n4java.Translator;

import com.puresol.utils.FileUtilities;

public enum MainMenu {

	START, PROJECT, FILES, ABOUT;

	private static final Translator translator = Translator
			.getTranslator(MainMenu.class);

	public static String getHTML(File rootDirectory, File file, MainMenu current) {
		StringBuilder output = new StringBuilder();

		output.append("<table class=\"menumenu\">\n");
		output.append("<tr class=\"menumenu\">\n");

		output.append("<td class=\"menumenu\">");
		if (current != START) {
			output.append("<a href=\""
					+ FileUtilities.getRelativePath(file, new File(
							rootDirectory, "index.html")) + "/index.html\">");
			output.append(translator.i18n("Start"));
			output.append("</a>");
		} else {
			output.append(translator.i18n("Start"));
		}
		output.append("</td>\n");

		output.append("<td class=\"menumenu\">");
		if (current != PROJECT) {
			output.append("<a href=\""
					+ FileUtilities.getRelativePath(file, new File(
							rootDirectory, "project/index.html")) + "\">");
			output.append(translator.i18n("Project"));
			output.append("</a>");
		} else {
			output.append(translator.i18n("Project"));
		}
		output.append("</td>\n");

		output.append("<td class=\"menumenu\">");
		if (current != FILES) {
			output.append("<a href=\""
					+ FileUtilities.getRelativePath(file, new File(
							rootDirectory, "files/index.html")) + "\">");
			output.append(translator.i18n("Files"));
			output.append("</a>");
		} else {
			output.append(translator.i18n("Files"));
		}
		output.append("</td>\n");

		output.append("<td class=\"menumenu\">");
		if (current != PROJECT) {
			output.append("<a href=\""
					+ FileUtilities.getRelativePath(file, new File(
							rootDirectory, "about.html")) + "\">");
			output.append(translator.i18n("About"));
			output.append("</a>");
		} else {
			output.append(translator.i18n("About"));
		}
		output.append("</td>\n");

		output.append("</tr>\n");
		output.append("</table>\n");

		return output.toString();
	}
}
