package com.puresol.coding.metrics.halstead;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.evaluation.api.EvaluatorFactory;

public class HalsteadActivator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(HalsteadActivator.class);

    private final List<ServiceRegistration> serviceRegistrations = new ArrayList<ServiceRegistration>();

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting Halstead...");

	registerFactory(context);

	logger.info("Started.");
    }

    private void registerFactory(BundleContext context) {
	HalsteadMetricServiceFactory halsteadMetricFactory = new HalsteadMetricServiceFactory();
	Dictionary<?, ?> headers = context.getBundle().getHeaders();

	ServiceRegistration registration = context.registerService(
		EvaluatorFactory.class.getName(), halsteadMetricFactory,
		headers);
	serviceRegistrations.add(registration);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping Halstead...");

	for (ServiceRegistration registration : serviceRegistrations) {
	    registration.unregister();
	}
	serviceRegistrations.clear();

	logger.info("Stopped.");
    }

}
