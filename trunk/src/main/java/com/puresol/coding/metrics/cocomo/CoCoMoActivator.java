package com.puresol.coding.metrics.cocomo;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.config.APIInformation;
import com.puresol.gui.osgi.BundleConfigurator;

public class CoCoMoActivator implements BundleActivator {

	private static final Logger logger = Logger
			.getLogger(CoCoMoActivator.class);

	private final List<ServiceRegistration> serviceRegistrations = new ArrayList<ServiceRegistration>();

	private final CoCoMoServiceFactory cocomoFactory = new CoCoMoServiceFactory();

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info("Starting CoCoMo...");

		registerProjectFactory(context);
		registerConfigurator(context);

		logger.info("Started.");
	}

	private void registerProjectFactory(BundleContext context) {
		String interfaces[] = new String[] { ProjectEvaluatorFactory.class
				.getName() };
		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", cocomoFactory.getName());
		properties.put("service.description", cocomoFactory.getDescription());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				cocomoFactory, properties);
		serviceRegistrations.add(registration);
	}

	private void registerConfigurator(BundleContext context) {
		String interfaces[] = new String[] { BundleConfigurator.class.getName() };
		CoCoMoConfigurator cocomoConfigurator = new CoCoMoConfigurator();
		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", cocomoConfigurator.getName());
		properties.put("service.description", cocomoConfigurator.getPathName());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				cocomoConfigurator, properties);
		serviceRegistrations.add(registration);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping CoCoMo...");

		for (ServiceRegistration registration : serviceRegistrations) {
			registration.unregister();
		}
		serviceRegistrations.clear();

		logger.info("Stopped.");
	}

}
