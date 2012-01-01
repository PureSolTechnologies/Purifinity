package com.puresol.coding.reporting.html;

import java.io.File;
import java.io.IOException;

import javax.i18n4java.Translator;

import com.puresol.utils.FileUtilities;
import com.puresol.utils.PathResolutionException;

enum MainMenu {

	START, PROJECT, FILES, ABOUT;

	private static final Translator translator = Translator
			.getTranslator(MainMenu.class);

	public static String getHTML(File rootDirectory, File file, MainMenu current)
			throws IOException {
		StringBuilder output = new StringBuilder();

		output.append("<table class=\"menumenu\">\n");
		output.append("<tr class=\"menumenu\">\n");

		output.append(createEntry(current, START, translator.i18n("Start"),
				rootDirectory, file, new File(rootDirectory, "index.html")));

		output.append(createEntry(current, PROJECT, translator.i18n("Project"),
				rootDirectory, file, new File(rootDirectory, "project"
						+ File.separator + "index.html")));

		output.append(createEntry(current, FILES, translator.i18n("Files"),
				rootDirectory, file, new File(rootDirectory, "files"
						+ File.separator + "index.html")));

		output.append(createEntry(current, ABOUT, translator.i18n("About"),
				rootDirectory, file,
				HTMLProjectAnalysisCreator.getAboutFile(rootDirectory)));

		output.append("</tr>\n");
		output.append("</table>\n");

		return output.toString();
	}

	private static String createEntry(MainMenu current, MainMenu menuEntry,
			String text, File rootDirectory, File from, File to)
			throws IOException {
		try {
			StringBuilder output = new StringBuilder();
			output.append("<td class=\"menumenu\">");
			if (current != menuEntry) {
				output.append("<a href=\""
						+ FileUtilities.getRelativePath(from.getPath(),
								to.getPath(), File.separator) + "\">");
				output.append(text);
				output.append("</a>");
			} else {
				output.append(text);
			}
			output.append("</td>\n");
			return output.toString();
		} catch (PathResolutionException e) {
			throw new IOException(e);
		}
	}
}
