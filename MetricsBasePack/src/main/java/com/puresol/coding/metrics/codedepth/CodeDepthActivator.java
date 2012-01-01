package com.puresol.coding.metrics.codedepth;

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
import com.puresol.osgi.BundleConfigurator;

public class CodeDepthActivator implements BundleActivator {

    private static final Logger logger = Logger
	    .getLogger(CodeDepthActivator.class);

    private final List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<ServiceRegistration<?>>();

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting CodeDepth...");

	registerFactory(context);
	registerConfigurator(context);
	logger.info("Started.");
    }

    private void registerFactory(BundleContext context) {
	CodeDepthMetricServiceFactory codeDepthFactory = new CodeDepthMetricServiceFactory();
	String interfaces[] = new String[] {
		ProjectEvaluatorFactory.class.getName(),
		CodeRangeEvaluatorFactory.class.getName() };

	Dictionary<String, Object> properties = new Hashtable<String, Object>();
	properties.put("service.name", codeDepthFactory.getName());
	properties
		.put("service.description", codeDepthFactory.getDescription());
	properties.put("service.vendor", APIInformation.getPackageOwner());

	ServiceRegistration<?> registration = context.registerService(
		interfaces, codeDepthFactory, properties);
	serviceRegistrations.add(registration);
    }

    private void registerConfigurator(BundleContext context) {
	String interfaces[] = new String[] { BundleConfigurator.class.getName() };
	CodeDepthMetricConfigurator cocomoConfigurator = new CodeDepthMetricConfigurator();
	Dictionary<String, Object> properties = new Hashtable<String, Object>();
	properties.put("service.name", cocomoConfigurator.getName());
	properties.put("service.description", cocomoConfigurator.getPathName());
	properties.put("service.vendor", APIInformation.getPackageOwner());

	ServiceRegistration<?> registration = context.registerService(
		interfaces, cocomoConfigurator, properties);
	serviceRegistrations.add(registration);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping CodeDepth...");

	for (ServiceRegistration<?> registration : serviceRegistrations) {
	    registration.unregister();
	}
	serviceRegistrations.clear();

	logger.info("Stopped.");
    }

}
