package com.puresoltechnologies.purifinity.server.plugin.filesystem;

import java.net.MalformedURLException;
import java.net.URL;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.versioning.Version;

public class DirectoryRepositoryPlugin {

	private static final String ID = DirectoryRepositoryPlugin.class.getName();
	private static final String NAME = "Directory Repository Plugin";
	private static final Version VERSION = BuildInformation.getVersion();
	private static final String DESCRIPTION = "Contains the repository support for simple directories.";
	private static final String VENDOR = "PureSol Technologies";
	private static final URL VENDOR_URL;
	static {
		try {
			VENDOR_URL = new URL("http://puresol-technologies.com");
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not create vendor URL.", e);
		}
	}
	private static final String PATH_TO_UI = "/repository.directory.ui";

	public static final PluginInformation INFORMATION = new PluginInformation(
			ID, NAME, VERSION, DESCRIPTION, VENDOR, VENDOR_URL, PATH_TO_UI);

}
