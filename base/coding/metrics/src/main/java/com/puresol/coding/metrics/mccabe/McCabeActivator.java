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

import com.puresol.coding.analysis.api.evaluation.EvaluatorFactory;
import com.puresol.coding.analysis.api.evaluation.EvaluatorStore;

public class McCabeActivator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(McCabeActivator.class);

    private final List<ServiceRegistration> serviceRegistrations = new ArrayList<ServiceRegistration>();

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting McCabe...");

	registerFactory(context);

	logger.info("Started.");
    }

    private void registerFactory(BundleContext context) {
	McCabeMetricServiceFactory mcCabeMetricFactory = new McCabeMetricServiceFactory();
	Dictionary<String, ?> headers = context.getBundle().getHeaders();

	ServiceRegistration registration = context.registerService(
		EvaluatorFactory.class.getName(), mcCabeMetricFactory, headers);
	serviceRegistrations.add(registration);

	Hashtable<String, String> properties = new Hashtable<String, String>();
	properties.put("evaluator", McCabeMetricEvaluator.class.getName());
	registration = context.registerService(EvaluatorStore.class.getName(),
		new McCabeMetricEvaluatorStore(), properties);
	serviceRegistrations.add(registration);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping McCabe...");

	for (ServiceRegistration registration : serviceRegistrations) {
	    registration.unregister();
	}
	serviceRegistrations.clear();

	logger.info("Stopped.");
    }

}
