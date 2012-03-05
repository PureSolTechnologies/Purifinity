/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ConfigFile is an object to handle ASCII configuration files with sections and
 * keys. <br/>
 * <br/>
 * 
 * A configuration file is an ASCII file with either Windows or Unix ending
 * characters. The file is organized in sections which start with a section
 * title. The section title is the name of the section in rectangular brackets,
 * e.g.: [SECTIONTITLE] <br/>
 * <br/>
 * 
 * In the section the values are organized as keys. Each key name is followed by
 * an equal sign '=' and the value for the key, e.g.: key=value <br/>
 * <br/>
 * A special section is a section with a vertical data table in it. For details
 * on that have a look to VerticalDataFile. In a section it is quite the same.
 * The table starts with a line of column titles and the rest are lines with
 * data. <br/>
 * <br/>
 * 
 * Comments are started with a sharp sign '#'.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ConfigFile {

    private static final Logger logger = LoggerFactory
	    .getLogger(ConfigFile.class);

    /**
     * This method removes comments by truncating the line after the sharp (#)
     * and removing all trailing and leading white spaces.
     * 
     * @param line
     *            is a String to be removed from comment and white spaces.
     * @return The cleaned string is returned.
     */
    static public String removeComment(String line) {

	String result = line;
	if (result.contains("#")) {
	    result = result.substring(0, result.indexOf("#"));
	}
	return result.trim();
    }

    /**
     * This is a File object keeping the path to the opened configuration file.
     */
    protected final File file;
    protected final RandomAccessFile raFile;

    /**
     * This is the constructor with a file object only. The file will be opened
     * read-only.
     * 
     * @param file
     *            is the file which is to open by the constructor.
     */
    public ConfigFile(File file) throws IOException {
	raFile = new RandomAccessFile(file, "r");
	this.file = file;
    }

    /**
     * This is the constructor with file object for the file to be opened and a
     * mode string defining the open mode.
     * 
     * @param file
     *            is the file which is to open by the constructor.
     * @param mode
     *            is the open mode string. It contains 'r' and/or 'w'.
     */
    public ConfigFile(File file, String mode) throws IOException {
	raFile = new RandomAccessFile(file, mode);
	this.file = file;
    }

    public void close() {
	try {
	    raFile.close();
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
    }

    /**
     * This method reads a single line readLine(), but trims the the leading and
     * trailing white spaces.
     * 
     * @return A String is returned representing the line read from the file.
     * @throws IOException
     *             is thrown in case of an IO error.
     */
    public String readLineTrimmed() throws IOException {
	String line = raFile.readLine();
	if (line == null) {
	    return null;
	}
	return removeComment(line);
    }

    /**
     * This methods starts at the beginning of the file and searches for a
     * section given as argument. If the section is found the file pointer
     * remains there.
     * 
     * @param name
     *            is the name of the section to look for.
     * @return True is returned in case the section was successfully found.
     *         Otherwise false is returned.
     */
    public boolean gotoSection(String name) {
	try {
	    raFile.seek(0);
	    String line = "";
	    while (!line.equals("[" + name + "]")) {
		line = readLineTrimmed();
		if (line == null) {
		    return false;
		}
	    }
	    return true;
	} catch (IOException ioe) {
	    return false;
	}
    }

    /**
     * This method searches for a section and counts the included lines of a
     * table without the column header line.
     * 
     * @param section
     *            is the name of the section read.
     * @return The number of data lines in the table is returned without
     *         counting the line with the column headers.
     */
    public int getTableLength(String section) {
	if (!gotoSection(section)) {
	    return 0;
	}
	int length = 0;
	try {
	    String line;
	    while ((line = readLineTrimmed()) != null) {
		if (line.startsWith("[")) {
		    break;
		}
		if (line.equals("")) {
		    continue;
		}
		if (line.startsWith("#")) {
		    continue;
		}
		length++;
	    }
	} catch (IOException ioe) {
	    return 0;
	}
	return length - 1; // -1 because of headerline
    }

    /**
     * This method reads the complete section into a string.
     * 
     * @param section
     * @return
     * @throws IOException
     */
    public String read(String section) throws IOException {
	if (!gotoSection(section)) {
	    return "";
	}
	String result = "";
	String line = raFile.readLine();
	while ((line != null) && (!line.startsWith("["))) {
	    result += line;
	    line = raFile.readLine();
	}
	return result;
    }

    /**
     * read() is the method to extract an entry from a configuration file.
     * 
     * @throws IOException
     *             is thrown in case of an IO error.
     * @param section
     *            is the name of the section where the entry must be found.
     * @param entry
     *            is the name of the entry which is to be found.
     * @return A string with the keys content is returned. If the key was not
     *         found, null is returned.
     */
    public String read(String section, String entry) throws IOException {
	if (!gotoSection(section)) {
	    return null;
	}
	String line;
	String result = null;
	while ((line = readLineTrimmed()) != null) {
	    if (line.startsWith("[")) {
		break;
	    } else if (line.startsWith(entry + "=")) {
		result = line;
		break;
	    }
	}
	if (line == null) {
	    return null;
	}
	if (!line.startsWith(entry + "=")) {
	    return null;
	}
	return result.substring(entry.length() + 1);
    }

    /**
     * This method writes a single section header to the already opened file.
     * The method only add rectangular brackets to the name and writes the line
     * into the file.
     * 
     * @param section
     *            is the name of the section the header is to be written.
     * @throws IOException
     *             is thrown in case of an IO error.
     */
    public void writeSection(String section) throws IOException {
	raFile.writeBytes("[" + section + "]\n");
    }

    /**
     * This mothod write a single key value pair into the file. The key name and
     * the key's value is specified. There is only an equal sign '=' added in
     * between and the string is written afterwards as a line into the file. To
     * be compatible a section header must be written before the key to have a
     * valid section and key assignment.
     * 
     * @param key
     *            is the name of the key to be written.
     * @param value
     *            is the key's value.
     * @throws IOException
     *             is thrown in case of an IO error.
     */
    public void writeEntry(String key, String value) throws IOException {
	raFile.writeBytes(key + "=" + value + "\n");
    }

    /**
     * This method just returns the current file's File object.
     * 
     * @return A file object is returned pointing the the currently opened file.
     */
    public File getFile() {
	return file;
    }

    /**
     * This method reads in a single(!) file the entry for a configuration key.
     * 
     * @param file
     *            is the file to be read. This is the complete file path to be
     *            read.
     * @param section
     *            is the name of the section where the entry must be found.
     * @param entry
     *            is the name of the entry which is to be found.
     * @return A string with the keys content is returned. If the key was not
     *         found, null is returned.
     */
    static private String readEntry(File file, String section, String entry) {
	if (!file.exists()) {
	    return "";
	}
	try {
	    ConfigFile configFile = new ConfigFile(file);
	    String line = configFile.read(section, entry);
	    configFile.close();
	    if (line == null) {
		return "";
	    }
	    return line;
	} catch (FileNotFoundException fnfe) {
	    return "";
	} catch (IOException ioe) {
	    return "";
	}
    }

    /**
     * This method returns a list of all potential configuration files which
     * could be available. The list of files is generated by using filename as
     * its path's end and replaceing the leading part with standard locations.
     * 
     * @param filename
     *            is the last part of the file path including the last
     *            directories and the file name.
     * @return
     */
    public static List<File> getPotentialConfigFiles(String filename) {
	List<File> files = new ArrayList<File>();
	files.add(new File("/etc/", filename));
	files.add(new File(System.getProperty("user.home"), filename));
	files.add(new File(System.getProperty("user.home"), "." + filename));
	files.add(new File(System.getProperty("user.dir"), filename));
	files.add(new File(System.getProperty("user.dir"), "." + filename));
	return files;
    }

    /**
     * This method returns a list of all available configuration files. The list
     * is generated by getPotentialConfigFiles().
     * 
     * @param filename
     *            is the last part of the file path including the last
     *            directories and the file name.
     * @return
     */
    public static List<File> getAvailableConfigFiles(String filename) {
	List<File> files = getPotentialConfigFiles(filename);
	List<File> availableFiles = new ArrayList<File>();
	for (File file : files) {
	    if (file.exists()) {
		availableFiles.add(file);
	    }
	}
	return availableFiles;
    }

    /**
     * readEntry is a static method to read configuration file entries in a very
     * easy (but not so efficient) way.
     * 
     * @param filename
     *            is the short path to the file to be read. Short path means you
     *            only have to specify the configuration directory and the
     *            filename. Suffixes "/etc/", "~/.", "./." and "./" are put
     *            before automatically.
     * @param section
     *            is the name of the section where the entry must be found.
     * @param entry
     *            is the name of the entry which is to be found.
     * @return A string with the keys content is returned. If the key was not
     *         found, null is returned.
     */
    static public String readEntry(String filename, String section, String entry) {
	URL url = ConfigFile.class.getResource(filename);
	if (url != null) {
	    String file = url.getFile();
	    return readEntry(new File(file), section, entry);
	}
	String result = "";
	String line;
	List<File> files = getAvailableConfigFiles(filename);
	for (File file : files) {
	    line = readEntry(file, section, entry);
	    if (!line.isEmpty()) {
		result = line;
	    }
	}
	return result;
    }

    static public String readSection(InputStream stream, String section)
	    throws IOException {
	if (stream == null) {
	    throw new IllegalArgumentException("stream must not be null!");
	}
	StringBuffer result = new StringBuffer();
	boolean inside = false;
	String line;
	BufferedReader reader = new BufferedReader(
		new InputStreamReader(stream));
	do {
	    line = reader.readLine();
	    if (line == null) {
		continue;
	    }
	    line = removeComment(line);
	    if (line.isEmpty()) {
		continue;
	    }
	    if (line.startsWith("[") && line.endsWith("]")) {
		if (inside) {
		    break;
		} else if (line.equals("[" + section + "]")) {
		    inside = true;
		}
		continue;
	    }
	    if (inside) {
		result.append(line + "\n");
	    }
	} while (line != null);
	reader.close();
	return result.toString();
    }

    static public String readSection(File file, String section) {
	InputStream stream = null;
	try {
	    stream = new FileInputStream(file);
	    String result = readSection(stream, section);
	    return result;
	} catch (IOException e) {
	    return "";
	} finally {
	    if (stream != null) {
		try {
		    stream.close();
		} catch (IOException e) {
		}
	    }
	}
    }

    static public String readSection(String resource, String section) {
	InputStream stream = null;
	try {
	    stream = ConfigFile.class.getClassLoader().getResourceAsStream(
		    resource);
	    if (stream != null) {
		return readSection(stream, section);
	    }
	    List<File> files = getAvailableConfigFiles(resource);
	    if (files.size() == 0) {
		return "";
	    }
	    // use last file...
	    return readSection(files.get(files.size() - 1), section);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} finally {
	    if (stream != null) {
		try {
		    stream.close();
		} catch (IOException e) {
		}
	    }
	}
    }

    /**
     * This method reads the content of the file completely into a ConfigHash.
     * 
     * @return A ConfigHash is returned containing all information in the config
     *         file.
     */
    static public ConfigHash readToHash(File file) {
	if (file == null) {
	    throw new IllegalArgumentException("file must not be null!");
	}
	if (!file.exists()) {
	    return new ConfigHash();
	}
	InputStream inStream = null;
	try {
	    inStream = new FileInputStream(file);
	    ConfigHash hash = readToHash(inStream);
	    inStream.close();
	    return hash;
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	} finally {
	    if (inStream != null) {
		try {
		    inStream.close();
		} catch (IOException e) {
		}
	    }
	}
    }

    static public ConfigHash readToHash(InputStream stream) throws IOException {
	if (stream == null) {
	    throw new IllegalArgumentException("stream must not be null!");
	}
	ConfigHash hash = new ConfigHash();
	String line;
	String section = "";
	BufferedReader reader = new BufferedReader(
		new InputStreamReader(stream));
	do {
	    line = reader.readLine();
	    if (line == null) {
		continue;
	    }
	    line = removeComment(line);
	    if (line.isEmpty()) {
		continue;
	    }
	    if (line.startsWith("[") && line.endsWith("]")) {
		section = line.substring(1, line.length() - 1);
		logger.trace("Found section '" + section + "'");
		continue;
	    }
	    if (section.equals("")) {
		continue;
	    }
	    if (!line.contains("=")) {
		continue;
	    }
	    String key = line.substring(0, line.indexOf("="));
	    if (key.equals("")) {
		continue;
	    }
	    String value = line.substring(line.indexOf("=") + 1);
	    if (value.equals("")) {
		continue;
	    }
	    if (!hash.containsKey(section)) {
		hash.put(section, new Hashtable<String, String>());
	    }
	    hash.get(section).put(key, value);
	    logger.trace("key: '" + key + "=" + value + "'");
	} while (line != null);
	return hash;
    }
}
