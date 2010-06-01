package com.puresol.coding.reporting;

import java.io.File;
import java.io.IOException;

import javax.i18n4j.Translator;

import com.puresol.coding.evaluator.metric.report.HTMLMetricsProject;
import com.puresol.jars.JarFile;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.reporting.html.Image;
import com.puresol.reporting.html.Link;
import com.puresol.utils.FileUtilities;

public class StandardReport {

	private static final Translator translator = Translator
			.getTranslator(StandardReport.class);

	private final File projectDirectory;
	private boolean copyrightFooter = false;
	private File cssFile = null;
	private File logoFile = null;
	private File favIconFile = null;

	public StandardReport(File projectDirectory) {
		this.projectDirectory = projectDirectory;
	}

	/**
	 * Checks wheither a copyright message is to be placed at the end of the
	 * file or not.
	 * 
	 * @return the copyrightFooter
	 */
	public boolean isCopyrightFooter() {
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
	 * @param cssFile
	 *            the cssFile to set
	 */
	public void setCssFile(File cssFile) {
		this.cssFile = cssFile;
	}

	/**
	 * @return the logoFile
	 */
	public File getLogoFile() {
		return logoFile;
	}

	/**
	 * @param logoFile
	 *            the logoFile to set
	 */
	public void setLogoFile(File logoFile) {
		this.logoFile = logoFile;
	}

	/**
	 * @return the favIconFile
	 */
	public File getFavIconFile() {
		return favIconFile;
	}

	/**
	 * @param favIconFile
	 *            the favIconFile to set
	 */
	public void setFavIconFile(File favIconFile) {
		this.favIconFile = favIconFile;
	}

	public void createFile(File file, String title, String bodyText)
			throws IOException {
		createStandardFiles();
		String output = generateFinalText(file, title, bodyText);
		writeFile(file, output);
	}

	public String createLogoIMGTag(File file) {
		return new Image(FileUtilities.getRelativePath(file, logoFile)).toHTML();

	}

	public String createLogoIMGTag(File file, int widht, int height,
			boolean relativeSize) {
		return new Image(FileUtilities.getRelativePath(file, logoFile), widht, height,
				relativeSize).toHTML();
	}

	public void createFile(File file, String html) throws IOException {
		createStandardFiles();
		writeFile(file, html);
	}

	public void createStandardFiles() {
		createLogoFile();
		createCSSFile();
		createFavIconFile();
	}

	private String generateFinalText(File file, String title, String bodyText) {
		String output = HTMLStandards.getStandardHeader(title, FileUtilities
				.getRelativePath(file, cssFile), false, FileUtilities.getRelativePath(
				file, favIconFile));
		output += createLogoIMGTag(file, 400, 0, false);
		output += "<h1>" + title + "</h1>";
		output += bodyText;
		if (copyrightFooter) {
			output += HTMLStandards.getStandardCopyrightFooter();
		} else {
			output += HTMLStandards.getStandardFooter();
		}
		return output;
	}

	private void writeFile(File file, String text) throws IOException {
		FileUtilities.writeFile(projectDirectory, file, text);
	}

	private void createLogoFile() {

		if (logoFile != null) {
			File logo = FileUtilities.addPaths(projectDirectory, logoFile);
			if (!logo.exists()) {
				JarFile.extractResource(HTMLMetricsProject.class
						.getResource("/config/logo.png"), logo);
			}
		}
	}

	private void createCSSFile() {
		if (cssFile != null) {
			File css = FileUtilities.addPaths(projectDirectory, cssFile);
			if (!css.exists()) {
				JarFile.extractResource(HTMLMetricsProject.class
						.getResource("/css/report.css"), css);
			}
		}
	}

	private void createFavIconFile() {
		if (favIconFile != null) {
			File favIcon = FileUtilities.addPaths(projectDirectory, favIconFile);
			if (!favIcon.exists()) {
				JarFile.extractResource(HTMLMetricsProject.class
						.getResource("/config/favicon.png"), favIcon);
			}
		}
	}

	public boolean createStartHTML(String name) {
		File outputFile = new File("start.html");
		String html = HTMLStandards.getStandardHeader(name, cssFile, false,
				favIconFile);
		html += createLogoIMGTag(outputFile, 400, 0, false) + " <h1>" + name
				+ "</h1>";
		html += "<p>" + HTMLStandards.getCopyright() + "</p>";
		html += "<p>" + translator.i18n("For more information have a look to:")
				+ " " + Link.getPureSolTechnolgiesHomePage().toHTML() + "</p>";
		html += HTMLStandards.getStandardFooter();
		return FileUtilities.writeFile(projectDirectory, outputFile, html);
	}

	public boolean createCopyrightHTML() {
		String name = translator.i18n("Copyright Information");
		File outputFile = new File("copyright.html");
		String html = HTMLStandards.getStandardHeader(name, cssFile, false,
				favIconFile);
		html += createLogoIMGTag(outputFile, 400, 0, false) + "<h1>" + name
				+ "</h1>";
		html += "<p>" + HTMLStandards.getCopyright() + "</p>";
		html += "<p>\n";
		html += "Permission to use, copy, modify and distribute<br/>\n";
		html += "this Software its accompanying documentation<br/>\n";
		html += "for any purpose is not allowed without further<br/>\n";
		html += "permission of the copyright holder.\n";
		html += "</p>\n";
		html += "<p>" + translator.i18n("For more information have a look to:")
				+ " " + Link.getPureSolTechnolgiesHomePage().toHTML() + "</p>";
		html += HTMLStandards.getStandardFooter();
		return FileUtilities.writeFile(projectDirectory, outputFile, html);
	}

}
