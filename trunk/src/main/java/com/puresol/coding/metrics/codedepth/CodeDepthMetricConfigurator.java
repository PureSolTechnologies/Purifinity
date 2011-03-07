package com.puresol.coding.metrics.codedepth;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.puresol.config.ConfigurationSource;
import com.puresol.config.PropertyDescription;
import com.puresol.config.sources.HomeFile;
import com.puresol.osgi.BundleConfigurator;

public class CodeDepthMetricConfigurator implements BundleConfigurator {

	@Override
	public String getName() {
		return "Code Depth Metric";
	}

	@Override
	public String getPathName() {
		return "Evaluators/Code Depth";
	}

	@Override
	public List<PropertyDescription<?>> getPropertyDescriptions() {
		return CodeDepthMetric.CONFIGURATION_PROPERTIES;
	}

	@Override
	public ConfigurationSource getSource() {
		try {
			return new HomeFile("Code Depth Metric Plugin Configuration",
					new File(".CodeAnalysis/plugins/codedepth.properties"),
					true, false);
		} catch (IOException e) {
			return null;
		}
	}
}
