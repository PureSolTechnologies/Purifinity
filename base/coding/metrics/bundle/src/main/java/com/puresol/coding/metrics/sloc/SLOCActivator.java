package com.puresol.coding.metrics.sloc;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.evaluation.api.EvaluatorFactory;

public class SLOCActivator implements BundleActivator {

	private static final Logger logger = LoggerFactory
			.getLogger(SLOCActivator.class);

	private final List<ServiceRegistration> serviceRegistrations = new ArrayList<ServiceRegistration>();

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info("Starting SLOC...");

		registerFactory(context);

		logger.info("Started.");
	}

	private void registerFactory(BundleContext context) {
		SLOCEvaluatorFactory slocMetricFactory = new SLOCEvaluatorFactory();

		ServiceRegistration registration = context.registerService(
				EvaluatorFactory.class.getName(), slocMetricFactory,
				new Hashtable<String, String>());
		serviceRegistrations.add(registration);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping SLOC...");

		for (ServiceRegistration registration : serviceRegistrations) {
			registration.unregister();
		}
		serviceRegistrations.clear();

		logger.info("Stopped.");
	}

}