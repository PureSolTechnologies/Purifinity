package com.puresol.coding.metrics.cocomo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.puresol.config.ConfigurationSource;
import com.puresol.config.PropertyDescription;
import com.puresol.config.sources.HomeFile;
import com.puresol.osgi.BundleConfigurator;

public class CoCoMoConfigurator implements BundleConfigurator {

	private final ConfigurationSource configSource;

	public CoCoMoConfigurator() {
		super();
		ConfigurationSource source = null;
		try {
			source = new HomeFile("CoCoMo Plugin Configuration", new File(
					".CodeAnalysis/plugins/CoCoMo.properties"), true, false);
		} catch (IOException e) {
		}
		configSource = source;
	}

	@Override
	public String getName() {
		return "Cost Construction Model";
	}

	@Override
	public String getPathName() {
		return "CoCoMo";
	}

	@Override
	public List<PropertyDescription<?>> getPropertyDescriptions() {
		return CoCoMo.CONFIGURATION_PROPERTIES;
	}

	@Override
	public ConfigurationSource getSource() {
		return configSource;
	}

}
