package com.puresol.purifinity.coding.evaluation.api;

import org.osgi.framework.BundleContext;

import com.puresol.commons.osgi.AbstractActivator;

public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		registerService(Evaluators.class, new EvaluatorsOSGi());
		registerService(EvaluatorStoreFactory.class,
				new EvaluatorStoreFactoryOSGi());
	}
}
