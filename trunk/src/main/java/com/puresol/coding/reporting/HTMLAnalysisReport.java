package com.puresol.coding.reporting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.i18n4java.Translator;

import com.puresol.reporting.html.HTMLStandards;
import com.puresol.reporting.html.Image;
import com.puresol.reporting.html.Link;
import com.puresol.utils.FileUtilities;

public class HTMLAnalysisReport {

	private static final Translator translator = Translator
			.getTranslator(HTMLAnalysisReport.class);

	private final File file;
	private final File cssFile;
	private final File logoFile;
	private final File favIconFile;
	private final FileWriter writer;
	private boolean copyrightFooter = false;

	public HTMLAnalysisReport(File file, File cssFile, File logoFile,
			File favIconFile, String title) throws IOException {
		this.file = file;
		this.cssFile = cssFile;
		this.logoFile = logoFile;
		this.favIconFile = favIconFile;
		this.writer = new FileWriter(file);
		open(title);
	}

	/**
	 * Checks wheither a copyright message is to be placed at the end of the
	 * file or not.
	 * 
	 * @return the copyrightFooter
	 */
	public boolean hasCopyrightFooter() {
		return copyrightFooter;
	}

	/**
	 * Defined wheither a copyright message is to be placed at the end of the
	 * file or not.
	 * 
	 * @param copyrightFooter
	 *            the copyrightFooter to set
	 */
	public void setCopyrightFooter(boolean copyrightFooter) {
		this.copyrightFooter = copyrightFooter;
	}

	/**
	 * @return the cssFile
	 */
	public File getCssFile() {
		return cssFile;
	}

	/**
	 * @return the logoFile
	 */
	public File getLogoFile() {
		return logoFile;
	}

	/**
	 * @return the favIconFile
	 */
	public File getFavIconFile() {
		return favIconFile;
	}

	public String createLogoIMGTag() {
		return new Image(FileUtilities.getRelativePath(file, logoFile))
				.toHTML();

	}

	public String createLogoIMGTag(int widht, int height, boolean relativeSize) {
		return new Image(FileUtilities.getRelativePath(file, logoFile), widht,
				height, relativeSize).toHTML();
	}

	private void open(String title) throws IOException {
		String output = HTMLStandards.getStandardHeader(getClass(), title,
				FileUtilities.getRelativePath(file, cssFile), false,
				FileUtilities.getRelativePath(file, favIconFile));
		output += createLogoIMGTag(400, 0, false);
		output += "<h1>" + title + "</h1>";
		writer.write(output);
	}

	public void close() throws IOException {
		if (copyrightFooter) {
			writer.write(HTMLStandards.getStandardCopyrightFooter(getClass()));
		} else {
			writer.write(HTMLStandards.getStandardFooter());
		}
		writer.close();
	}

	public String createCopyrightMessage() {
		String name = translator.i18n("Copyright Information");
		String html = HTMLStandards.getStandardHeader(getClass(), name,
				cssFile, false, favIconFile);
		html += createLogoIMGTag(400, 0, false) + "<h1>" + name + "</h1>";
		html += "<p>" + HTMLStandards.getCopyright(getClass()) + "</p>";
		html += "<p>\n";
		html += "Permission to use, copy, modify and distribute<br/>\n";
		html += "this Software its accompanying documentation<br/>\n";
		html += "for any purpose is not allowed without further<br/>\n";
		html += "permission of the copyright holder.\n";
		html += "</p>\n";
		html += "<p>" + translator.i18n("For more information have a look to:")
				+ " " + Link.getPureSolTechnolgiesHomePage().toHTML() + "</p>";
		html += HTMLStandards.getStandardFooter();
		return html;
	}

	public void write(String html) throws IOException {
		writer.write(html);
	}

}
