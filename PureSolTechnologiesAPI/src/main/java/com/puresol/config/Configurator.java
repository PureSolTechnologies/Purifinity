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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * This object handles the connectivity to all configuration files and assures,
 * that all configuration files are only read once and that the information is
 * statically stored in memory for usage of all other objects.
 * 
 * Reading configuration files is very time consuming and this reading should be
 * performed only when it's needed and only once per file! For an efficient
 * handling of the data this object is designed as a singleton pattern.
 * Therefore, only one instance is kept in the memory and all configuration
 * files read with this object are only read once and only one copy of the date
 * is stored in memory.
 * 
 * @author Rick-Rainer Ludwig
 */
public class Configurator {

	private static final Logger logger = Logger.getLogger(Configurator.class);

	/**
	 * This is the private instance variable for the one created instance.
	 */
	private static Configurator instance;

	/**
	 * This static variable keeps all information of all configuration files.
	 * This variable is static because of the wish, that each configuration file
	 * is only read once. Reading files is very time consuming.
	 */
	private static volatile Map<String, ConfigHash> configuratorHash = new Hashtable<String, ConfigHash>();

	/**
	 * This is a standard configurator with default values. It's declared as
	 * private for the singleton pattern to be used.
	 */
	private Configurator() {
	}

	/**
	 * This method is used internally in the case the configuration file needed
	 * was not loaded yet. This method uses the ConfigFile obeject to read the
	 * whole file into a ConfigHash.
	 * 
	 * @param file
	 *            is the short file path.
	 * @throws ConfigException
	 *             is thrown in case of config read file failures.
	 */
	private boolean loadResource(String resource) {
		logger.debug("Load resource '" + resource + "'");
		InputStream inStream = getClass().getResourceAsStream(resource);
		if (inStream == null) {
			inStream = getClass().getResourceAsStream("/" + resource);
		}
		if (inStream == null) {
			logger.debug("Resource '" + resource + "' not found!");
			return false;
		}
		logger.debug("Reading...");
		return load(resource, inStream);
	}

	private boolean load(String resource, InputStream inStream) {
		try {
			configuratorHash.get(resource).putAll(
					ConfigFile.readToHash(inStream));
			return true;
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * This method reads the configuration in the appropriate order. At first
	 * it's looked for a file within the CLASSPATH. If this file is found no
	 * other directories are searched due to the assumption that this is the one
	 * and only package or distribution configuration.
	 * 
	 * If it's not a resource (the file is not in the CLASSPATH) the directories
	 * '/etc', 'user.home', 'user.home/.', 'user.dir' and 'user.dir/.' are
	 * searched.
	 * 
	 * If a file is present several times, the entry's last version is used.
	 * 
	 * @param resource
	 *            is the short file path.
	 * @throws ConfigException
	 *             is thrown in case of config read file failures.
	 */
	private void readAll(String resource, boolean firstValid)
			throws ConfigException {
		logger.debug("Try to read information from resource '" + resource + "'");
		if (!configuratorHash.containsKey(resource)) {
			configuratorHash.put(resource, new ConfigHash());
		}
		boolean found = loadResource(resource);
		if ((!found) || (!firstValid)) {
			List<File> files = ConfigFile.getAvailableConfigFiles(resource);
			for (File configFile : files) {
				try {
					found |= load(resource, new FileInputStream(configFile));
				} catch (FileNotFoundException e) {
					logger.debug("File '" + configFile
							+ "' not found. Skipping.");
				}
				if (found && (!firstValid)) {
					break;
				}
			}
		}
		if (!found) {
			logger.warn("Could no read configuration file '" + resource + "'");
			throw new ConfigException("Could not read configuration file '"
					+ resource + "'!");
		}
	}

	/**
	 * This method is for internal use only and it reads the configHash and
	 * returns the value specified by file, section and key. If the specified
	 * file was not read before the file's content will be read and included
	 * into the configHash.
	 * 
	 * @param resource
	 *            is the short path to the configuration file. The reading will
	 *            be performed on different places regarding to the OS.
	 * @param section
	 *            is the section in the file which contains the needed key.
	 * @param key
	 *            is the key name to be read and returned.
	 * @return A String is returned containing the value of the key.
	 */
	private String readEntry(String resource, String section, String key,
			boolean firstValid) {
		try {
			if (!configuratorHash.containsKey(resource)) {
				readAll(resource, firstValid);
			}
		} catch (ConfigException e) {
			logger.warn(e.getMessage(), e);
			return "";
		}
		ConfigHash ch = configuratorHash.get(resource);
		if (ch == null) {
			logger.warn("Resource '" + resource + "' is not available!");
			return "";
		}
		Hashtable<String, String> sh = ch.get(section);
		if (sh == null) {
			logger.warn("Section '" + section + "' in resource '" + resource
					+ "' is not available!");
			return "";
		}
		String result = sh.get(key);
		logger.trace(resource + ":" + section + "/" + key + " = " + result);
		return result;
	}

	/**
	 * This method reads the configHash and returns the value specified by file,
	 * section and key. If the specified file was not read before the file's
	 * content will be read and included into the configHash.
	 * 
	 * @param resource
	 *            is the short path to the configuration file. The reading will
	 *            be performed on different places regarding to the OS.
	 * @param section
	 *            is the section in the file which contains the needed key.
	 * @param key
	 *            is the key name to be read and returned.
	 * @return A String is returned containing the value of the key.
	 */
	public static String getEntry(String resource, String section, String key,
			boolean firstValid) {
		return getInstance().readEntry(resource, section, key, firstValid);
	}

	/**
	 * 
	 * @param resource
	 *            is the short path to the configuration file. The reading will
	 *            be performed on different places regarding to the OS.
	 * @param firstValid
	 * @return
	 */
	private ConfigHash returnResource(String resource,
			boolean firstValid) {
		try {
			if (!configuratorHash.containsKey(resource)) {
				readAll(resource, firstValid);
			}
		} catch (ConfigException ce) {
			return null;
		}
		return configuratorHash.get(resource);
	}

	/**
	 * 
	 * @param resource
	 *            is the short path to the configuration file. The reading will
	 *            be performed on different places regarding to the OS.
	 * @param firstValid
	 * @return
	 */
	public static ConfigHash getResource(String resource,
			boolean firstValid) {
		return getInstance().returnResource(resource, firstValid);
	}

	/**
	 * This method returns a newly created instance for the singleton.
	 */
	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new Configurator();
		}
	}

	/**
	 * This is the method to get a reference to the Configurator instance which
	 * is created only once. This is the only way to get access to this class,
	 * because this class was designed in singleton pattern.
	 * 
	 * @return The reference to the Configurator class is returned.
	 */
	public static synchronized Configurator getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}
}
