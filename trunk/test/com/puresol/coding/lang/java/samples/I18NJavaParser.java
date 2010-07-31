/***************************************************************************
 *
 *   I18NJavaParser.java
 *   -------------------
 *   copyright            : (c) 2009 by Rick-Rainer Ludwig
 *   author               : Rick-Rainer Ludwig
 *   email                : rl719236@sourceforge.net
 *
 ***************************************************************************/

/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package com.puresol.coding.lang.java.samples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Pattern;

import javax.i18n4java.data.MultiLanguageTranslations;

import org.apache.log4j.Logger;

/**
 * This object reads Java files and searches for I18N strings which can be
 * collected afterwards in an I18N file for translation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class I18NJavaParser {

	/**
	 * This method is used to parse a file for I18N Strings.
	 * 
	 * @param file
	 *            to be read and processed.
	 * @return A Vector of all I18N strings is returned.
	 * @throws FileNotFoundException
	 */
	static public MultiLanguageTranslations parseFile(File file)
			throws FileNotFoundException {
		I18NJavaParser parser = new I18NJavaParser(file);
		return parser.parse();
	}

	private File file = null;
	private String packageName = "";
	private Logger logger = null;

	static private final Pattern I18N_PATTERN = Pattern
			.compile(".*i18n\\s*\\(.*");
	static private final Pattern I18N_PATTERN_2 = Pattern
			.compile(".*\"i18n:.*");

	private I18NJavaParser(File file) {
		this.file = file;
		logger = Logger.getLogger(I18NJavaParser.class);
	}

	/**
	 * This is the internal method started for processing.
	 * 
	 * @param file
	 *            is the file to be processed.
	 * @return A Vector of all I18N strings is returned.
	 * @throws FileNotFoundException
	 */
	private MultiLanguageTranslations parse() throws FileNotFoundException {
		RandomAccessFile f = new RandomAccessFile(file, "r");
		return readFileAndPrepareData(f);
	}

	/**
	 * This method reads a whole file, trims the lines and adds everything to
	 * one String object.
	 * 
	 * @param file
	 *            is the file to read.
	 * @return A String is returned containing the content of the file reduced
	 *         by its end of lines.
	 * @throws FileNotFoundException
	 */
	private MultiLanguageTranslations readFileAndPrepareData(RandomAccessFile f) {
		MultiLanguageTranslations translations = new MultiLanguageTranslations();
		try {
			String line;
			StringBuffer buffer = null;
			int lineNumber = 0;
			while ((line = readLineWithoutComments(f)) != null) {
				buffer = new StringBuffer(line);
				lineNumber++;
				int startLineNumber = lineNumber;
				while (buffer.toString().endsWith("\"")
						|| buffer.toString().endsWith("+")
						|| buffer.toString().endsWith(",")) {
					String nextLine = readLineWithoutComments(f);
					lineNumber++;
					if (nextLine == null) {
						throw new IOException("Unexpected end of file!");
					}
					buffer.append(nextLine);
				}
				// remove string appends: '" + "' --> ""
				line = buffer.toString().replaceAll("\"\\s*\\+\\s*\"", "");
				if (line.contains("package")) {
					packageName = extractPackageName(line);
					continue;
				}
				if (line.isEmpty()) {
					continue;
				}
				translations
						.add(extractI18N(line, startLineNumber, lineNumber));
			}
			return translations;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return translations;
		}
	}

	private String readLineWithoutComments(RandomAccessFile f)
			throws IOException {
		String line;
		line = f.readLine();
		if (line != null) {
			// remove trailing comments '//...' with various spaces
			line = line.replaceAll("\\/\\/.*$", "");
			line = line.trim();
		}
		return line;
	}

	private String extractPackageName(String line) {
		String name = line.replaceAll("^.*package\\s*", "");
		return name.replaceAll("\\s*;\\s*$", "");
	}

	private MultiLanguageTranslations extractI18N(String line, int startLine,
			int endLine) {
		MultiLanguageTranslations translations = new MultiLanguageTranslations();
		if ((!I18N_PATTERN.matcher(line).matches())
				&& (!I18N_PATTERN_2.matcher(line).matches())) {
			return translations;
		}
		// remove all context parameters...
		line = line.replaceAll("i18n\\s*\\(\\s*[^\",]+\\s*,\\s*\"", "i18n(\"");
		// find "i18n:..." constructions and convert them to i18n("...")
		line = line.replaceAll("\"i18n:", "i18n(\"");
		// split at 'i18n("'
		String[] i18ns = line.split("i18n\\s*\\(\\s*\"");
		for (int index = 1; index < i18ns.length; index++) {
			String source = extractStringFromStartToEnd("\"" + i18ns[index]);
			if (source != null) {
				translations.add(MultiLanguageTranslations.from(
						source.replaceAll("\\\\n", "\n"), packageName + "."
								+ file.getName(), startLine));
			}
		}
		return translations;
	}

	/**
	 * This method takes a string which directly starts with an I18N string and
	 * looks for the right quotation mark as end of the I18N string. The first
	 * quotation with an even number of backslashes before it is the right end
	 * of I18N string.
	 * 
	 * @param line
	 *            to be processed.
	 * @return The correct I18N String is returned.
	 */
	private String extractStringFromStartToEnd(String line) {
		if (!line.startsWith("\"")) {
			// remove context parameter if present
			line = line.replaceAll("^[^,\\)]*,\\s*", "");
			if (!line.startsWith("\"")) {
				// not a String constant found with starting "
				// maybe a variable is used here, nothing to translate...
				return null;
			}
		}
		line = line.replaceAll("^\"", "");
		int bsCount = 0; // backslash counter
		for (int index = 0; index < line.length(); index++) {
			if (line.charAt(index) == '\"') {
				if ((bsCount % 2) == 0) {
					return line.substring(0, index);
				}
			}
			if (line.charAt(index) == '\\') {
				bsCount++;
			} else {
				bsCount = 0;
			}
		}
		return null;
	}
}
