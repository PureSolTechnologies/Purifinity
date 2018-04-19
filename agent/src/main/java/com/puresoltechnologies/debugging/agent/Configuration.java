package com.puresoltechnologies.debugging.agent;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.regex.Pattern;

public class Configuration {

    private static File directory = new File(".");
    private static File outputDirectory = null;
    private static Pattern filterPattern = null;
    private static final Properties properties = new Properties();

    public static void initialize(String agentArgs) {
	if (agentArgs == null) {
	    agentArgs = "";
	}
	try (ByteArrayInputStream inputStream = new ByteArrayInputStream(
		agentArgs.replace(',', '\n').getBytes(Charset.defaultCharset()))) {
	    properties.load(inputStream);
	    String directoryProperty = properties.getProperty("directory");
	    if (directoryProperty != null) {
		directory = new File(directoryProperty);
	    }
	    initializeOutputDirectory();
	    String filter = properties.getProperty("filter");
	    if (filter != null) {
		filterPattern = Pattern.compile(filter);
	    }
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    private static void initializeOutputDirectory() throws IOException {
	File profileDirectory = new File(directory, "purifinity.profile");
	outputDirectory = new File(profileDirectory, String.valueOf(System.currentTimeMillis()));
	if (!outputDirectory.exists()) {
	    if (!outputDirectory.mkdirs()) {
		throw new IOException("Cannot create directory '" + outputDirectory + "'.");
	    }
	} else if (!outputDirectory.isDirectory()) {
	    throw new IOException("Output directory '" + outputDirectory + "' is not a directory.");
	}
	System.out.println("Output directory: " + outputDirectory);
    }

    public static File getDirectory() {
	return directory;
    }

    public static File getOutputDirectory() {
	return outputDirectory;
    }

    public static Pattern getFilterPattern() {
	return filterPattern;
    }

    public static boolean hasFilterPattern() {
	return filterPattern != null;
    }
}
