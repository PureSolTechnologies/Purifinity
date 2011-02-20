package com.puresol.coding.metrics.halstead;

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

public class HalsteadActivator implements BundleActivator {

	private static final Logger logger = Logger
			.getLogger(HalsteadActivator.class);

	private final List<ServiceRegistration> serviceRegistrations = new ArrayList<ServiceRegistration>();

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info("Starting Halstead...");

		HalsteadMetricServiceFactory halsteadMetricFactory = new HalsteadMetricServiceFactory();

		String interfaces[] = new String[] {
				ProjectEvaluatorFactory.class.getName(),
				CodeRangeEvaluatorFactory.class.getName() };

		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", halsteadMetricFactory.getName());
		properties.put("service.description",
				halsteadMetricFactory.getDescription());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				halsteadMetricFactory, properties);
		serviceRegistrations.add(registration);

		logger.info("Started.");
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
