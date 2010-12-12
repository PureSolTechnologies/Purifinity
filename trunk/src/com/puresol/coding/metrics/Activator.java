package com.puresol.coding.metrics;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.puresol.coding.evaluator.Evaluators;
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

		Evaluators evaluators = Evaluators.getInstance();

		cocomoFactory = new CoCoMoFactory();
		evaluators.registerProjectEvaluator(cocomoFactory);

		codeDepthMetricFactory = new CodeDepthMetricFactory();
		evaluators.registerCodeRangeEvaluator(codeDepthMetricFactory);

		entropyMetricFactory = new EntropyMetricFactory();
		evaluators.registerCodeRangeEvaluator(entropyMetricFactory);

		halsteadMetricFactory = new HalsteadMetricFactory();
		evaluators.registerCodeRangeEvaluator(halsteadMetricFactory);

		maintainabilityIndexFactory = new MaintainabilityIndexFactory();
		evaluators.registerCodeRangeEvaluator(maintainabilityIndexFactory);

		mcCabeMetricFactory = new McCabeMetricFactory();
		evaluators.registerCodeRangeEvaluator(mcCabeMetricFactory);

		slocMetricFactory = new SLOCMetricFactory();
		evaluators.registerProjectEvaluator(slocMetricFactory);
		evaluators.registerCodeRangeEvaluator(slocMetricFactory);

		logger.info("Started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		logger.info("Stopping Metrics Base Package...");

		Evaluators evaluators = Evaluators.getInstance();

		evaluators.unregisterProjectEvaluator(cocomoFactory);
		cocomoFactory = null;

		evaluators.unregisterCodeRangeEvaluator(codeDepthMetricFactory);
		codeDepthMetricFactory = null;

		evaluators.unregisterCodeRangeEvaluator(entropyMetricFactory);
		entropyMetricFactory = null;

		evaluators.unregisterCodeRangeEvaluator(halsteadMetricFactory);
		halsteadMetricFactory = null;

		evaluators.unregisterCodeRangeEvaluator(maintainabilityIndexFactory);
		maintainabilityIndexFactory = null;

		evaluators.unregisterCodeRangeEvaluator(mcCabeMetricFactory);
		mcCabeMetricFactory = null;

		evaluators.unregisterProjectEvaluator(slocMetricFactory);
		evaluators.unregisterCodeRangeEvaluator(slocMetricFactory);
		slocMetricFactory = null;

		logger.info("Stopped.");
	}

}
