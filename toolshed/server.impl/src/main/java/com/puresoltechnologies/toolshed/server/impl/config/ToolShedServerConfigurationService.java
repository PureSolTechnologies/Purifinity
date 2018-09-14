package com.puresoltechnologies.toolshed.server.impl.config;

import java.io.File;

public class ToolShedServerConfigurationService {

    private static String toolShedHome = null;
    private static File toolShedHomeDirectory = null;
    private static ToolShedServerConfiguration configuration = null;

    public static void initialize(ToolShedServerConfiguration configuration) {
	toolShedHome = System.getProperty("toolshed.home");
	if (toolShedHome == null) {
	    throw new IllegalStateException("Property 'toolshed.home' not set.");
	}
	toolShedHomeDirectory = new File(toolShedHome);
	if (!toolShedHomeDirectory.exists()) {
	    throw new IllegalStateException("Toolshed home '" + toolShedHomeDirectory + "' does not exist.");
	}
	if (!toolShedHomeDirectory.isDirectory()) {
	    throw new IllegalStateException("Toolshed home '" + toolShedHomeDirectory + "' is not a directory.");
	}
	ToolShedServerConfigurationService.configuration = configuration;
    }

    public static String getToolShedHome() {
	return toolShedHome;
    }

    public static File getToolShedHomeDirectory() {
	return toolShedHomeDirectory;
    }

    public static ToolShedServerConfiguration getServerConfiguration() {
	return configuration;
    }

    private ToolShedServerConfigurationService() {
    }

}
