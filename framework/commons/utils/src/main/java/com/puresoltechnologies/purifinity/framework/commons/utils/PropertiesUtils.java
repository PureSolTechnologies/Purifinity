package com.puresoltechnologies.purifinity.framework.commons.utils;

import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesUtils {

    public static String toString(Properties properties) {
	StringBuilder builder = new StringBuilder();
	for (Entry<Object, Object> entry : properties.entrySet()) {
	    if (builder.length() > 0) {
		builder.append("\n");
	    }
	    builder.append(entry.getKey()).append("=").append(entry.getValue());
	}
	return builder.toString();
    }

    public static Properties fromString(String string) {
	String[] entries = string.split("\n");
	Properties properties = new Properties();
	for (String entry : entries) {
	    String[] splitEntry = entry.split("=", 2);
	    if (splitEntry.length != 2) {
		throw new IllegalArgumentException("Argument '" + string
			+ "' is not a valid Properties string.");
	    }
	    properties.setProperty(splitEntry[0], splitEntry[1]);
	}
	return properties;
    }

}
