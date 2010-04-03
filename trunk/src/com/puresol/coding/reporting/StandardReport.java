package com.puresol.coding.reporting;

import java.io.File;
import java.io.IOException;

import javax.i18n4j.Translator;

import com.puresol.coding.evaluator.metric.report.HTMLMetricsProject;
import com.puresol.jars.JarFile;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.reporting.html.Link;
import com.puresol.utils.Files;

public class StandardReport {

	private static final Translator translator = Translator
			.getTranslator(StandardReport.class);

	private final File projectDirectory;
	private boolean copyrightFooter = false;
	private File cssFile = null;
	private File logoFile = null;

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

	public void createFile(File file, String title, String bodyText)
			throws IOException {
		createStandardFiles();

		String output = generateFinalText(title, bodyText);
		writeFile(file, output);
	}

	public void createFile(File file, String html) throws IOException {
		createStandardFiles();
		writeFile(file, html);
	}

	public void createStandardFiles() {
		createLogo();
		createCSS();
	}

	private String generateFinalText(String title, String bodyText) {
		String output = HTMLStandards.getStandardHeader(title, cssFile, false);
		output += bodyText;
		if (copyrightFooter) {
			output += HTMLStandards.getStandardCopyrightFooter();
		} else {
			output += HTMLStandards.getStandardFooter();
		}
		return output;
	}

	private void writeFile(File file, String text) throws IOException {
		Files.writeFile(projectDirectory, file, text);
	}

	private void createLogo() {

		if (logoFile != null) {
			File logo = Files.addPaths(projectDirectory, logoFile);
			if (!logo.exists()) {
				JarFile.extractResource(HTMLMetricsProject.class
						.getResource("/config/logo.jpeg"), logo);
			}
		}
	}

	private void createCSS() {
		if (cssFile != null) {
			File css = Files.addPaths(projectDirectory, cssFile);
			if (!css.exists()) {
				JarFile.extractResource(HTMLMetricsProject.class
						.getResource("/css/report.css"), css);
			}
		}
	}

	public boolean createStartHTML(String name) {
		String html = HTMLStandards.getStandardHeader(name, cssFile, false);
		html += "<img src=\"graphics/logo.jpeg\"/> <h1>" + name + "</h1>";
		html += "<p>" + HTMLStandards.getCopyright() + "</p>";
		html += "<p>" + translator.i18n("For more information have a look to:")
				+ " " + Link.getPureSolTechnolgiesHomePage().toHTML() + "</p>";
		html += HTMLStandards.getStandardFooter();
		return Files.writeFile(projectDirectory, new File("start.html"), html);
	}
	
	public static void main(String args[]) {
		StandardReport standardReport = new StandardReport(new File("/home/ludwig/CodeAnalysis"));
		standardReport.setCssFile(new File("css/report.css"));
		standardReport.setLogoFile(new File("graphics/logo.jpeg"));
		standardReport.createStandardFiles();
	}
}
