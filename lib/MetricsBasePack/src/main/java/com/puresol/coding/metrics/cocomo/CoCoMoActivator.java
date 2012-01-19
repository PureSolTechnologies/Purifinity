package com.puresol.coding.metrics.cocomo;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.config.APIInformation;
import com.puresol.config.sources.BundleConfigurators;
import com.puresol.osgi.BundleConfigurator;

public class CoCoMoActivator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(CoCoMoActivator.class);

    private final List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<ServiceRegistration<?>>();
    private CoCoMoConfigurator cocomoConfigurator = null;

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting CoCoMo...");

	registerProjectFactory(context);
	registerConfigurator(context);

	logger.info("Started.");
    }

    private void registerProjectFactory(BundleContext context) {
	CoCoMoServiceFactory cocomoFactory = new CoCoMoServiceFactory();
	String interfaces[] = new String[] { ProjectEvaluatorFactory.class
		.getName() };

	Dictionary<String, Object> properties = new Hashtable<String, Object>();
	properties.put("service.name", cocomoFactory.getName());
	properties.put("service.description", cocomoFactory.getDescription());
	properties.put("service.vendor", APIInformation.getPackageOwner());

	ServiceRegistration<?> registration = context.registerService(
		interfaces, cocomoFactory, properties);
	serviceRegistrations.add(registration);
    }

    private void registerConfigurator(BundleContext context) {
	cocomoConfigurator = new CoCoMoConfigurator();
	BundleConfigurators.getInstance().addSource(
		cocomoConfigurator.getSource());

	String interfaces[] = new String[] { BundleConfigurator.class.getName() };
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
	logger.info("Stopping CoCoMo...");

	if (cocomoConfigurator != null) {
	    BundleConfigurators.getInstance().removeSource(
		    cocomoConfigurator.getSource());
	    cocomoConfigurator = null;
	}

	for (ServiceRegistration<?> registration : serviceRegistrations) {
	    registration.unregister();
	}
	serviceRegistrations.clear();

	logger.info("Stopped.");
    }

}
