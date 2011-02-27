package com.puresol.config.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationHomePersistence {

	public static void load(String directoryName,
			ConfigurationLayer configurationType) throws IOException {
		ConfigurationHomePersistence persistence = new ConfigurationHomePersistence(
				directoryName, configurationType);
		persistence.loadConfiguration();
	}

	public static void store(String directoryName,
			ConfigurationLayer configurationType) throws IOException {
		ConfigurationHomePersistence persistence = new ConfigurationHomePersistence(
				directoryName, configurationType);
		persistence.storeConfiguration();
	}

	private final String directoryName;
	private final ConfigurationLayer configurationType;

	private ConfigurationHomePersistence(String directoryName,
			ConfigurationLayer configurationType) {
		super();
		this.directoryName = directoryName;
		this.configurationType = configurationType;
	}

	private File getDirectory() {
		return new File(System.getProperty("user.home"), directoryName);
	}

	private void loadConfiguration() throws IOException {
		ConfigurationManager configurationManager = ConfigurationManager
				.getInstance(configurationType);
		File files[] = getDirectory().listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File directory, String name) {
				return name.endsWith(".properties");
			}
		});
		for (File file : files) {
			Properties properties = new Properties();
			InputStream inputStream = new FileInputStream(file);
			try {
				properties.load(inputStream);
				configurationManager.addProperties(
						file.getName().replace(".properties", ""), properties);
			} finally {
				inputStream.close();
			}
		}
	}

	private void storeConfiguration() throws IOException {
		ConfigurationManager configurationManager = ConfigurationManager
				.getInstance(configurationType);
		for (String context : configurationManager.getContexts()) {
			File file = new File(getDirectory(), context + ".properties");
			Properties properties = configurationManager
					.getContextProperties(context);
			FileOutputStream outputStream = new FileOutputStream(file);
			try {
				properties.store(outputStream,
						"Properties file for application context '" + context
								+ "'");
			} finally {
				outputStream.close();
			}
		}
	}
}
