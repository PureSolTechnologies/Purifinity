package com.puresol.coding.metrics.entropy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;

public class EntropyActivator implements BundleActivator {

	private static final Logger logger = LoggerFactory
			.getLogger(EntropyActivator.class);

	private final List<ServiceRegistration> serviceRegistrations = new ArrayList<ServiceRegistration>();

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info("Starting Entropy...");

		registerFactory(context);

		logger.info("Started.");
	}

	private void registerFactory(BundleContext context) {
		EntropyMetricServiceFactory entropyMetricFactory = new EntropyMetricServiceFactory();

		ServiceRegistration registration = context.registerService(
				EvaluatorFactory.class.getName(), entropyMetricFactory,
				new Hashtable<String, String>());
		serviceRegistrations.add(registration);

		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("evaluator", EntropyEvaluator.class.getName());
		registration = context.registerService(EvaluatorStore.class.getName(),
				new EntropyEvaluatorStore(), properties);
		serviceRegistrations.add(registration);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping Entropy...");

		for (ServiceRegistration registration : serviceRegistrations) {
			registration.unregister();
		}
		serviceRegistrations.clear();

		logger.info("Stopped.");
	}

}
