package com.puresol.coding.metrics.cocomo;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.evaluator.ProjectEvaluatorFactory;

public class CoCoMoActivator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(CoCoMoActivator.class);

    private final List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<ServiceRegistration<?>>();

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting CoCoMo...");

	registerProjectFactory(context);

	logger.info("Started.");
    }

    private void registerProjectFactory(BundleContext context) {
	CoCoMoServiceFactory cocomoFactory = new CoCoMoServiceFactory();
	Dictionary<String, String> headers = context.getBundle().getHeaders();

	ServiceRegistration<?> registration = context.registerService(
		ProjectEvaluatorFactory.class, cocomoFactory, headers);
	serviceRegistrations.add(registration);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping CoCoMo...");

	for (ServiceRegistration<?> registration : serviceRegistrations) {
	    registration.unregister();
	}
	serviceRegistrations.clear();

	logger.info("Stopped.");
    }

}
