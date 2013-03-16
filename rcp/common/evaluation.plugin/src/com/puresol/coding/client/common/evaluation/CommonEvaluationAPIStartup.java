package com.puresol.coding.client.common.evaluation;

import org.osgi.framework.BundleActivator;

import com.puresol.coding.client.common.osgi.AbstractStartup;
import com.puresol.coding.evaluation.api.Activator;

public class CommonEvaluationAPIStartup extends AbstractStartup {

	protected CommonEvaluationAPIStartup(
			Class<? extends BundleActivator> activatorClass) {
		super(Activator.class);
	}

}
