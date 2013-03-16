package com.puresol.coding.client.common.analysis;

import org.osgi.framework.BundleActivator;

import com.puresol.coding.analysis.impl.Activator;
import com.puresol.coding.client.common.osgi.AbstractStartup;

public class CommonAnalysisImplStartup extends AbstractStartup {

	protected CommonAnalysisImplStartup(
			Class<? extends BundleActivator> activatorClass) {
		super(Activator.class);
	}

}
