package com.puresol.coding.client.common.analysis;

import org.osgi.framework.BundleActivator;

import com.puresol.coding.analysis.api.Activator;
import com.puresol.coding.client.common.osgi.AbstractStartup;

public class CommonAnalysisAPIStartup extends AbstractStartup {

	protected CommonAnalysisAPIStartup(
			Class<? extends BundleActivator> activatorClass) {
		super(Activator.class);
	}

}
