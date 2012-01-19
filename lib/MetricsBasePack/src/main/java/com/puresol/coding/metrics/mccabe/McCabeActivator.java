package com.puresol.coding.metrics.mccabe;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.config.APIInformation;

public class McCabeActivator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(McCabeActivator.class);

    private final List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<ServiceRegistration<?>>();

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting McCabe...");

	registerFactory(context);

	logger.info("Started.");
    }

    private void registerFactory(BundleContext context) {
	McCabeMetricServiceFactory mcCabeMetricFactory = new McCabeMetricServiceFactory();

	String interfaces[] = new String[] {
		ProjectEvaluatorFactory.class.getName(),
		CodeRangeEvaluatorFactory.class.getName() };

	Dictionary<String, Object> properties = new Hashtable<String, Object>();
	properties.put("service.name", mcCabeMetricFactory.getName());
	properties.put("service.description",
		mcCabeMetricFactory.getDescription());
	properties.put("service.vendor", APIInformation.getPackageOwner());

	ServiceRegistration<?> registration = context.registerService(
		interfaces, mcCabeMetricFactory, properties);
	serviceRegistrations.add(registration);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping McCabe...");

	for (ServiceRegistration<?> registration : serviceRegistrations) {
	    registration.unregister();
	}
	serviceRegistrations.clear();

	logger.info("Stopped.");
    }

}
