package com.puresol.coding.metrics.codedepth;

import java.util.List;

import com.puresol.config.properties.PropertyDescription;
import com.puresol.osgi.BundleConfigurator;

public class CodeDepthMetricConfigurator implements BundleConfigurator {

	@Override
	public String getName() {
		return "Code Depth Metric";
	}

	@Override
	public String getContext() {
		return CodeDepthMetric.class.getSimpleName();
	}

	@Override
	public String getPathName() {
		return "Evaluators/Code Depth";
	}

	@Override
	public List<PropertyDescription<?>> getPropertyDescriptions() {
		return CodeDepthMetric.CONFIGURATION_PROPERTIES;
	}

}
