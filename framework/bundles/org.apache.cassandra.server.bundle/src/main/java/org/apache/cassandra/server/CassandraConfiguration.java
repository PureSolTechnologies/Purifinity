package org.apache.cassandra.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CassandraConfiguration {

	private static final String VARIABLE_PATTERN = "\\$\\{([\\w.-]+)\\}";
	static final Pattern pattern = Pattern.compile(VARIABLE_PATTERN);

	/**
	 * This method creates a new cassandra.yaml file.
	 * 
	 * @param configurationDirectory
	 *            is the configuration directory.
	 * @return A {@link File} is returned pointing to the newly created YAML
	 *         file.
	 * @throws IOException
	 */
	public static File createConfigurationFile(File configurationDirectory)
			throws IOException {
		checkTargetDirectory(configurationDirectory);
		File configurationFile = new File(configurationDirectory,
				"cassandra.yaml");
		recreateEmptyConfigurationFile(configurationFile);
		try (InputStream template = CassandraConfiguration.class
				.getResourceAsStream("/cassandra.yaml.template");
				InputStreamReader inputStreamReader = new InputStreamReader(
						template);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				FileWriter writer = new FileWriter(configurationFile)) {
			copySettingsAndReplaceProperties(bufferedReader, writer);
		}
		return configurationFile;
	}

	/**
	 * This method creates a new log4j-server.properties file.
	 * 
	 * @param configurationDirectory
	 *            is the configuration directory.
	 * @return A {@link File} is returned pointing to the newly created
	 *         properties file.
	 * @throws IOException
	 */
	public static File createLogSettingsFile(File configurationDirectory)
			throws IOException {
		checkTargetDirectory(configurationDirectory);
		File configurationFile = new File(configurationDirectory,
				"log4j-server.properties");
		recreateEmptyConfigurationFile(configurationFile);
		try (InputStream template = CassandraConfiguration.class
				.getResourceAsStream("/log4j-server.properties.template");
				InputStreamReader inputStreamReader = new InputStreamReader(
						template);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				FileWriter writer = new FileWriter(configurationFile)) {
			copySettingsAndReplaceProperties(bufferedReader, writer);
		}
		return configurationFile;
	}

	private static void copySettingsAndReplaceProperties(
			BufferedReader bufferedReader, FileWriter writer)
			throws IOException {
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			Matcher matcher = pattern.matcher(line);
			while (matcher.find()) {
				String propertyName = matcher.group(1);
				line = line.replaceAll("\\$\\{" + propertyName + "\\}", Matcher
						.quoteReplacement(System.getProperty(propertyName)));
			}
			writer.write(line);
			writer.write("\n");
		}
	}

	private static void recreateEmptyConfigurationFile(File configurationFile)
			throws IOException {
		if (configurationFile.exists()) {
			if (!configurationFile.delete()) {
				throw new IOException(
						"Could not delete old configuration file '"
								+ configurationFile + "'.");
			}
		}
		if (!configurationFile.createNewFile()) {
			throw new IOException("Could not create new configuration file '"
					+ configurationFile + "'.");
		}
	}

	private static void checkTargetDirectory(File configurationDirectory)
			throws IOException {
		if (!configurationDirectory.exists()) {
			throw new IOException("Target directory '" + configurationDirectory
					+ "' for configuration does not exist.");
		}
	}
}
