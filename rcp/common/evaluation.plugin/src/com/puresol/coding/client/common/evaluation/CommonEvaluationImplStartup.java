package com.puresol.coding.client.common.evaluation;

import org.osgi.framework.BundleActivator;

import com.puresol.coding.client.common.osgi.AbstractStartup;
import com.puresol.coding.evaluation.impl.Activator;

public class CommonEvaluationImplStartup extends AbstractStartup {

	protected CommonEvaluationImplStartup(
			Class<? extends BundleActivator> activatorClass) {
		super(Activator.class);
	}

}
