package org.apache.cassandra.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CassandraConfiguration {

	private static final String VARIABLE_PATTERN = "\\$\\{([\\w.-]+)\\}";
	static final Pattern pattern = Pattern.compile(VARIABLE_PATTERN);

	public static File createConfigurationFile(File targetDirectory)
			throws FileNotFoundException, IOException {
		if (!targetDirectory.exists()) {
			throw new IOException("Target directory '" + targetDirectory
					+ "' for configuration does not exist.");
		}
		File configurationFile = new File(targetDirectory, "cassandra.yaml");
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
		try (InputStream template = CassandraConfiguration.class
				.getResourceAsStream("/cassandra.yaml.template");
				InputStreamReader inputStreamReader = new InputStreamReader(
						template);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				FileWriter writer = new FileWriter(configurationFile)) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);
				while (matcher.find()) {
					String propertyName = matcher.group(1);
					line = line.replaceAll("\\$\\{" + propertyName + "\\}",
							Matcher.quoteReplacement(System.getProperty(propertyName)));
				}
				writer.write(line);
				writer.write("\n");
			}
		}
		return configurationFile;
	}
}
