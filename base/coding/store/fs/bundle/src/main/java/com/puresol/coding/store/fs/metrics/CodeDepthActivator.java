package com.puresol.coding.store.fs.metrics;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.metrics.codedepth.CodeDepthEvaluator;

public class CodeDepthActivator implements BundleActivator {

	private static final Logger logger = LoggerFactory
			.getLogger(CodeDepthActivator.class);

	private final List<ServiceRegistration> serviceRegistrations = new ArrayList<ServiceRegistration>();

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info("Starting CodeDepth...");

		registerFactory(context);
		logger.info("Started.");
	}

	private void registerFactory(BundleContext context) {
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("evaluator", CodeDepthEvaluator.class.getName());
		ServiceRegistration registration = context.registerService(
				EvaluatorStore.class.getName(), new CodeDepthEvaluatorStore(),
				properties);
		serviceRegistrations.add(registration);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping CodeDepth...");

		for (ServiceRegistration registration : serviceRegistrations) {
			registration.unregister();
		}
		serviceRegistrations.clear();

		logger.info("Stopped.");
	}

}
