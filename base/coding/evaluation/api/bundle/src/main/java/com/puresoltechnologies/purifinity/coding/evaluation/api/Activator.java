package com.puresoltechnologies.purifinity.coding.evaluation.api;

import org.osgi.framework.BundleContext;

import com.puresoltechnologies.commons.osgi.AbstractActivator;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.api.Evaluators;

public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		registerService(Evaluators.class, new EvaluatorsOSGi());
		registerService(EvaluatorStoreFactory.class,
				new EvaluatorStoreFactoryOSGi());
	}
}
