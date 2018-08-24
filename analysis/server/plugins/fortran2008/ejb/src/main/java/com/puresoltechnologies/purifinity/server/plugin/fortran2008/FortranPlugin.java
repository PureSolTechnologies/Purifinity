package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import java.net.MalformedURLException;
import java.net.URL;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.versioning.Version;

public class FortranPlugin {

	private static final String ID = FortranPlugin.class.getName();
	private static final String NAME = "Fortran 2008 Plugin";
	private static final Version VERSION = BuildInformation.getVersion();
	private static final String DESCRIPTION = "Contains the analyzer for Fortran 2008.";
	private static final String VENDOR = "PureSol Technologies";
	private static final URL VENDOR_URL;
	static {
		try {
			VENDOR_URL = new URL("http://puresol-technologies.com");
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not create vendor URL.", e);
		}
	}
	private static final String PATH_TO_UI = "/fortran2008.ui";

	public static final PluginInformation INFORMATION = new PluginInformation(
			ID, NAME, VERSION, DESCRIPTION, VENDOR, VENDOR_URL, PATH_TO_UI);

}
