package com.puresoltechnologies.purifinity.server.metrics;

import java.net.MalformedURLException;
import java.net.URL;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;
import com.puresoltechnologies.versioning.Version;

public class MetricsPlugin {

	private static final String ID = MetricsPlugin.class.getName();
	private static final String NAME = "Metrics Plugin";
	private static final Version VERSION = new Version(0, 3, 0);
	private static final String DESCRIPTION = "Contains standard metrics which can be applied to different languages.";
	private static final String VENDOR = "PureSol Technologies";
	private static final URL VENDOR_URL;
	static {
		try {
			VENDOR_URL = new URL("http://puresol-technologies.com");
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not create vendor URL.", e);
		}
	}
	private static final String PATH_TO_UI = "/metrics.ui";

	public static final PluginInformation INFORMATION = new PluginInformation(
			ID, NAME, VERSION, DESCRIPTION, VENDOR, VENDOR_URL, PATH_TO_UI);

}
