package com.puresol.coding.metrics;

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
import com.puresol.coding.metrics.cocomo.CoCoMoConfigurator;
import com.puresol.coding.metrics.cocomo.CoCoMoServiceFactory;
import com.puresol.coding.metrics.codedepth.CodeDepthMetricServiceFactory;
import com.puresol.coding.metrics.entropy.EntropyMetricServiceFactory;
import com.puresol.coding.metrics.halstead.HalsteadMetricServiceFactory;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexServiceFactory;
import com.puresol.coding.metrics.mccabe.McCabeMetricServiceFactory;
import com.puresol.coding.metrics.normmaint.NormalizedMaintainabilityIndexServiceFactory;
import com.puresol.coding.metrics.sloc.SLOCMetricServiceFactory;
import com.puresol.config.APIInformation;
import com.puresol.gui.osgi.BundleConfigurator;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator implements BundleActivator {

	private static final Logger logger = Logger.getLogger(Activator.class);

	private final List<ServiceRegistration> serviceRegistrations = new ArrayList<ServiceRegistration>();

	private void registerCoCoMo(BundleContext context) {
		CoCoMoServiceFactory cocomoFactory = new CoCoMoServiceFactory();

		String interfaces[] = new String[] { ProjectEvaluatorFactory.class
				.getName() };
		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", cocomoFactory.getName());
		properties.put("service.description", cocomoFactory.getDescription());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				cocomoFactory, properties);
		serviceRegistrations.add(registration);

		interfaces = new String[] { BundleConfigurator.class.getName() };
		CoCoMoConfigurator cocomoConfigurator = new CoCoMoConfigurator();
		properties = new Hashtable<Object, Object>();
		properties.put("service.name", cocomoConfigurator.getName());
		properties.put("service.description", cocomoConfigurator.getPathName());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		registration = context.registerService(interfaces, cocomoConfigurator,
				properties);
		serviceRegistrations.add(registration);

	}

	private void registerCodeDepth(BundleContext context) {
		CodeDepthMetricServiceFactory codeDepthMetricFactory = new CodeDepthMetricServiceFactory();

		String interfaces[] = new String[] {
				ProjectEvaluatorFactory.class.getName(),
				CodeRangeEvaluatorFactory.class.getName() };

		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", codeDepthMetricFactory.getName());
		properties.put("service.description", codeDepthMetricFactory
				.getDescription());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				codeDepthMetricFactory, properties);
		serviceRegistrations.add(registration);
	}

	private void registerEntropy(BundleContext context) {
		EntropyMetricServiceFactory entropyMetricFactory = new EntropyMetricServiceFactory();

		String interfaces[] = new String[] {
				ProjectEvaluatorFactory.class.getName(),
				CodeRangeEvaluatorFactory.class.getName() };

		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", entropyMetricFactory.getName());
		properties.put("service.description", entropyMetricFactory
				.getDescription());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				entropyMetricFactory, properties);
		serviceRegistrations.add(registration);
	}

	private void registerHalstead(BundleContext context) {
		HalsteadMetricServiceFactory halsteadMetricFactory = new HalsteadMetricServiceFactory();

		String interfaces[] = new String[] {
				ProjectEvaluatorFactory.class.getName(),
				CodeRangeEvaluatorFactory.class.getName() };

		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", halsteadMetricFactory.getName());
		properties.put("service.description", halsteadMetricFactory
				.getDescription());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				halsteadMetricFactory, properties);
		serviceRegistrations.add(registration);
	}

	private void registerMaintainability(BundleContext context) {
		MaintainabilityIndexServiceFactory maintainabilityIndexFactory = new MaintainabilityIndexServiceFactory();

		String interfaces[] = new String[] {
				ProjectEvaluatorFactory.class.getName(),
				CodeRangeEvaluatorFactory.class.getName() };

		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", maintainabilityIndexFactory.getName());
		properties.put("service.description", maintainabilityIndexFactory
				.getDescription());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				maintainabilityIndexFactory, properties);
		serviceRegistrations.add(registration);
	}

	private void registerNormalizedMaintainability(BundleContext context) {
		NormalizedMaintainabilityIndexServiceFactory normalizedMaintainabilityIndexFactory = new NormalizedMaintainabilityIndexServiceFactory();

		String interfaces[] = new String[] {
				ProjectEvaluatorFactory.class.getName(),
				CodeRangeEvaluatorFactory.class.getName() };

		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", normalizedMaintainabilityIndexFactory
				.getName());
		properties.put("service.description",
				normalizedMaintainabilityIndexFactory.getDescription());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				normalizedMaintainabilityIndexFactory, properties);
		serviceRegistrations.add(registration);
	}

	private void registerMcCabe(BundleContext context) {
		McCabeMetricServiceFactory mcCabeMetricFactory = new McCabeMetricServiceFactory();

		String interfaces[] = new String[] {
				ProjectEvaluatorFactory.class.getName(),
				CodeRangeEvaluatorFactory.class.getName() };

		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", mcCabeMetricFactory.getName());
		properties.put("service.description", mcCabeMetricFactory
				.getDescription());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				mcCabeMetricFactory, properties);
		serviceRegistrations.add(registration);
	}

	private void registerSLOC(BundleContext context) {
		SLOCMetricServiceFactory slocMetricFactory = new SLOCMetricServiceFactory();

		String interfaces[] = new String[] {
				ProjectEvaluatorFactory.class.getName(),
				CodeRangeEvaluatorFactory.class.getName() };

		Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put("service.name", slocMetricFactory.getName());
		properties.put("service.description", slocMetricFactory
				.getDescription());
		properties.put("service.vendor", APIInformation.getPackageOwner());

		ServiceRegistration registration = context.registerService(interfaces,
				slocMetricFactory, properties);
		serviceRegistrations.add(registration);
	}

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info("Starting Metrics Base Package...");

		registerCoCoMo(context);
		registerCodeDepth(context);
		registerEntropy(context);
		registerHalstead(context);
		registerMaintainability(context);
		registerNormalizedMaintainability(context);
		registerMcCabe(context);
		registerSLOC(context);

		logger.info("Started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping Metrics Base Package...");

		for (ServiceRegistration registration : serviceRegistrations) {
			registration.unregister();
		}
		serviceRegistrations.clear();

		logger.info("Stopped.");
	}

}
