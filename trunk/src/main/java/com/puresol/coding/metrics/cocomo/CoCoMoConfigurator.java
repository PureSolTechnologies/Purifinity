package com.puresol.coding.metrics.cocomo;

import java.util.List;

import com.puresol.config.properties.PropertyDescription;
import com.puresol.gui.osgi.BundleConfigurator;

public class CoCoMoConfigurator implements BundleConfigurator {

	@Override
	public String getName() {
		return "Cost Construction Model";
	}

	@Override
	public String getContext() {
		return CoCoMo.class.getSimpleName();
	}

	@Override
	public String getPathName() {
		return "Evaluators/CoCoMo";
	}

	@Override
	public List<PropertyDescription<?>> getPropertyDescriptions() {
		return CoCoMo.CONFIGURATION_PROPERTIES;
	}

}
