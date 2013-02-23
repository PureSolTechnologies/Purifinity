package com.puresol.coding.metrics.normmaint;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.evaluation.api.EvaluatorFactory;

public class NormalizedMaintainabilityActivator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(NormalizedMaintainabilityActivator.class);

    private final List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<ServiceRegistration<?>>();

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting Normalized Maintainability Index...");

	registerFactory(context);

	logger.info("Started.");
    }

    private void registerFactory(BundleContext context) {
	NormalizedMaintainabilityIndexEvaluatorFactory normalizedMaintainabilityIndexFactory = new NormalizedMaintainabilityIndexEvaluatorFactory();
	Dictionary<String, ?> headers = context.getBundle().getHeaders();

	ServiceRegistration<?> registration = context.registerService(
		EvaluatorFactory.class, normalizedMaintainabilityIndexFactory,
		headers);
	serviceRegistrations.add(registration);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping Normalized Maintainability Index...");

	for (ServiceRegistration<?> registration : serviceRegistrations) {
	    registration.unregister();
	}
	serviceRegistrations.clear();

	logger.info("Stopped.");
    }

}
