package com.puresoltechnologies.purifinity.server.plugin.java7;

import java.net.MalformedURLException;
import java.net.URL;

import com.puresoltechnologies.commons.types.Version;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;

public class JavaPlugin {

	private static final String ID = JavaPlugin.class.getName();
	private static final String NAME = "Java 7 Plugin";
	private static final Version VERSION = new Version(0, 3, 0);
	private static final String DESCRIPTION = "Contains the analyzer for Java 7.";
	private static final String VENDOR = "PureSol Technologies";
	private static final URL VENDOR_URL;
	static {
		try {
			VENDOR_URL = new URL("http://puresol-technologies.com");
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not create vendor URL.", e);
		}
	}
	private static final String PATH_TO_UI = "/java7.ui";

	public static final PluginInformation INFORMATION = new PluginInformation(
			ID, NAME, VERSION, DESCRIPTION, VENDOR, VENDOR_URL, PATH_TO_UI);

}
