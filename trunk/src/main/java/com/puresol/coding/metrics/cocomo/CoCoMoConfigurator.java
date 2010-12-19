package com.puresol.coding.metrics.cocomo;

import com.puresol.gui.osgi.BundleConfigurator;
import com.puresol.gui.osgi.BundleConfiguratorPanel;

public class CoCoMoConfigurator implements BundleConfigurator {

	@Override
	public String getName() {
		return "Cost Construction Model";
	}

	@Override
	public String getPathName() {
		return "Evaluators/CoCoMo";
	}

	@Override
	public BundleConfiguratorPanel createPanel() {
		return new CoCoMoConfiguratorPanel();
	}

}
