package com.puresoltechnologies.purifinity.server.common.configuration;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class PurifinityConfiguration {

    private File homeDirectory = null;
    private File confDirectory = null;

    @PostConstruct
    public void readConfiguration() {
	String homeDirectoryString = System.getProperty("purifinity.directory.base");
	if ((homeDirectoryString == null) || (homeDirectoryString.isEmpty())) {
	    throw new IllegalStateException("System property 'purifinity.directory.base' is missing.");
	}
	homeDirectory = new File(homeDirectoryString);
	if ((!homeDirectory.exists()) || (!homeDirectory.isDirectory())) {
	    throw new IllegalStateException(
		    "Purifinity home directory '" + homeDirectory + "' does not exist or is not a directory.");
	}
	confDirectory = new File(homeDirectoryString, "etc");
	if ((!confDirectory.exists()) || (!confDirectory.isDirectory())) {
	    throw new IllegalStateException(
		    "Configuration directory '" + confDirectory + "' does not exist or is not a directory.");
	}
    }

    public File getHomeDir() {
	return homeDirectory;
    }

    public File getConfigDir() {
	return confDirectory;
    }

}
