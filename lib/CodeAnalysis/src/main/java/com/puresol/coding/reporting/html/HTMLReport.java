package com.puresol.coding.reporting.html;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.i18n4java.Translator;

import com.puresol.reporting.html.HTMLStandards;
import com.puresol.reporting.html.Image;
import com.puresol.reporting.html.Link;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PathResolutionException;

class HTMLReport {

	private static final Translator translator = Translator
			.getTranslator(HTMLReport.class);

	private final File file;
	private final File cssFile;
	private final File logoFile;
	private final File favIconFile;
	private final FileWriter writer;
	private boolean copyrightFooter = false;

	public HTMLReport(File file, File cssFile, File logoFile, File favIconFile,
			String title) throws IOException {
		this.file = file;
		this.cssFile = cssFile;
		this.logoFile = logoFile;
		this.favIconFile = favIconFile;
		File directory = file.getParentFile();
		if (!directory.exists()) {
			if (!directory.mkdirs()) {
				throw new IOException("Could not create directory '"
						+ directory + "'!");
			}
		} else {
			if (!directory.isDirectory()) {
				throw new IOException("'" + directory
						+ "' should be a directory!!");
			}
		}
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

	public File getFile() {
		return file;
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

	public String createLogoIMGTag() throws IOException {
		try {
			return new Image(new File(FileUtilities.getRelativePath(
					file.getPath(), logoFile.getParent(), File.separator)))
					.toHTML();
		} catch (PathResolutionException e) {
			throw new IOException(e);
		}
	}

	public String createLogoIMGTag(int widht, int height, boolean relativeSize)
			throws IOException {
		try {
			return new Image(new File(FileUtilities.getRelativePath(
					file.getPath(), logoFile.getPath(), File.separator)),
					widht, height, relativeSize).toHTML();
		} catch (PathResolutionException e) {
			throw new IOException(e);
		}
	}

	private void open(String title) throws IOException {
		try {
			String output = HTMLStandards.getStandardHeader(
					title,
					new File(FileUtilities.getRelativePath(file.getPath(),
							cssFile.getPath(), File.separator)),
					false,
					new File(FileUtilities.getRelativePath(file.getPath(),
							favIconFile.getPath(), File.separator)));
			output += createLogoIMGTag(400, 0, false);
			output += "<h1>" + title + "</h1>";
			writer.write(output);
		} catch (PathResolutionException e) {
			throw new IOException(e);
		}
	}

	public void close() throws IOException {
		if (copyrightFooter) {
			writer.write(HTMLStandards.getStandardCopyrightFooter());
		} else {
			writer.write(HTMLStandards.getStandardFooter());
		}
		writer.close();
	}

	public String createCopyrightMessage() throws IOException {
		String name = translator.i18n("Copyright Information");
		String html = HTMLStandards.getStandardHeader(name, cssFile, false,
				favIconFile);
		html += createLogoIMGTag(400, 0, false) + "<h1>" + name + "</h1>";
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
		return html;
	}

	public void write(String html) throws IOException {
		writer.write(html);
	}

}
