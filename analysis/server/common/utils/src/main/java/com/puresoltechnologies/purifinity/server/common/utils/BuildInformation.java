package com.puresoltechnologies.purifinity.server.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import com.puresoltechnologies.versioning.Version;

public class BuildInformation {

    private static final Properties buildProperties = new Properties();
    static {
	try (InputStream stream = BuildInformation.class.getResourceAsStream("/META-INF/build.properties")) {
	    buildProperties.load(stream);
	} catch (IOException e) {
	    throw new RuntimeException("Could not read build.properties.", e);
	}
    }

    public static Version getVersion() {
	return Version.valueOf(buildProperties.get("project.version").toString());
    }

    public static String getInceptionYear() {
	return buildProperties.get("project.inceptionYear").toString();
    }

    public static LocalDateTime getBuildTimestamp() {
	String timestamp = buildProperties.get("project.buildYear").toString();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	return LocalDateTime.parse(timestamp, formatter);
    }

}
