package com.puresol.coding.metrics;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.puresol.coding.evaluator.CodeRangeEvaluatorManager;
import com.puresol.coding.evaluator.ProjectEvaluatorManager;
import com.puresol.coding.metrics.cocomo.CoCoMoConfigurator;
import com.puresol.coding.metrics.cocomo.CoCoMoServiceFactory;
import com.puresol.coding.metrics.codedepth.CodeDepthMetricServiceFactory;
import com.puresol.coding.metrics.entropy.EntropyMetricServiceFactory;
import com.puresol.coding.metrics.halstead.HalsteadMetricServiceFactory;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexServiceFactory;
import com.puresol.coding.metrics.mccabe.McCabeMetricServiceFactory;
import com.puresol.coding.metrics.normmaint.NormalizedMaintainabilityIndexServiceFactory;
import com.puresol.coding.metrics.sloc.SLOCMetricServiceFactory;
import com.puresol.gui.osgi.BundleConfiguratorManager;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator implements BundleActivator {

	private static final Logger logger = Logger.getLogger(Activator.class);

	private CoCoMoServiceFactory cocomoFactory = null;
	private CodeDepthMetricServiceFactory codeDepthMetricFactory = null;
	private EntropyMetricServiceFactory entropyMetricFactory = null;
	private HalsteadMetricServiceFactory halsteadMetricFactory = null;
	private MaintainabilityIndexServiceFactory maintainabilityIndexFactory = null;
	private NormalizedMaintainabilityIndexServiceFactory normalizedMaintainabilityIndexFactory = null;
	private McCabeMetricServiceFactory mcCabeMetricFactory = null;
	private SLOCMetricServiceFactory slocMetricFactory = null;
	private CoCoMoConfigurator cocomoConfigurator = null;

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info("Starting Metrics Base Package...");

		ProjectEvaluatorManager projectEvaluatorManager = ProjectEvaluatorManager
				.getInstance();
		CodeRangeEvaluatorManager codeRangeEvaluatorManager = CodeRangeEvaluatorManager
				.getInstance();
		BundleConfiguratorManager bundleConfiguratorManager = BundleConfiguratorManager
				.getInstance();

		cocomoFactory = new CoCoMoServiceFactory();
		projectEvaluatorManager.register(cocomoFactory);
		cocomoConfigurator = new CoCoMoConfigurator();
		bundleConfiguratorManager.register(cocomoConfigurator);

		codeDepthMetricFactory = new CodeDepthMetricServiceFactory();
		projectEvaluatorManager.register(codeDepthMetricFactory);
		codeRangeEvaluatorManager.register(codeDepthMetricFactory);

		entropyMetricFactory = new EntropyMetricServiceFactory();
		projectEvaluatorManager.register(entropyMetricFactory);
		codeRangeEvaluatorManager.register(entropyMetricFactory);

		halsteadMetricFactory = new HalsteadMetricServiceFactory();
		projectEvaluatorManager.register(halsteadMetricFactory);
		codeRangeEvaluatorManager.register(halsteadMetricFactory);

		maintainabilityIndexFactory = new MaintainabilityIndexServiceFactory();
		projectEvaluatorManager.register(maintainabilityIndexFactory);
		codeRangeEvaluatorManager.register(maintainabilityIndexFactory);

		normalizedMaintainabilityIndexFactory = new NormalizedMaintainabilityIndexServiceFactory();
		projectEvaluatorManager.register(normalizedMaintainabilityIndexFactory);
		codeRangeEvaluatorManager
				.register(normalizedMaintainabilityIndexFactory);

		mcCabeMetricFactory = new McCabeMetricServiceFactory();
		projectEvaluatorManager.register(mcCabeMetricFactory);
		codeRangeEvaluatorManager.register(mcCabeMetricFactory);

		slocMetricFactory = new SLOCMetricServiceFactory();
		projectEvaluatorManager.register(slocMetricFactory);
		codeRangeEvaluatorManager.register(slocMetricFactory);

		logger.info("Started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping Metrics Base Package...");

		ProjectEvaluatorManager projectEvaluatorManager = ProjectEvaluatorManager
				.getInstance();
		CodeRangeEvaluatorManager codeRangeEvaluatorManager = CodeRangeEvaluatorManager
				.getInstance();
		BundleConfiguratorManager bundleConfiguratorManager = BundleConfiguratorManager
				.getInstance();

		projectEvaluatorManager.unregister(cocomoFactory);
		cocomoFactory = null;
		bundleConfiguratorManager.unregister(cocomoConfigurator);
		cocomoConfigurator = null;

		projectEvaluatorManager.unregister(codeDepthMetricFactory);
		codeRangeEvaluatorManager.unregister(codeDepthMetricFactory);
		codeDepthMetricFactory = null;

		projectEvaluatorManager.unregister(entropyMetricFactory);
		codeRangeEvaluatorManager.unregister(entropyMetricFactory);
		entropyMetricFactory = null;

		projectEvaluatorManager.unregister(halsteadMetricFactory);
		codeRangeEvaluatorManager.unregister(halsteadMetricFactory);
		halsteadMetricFactory = null;

		projectEvaluatorManager.unregister(maintainabilityIndexFactory);
		codeRangeEvaluatorManager.unregister(maintainabilityIndexFactory);
		maintainabilityIndexFactory = null;

		projectEvaluatorManager
				.unregister(normalizedMaintainabilityIndexFactory);
		codeRangeEvaluatorManager
				.unregister(normalizedMaintainabilityIndexFactory);
		normalizedMaintainabilityIndexFactory = null;

		projectEvaluatorManager.unregister(mcCabeMetricFactory);
		codeRangeEvaluatorManager.unregister(mcCabeMetricFactory);
		mcCabeMetricFactory = null;

		projectEvaluatorManager.unregister(slocMetricFactory);
		codeRangeEvaluatorManager.unregister(slocMetricFactory);
		slocMetricFactory = null;

		logger.info("Stopped.");
	}

}
