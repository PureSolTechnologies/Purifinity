package com.puresoltechnologies.purifinity.server.core.impl.config;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;

/**
 * This class provides central access to Purifinity configuration.
 * 
 * @author Rick-Rainer Ludwig
 */
@Singleton
@Startup
public class PurifinityConfiguration {

	@Inject
	private Logger logger;

	private final File purifinityHome;
	private final File configDirectory;
	private final File pluginDirectory;

	public PurifinityConfiguration() {
		purifinityHome = new File(System.getProperty("purifinity.home"))
				.getAbsoluteFile();
		configDirectory = new File(purifinityHome, "config");
		pluginDirectory = new File(purifinityHome, "plugins");
	}

	@PostConstruct
	public void checkConfiguration() {
		checkDirectory(purifinityHome);
		checkDirectory(configDirectory);
		checkDirectory(pluginDirectory);
	}

	private void checkDirectory(File directory) {
		if (directory.exists()) {
			logger.info("Directory '" + directory + "' exists.");
			if (!directory.isDirectory()) {
				throw new IllegalStateException("The provided directory '"
						+ directory + "' is not a directory.");
			}
			if (!directory.canRead()) {
				throw new IllegalStateException("The provided directory '"
						+ directory + "' is not readable.");
			}
			if (!directory.canWrite()) {
				throw new IllegalStateException("The provided directory '"
						+ directory + "' is not readable.");
			}
		} else {
			logger.info("Directory '" + directory + "' does not exist.");
			if (!directory.mkdirs()) {
				throw new RuntimeException("Could not create directory '"
						+ directory + "'.");
			}
		}
	}

	/**
	 * Returns the Purifinity home directory.
	 * 
	 * @return A {@link File} is returned.
	 */
	public File getPurifinityHome() {
		return purifinityHome;
	}

	/**
	 * Returns the Purifinity configuration directory.
	 * 
	 * @return A {@link File} is returned.
	 */
	public File getConfigDirectory() {
		return configDirectory;
	}

	/**
	 * Returns the Purifinity plugin directory.
	 * 
	 * @return A {@link File} is returned.
	 */
	public File getPluginDirectory() {
		return pluginDirectory;
	}

}
