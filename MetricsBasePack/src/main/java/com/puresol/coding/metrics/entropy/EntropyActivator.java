package com.puresol.coding.metrics.entropy;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.config.APIInformation;

public class EntropyActivator implements BundleActivator {

    private static final Logger logger = Logger
	    .getLogger(EntropyActivator.class);

    private final List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<ServiceRegistration<?>>();

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting Entropy...");

	registerFactory(context);

	logger.info("Started.");
    }

    private void registerFactory(BundleContext context) {
	EntropyMetricServiceFactory entropyMetricFactory = new EntropyMetricServiceFactory();

	String interfaces[] = new String[] {
		ProjectEvaluatorFactory.class.getName(),
		CodeRangeEvaluatorFactory.class.getName() };

	Dictionary<String, Object> properties = new Hashtable<String, Object>();
	properties.put("service.name", entropyMetricFactory.getName());
	properties.put("service.description",
		entropyMetricFactory.getDescription());
	properties.put("service.vendor", APIInformation.getPackageOwner());

	ServiceRegistration<?> registration = context.registerService(
		interfaces, entropyMetricFactory, properties);
	serviceRegistrations.add(registration);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping Entropy...");

	for (ServiceRegistration<?> registration : serviceRegistrations) {
	    registration.unregister();
	}
	serviceRegistrations.clear();

	logger.info("Stopped.");
    }

}
