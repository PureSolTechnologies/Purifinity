package com.puresol.coding.metrics;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.puresol.coding.evaluator.CodeRangeEvaluatorManager;
import com.puresol.coding.evaluator.ProjectEvaluatorManager;
import com.puresol.coding.metrics.cocomo.CoCoMoFactory;
import com.puresol.coding.metrics.codedepth.CodeDepthMetricFactory;
import com.puresol.coding.metrics.entropy.EntropyMetricFactory;
import com.puresol.coding.metrics.halstead.HalsteadMetricFactory;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexFactory;
import com.puresol.coding.metrics.mccabe.McCabeMetricFactory;
import com.puresol.coding.metrics.sloc.SLOCMetricFactory;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator implements BundleActivator {

	private static final Logger logger = Logger.getLogger(Activator.class);

	private CoCoMoFactory cocomoFactory = null;
	private CodeDepthMetricFactory codeDepthMetricFactory = null;
	private EntropyMetricFactory entropyMetricFactory = null;
	private HalsteadMetricFactory halsteadMetricFactory = null;
	private MaintainabilityIndexFactory maintainabilityIndexFactory = null;
	private McCabeMetricFactory mcCabeMetricFactory = null;
	private SLOCMetricFactory slocMetricFactory = null;

	@Override
	public void start(BundleContext context) throws Exception {
		logger.info("Starting Metrics Base Package...");

		ProjectEvaluatorManager projectEvaluatorManager = ProjectEvaluatorManager
				.getInstance();
		CodeRangeEvaluatorManager codeRangeEvaluatorManager = CodeRangeEvaluatorManager
				.getInstance();

		cocomoFactory = new CoCoMoFactory();
		projectEvaluatorManager.register(cocomoFactory);

		codeDepthMetricFactory = new CodeDepthMetricFactory();
		projectEvaluatorManager.register(codeDepthMetricFactory);
		codeRangeEvaluatorManager.register(codeDepthMetricFactory);

		entropyMetricFactory = new EntropyMetricFactory();
		projectEvaluatorManager.register(entropyMetricFactory);
		codeRangeEvaluatorManager.register(entropyMetricFactory);

		halsteadMetricFactory = new HalsteadMetricFactory();
		projectEvaluatorManager.register(halsteadMetricFactory);
		codeRangeEvaluatorManager.register(halsteadMetricFactory);

		maintainabilityIndexFactory = new MaintainabilityIndexFactory();
		projectEvaluatorManager.register(maintainabilityIndexFactory);
		codeRangeEvaluatorManager.register(maintainabilityIndexFactory);

		mcCabeMetricFactory = new McCabeMetricFactory();
		projectEvaluatorManager.register(mcCabeMetricFactory);
		codeRangeEvaluatorManager.register(mcCabeMetricFactory);

		slocMetricFactory = new SLOCMetricFactory();
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

		projectEvaluatorManager.unregister(cocomoFactory);
		cocomoFactory = null;

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

		projectEvaluatorManager.unregister(mcCabeMetricFactory);
		codeRangeEvaluatorManager.unregister(mcCabeMetricFactory);
		mcCabeMetricFactory = null;

		projectEvaluatorManager.unregister(slocMetricFactory);
		codeRangeEvaluatorManager.unregister(slocMetricFactory);
		slocMetricFactory = null;

		logger.info("Stopped.");
	}

}
