package com.puresoltechnologies.debugging.agent;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

public class Configuration {

    private static File directory = new File(".");
    private static final Properties properties = new Properties();

    public static void initialize(String agentArgs) {
	try (ByteArrayInputStream inputStream = new ByteArrayInputStream(
		agentArgs.replace(',', '\n').getBytes(Charset.defaultCharset()))) {
	    properties.load(inputStream);
	    String directoryProperty = properties.getProperty("directory");
	    if (directoryProperty != null) {
		directory = new File(directoryProperty);
	    }
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}
    }

    public static File getDirectory() {
	return directory;
    }
}
