package com.puresol.coding.metrics.sloc;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;

public class SLOCActivator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(SLOCActivator.class);

    private final List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<ServiceRegistration<?>>();

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting SLOC...");

	registerFactory(context);

	logger.info("Started.");
    }

    private void registerFactory(BundleContext context) {
	SLOCMetricServiceFactory slocMetricFactory = new SLOCMetricServiceFactory();
	Dictionary<String, String> headers = context.getBundle().getHeaders();

	ServiceRegistration<?> registration = context.registerService(
		ProjectEvaluatorFactory.class, slocMetricFactory, headers);
	serviceRegistrations.add(registration);

	registration = context.registerService(CodeRangeEvaluatorFactory.class,
		slocMetricFactory, headers);
	serviceRegistrations.add(registration);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping SLOC...");

	for (ServiceRegistration<?> registration : serviceRegistrations) {
	    registration.unregister();
	}
	serviceRegistrations.clear();

	logger.info("Stopped.");
    }

}
