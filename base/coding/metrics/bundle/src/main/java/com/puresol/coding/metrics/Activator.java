package com.puresol.coding.metrics;

import org.osgi.framework.BundleContext;

import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.metrics.cocomo.CoCoMoServiceFactory;
import com.puresol.coding.metrics.codedepth.CodeDepthMetricEvaluatorFactory;
import com.puresol.coding.metrics.entropy.EntropyMetricServiceFactory;
import com.puresol.coding.metrics.halstead.HalsteadMetricEvaluatorFactory;
import com.puresol.coding.metrics.maintainability.MaintainabilityIndexEvaluatorFactory;
import com.puresol.coding.metrics.mccabe.McCabeMetricServiceFactory;
import com.puresol.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorFactory;
import com.puresol.coding.metrics.sloc.SLOCEvaluatorFactory;
import com.puresol.commons.osgi.AbstractActivator;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator extends AbstractActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);

		registerService(EvaluatorFactory.class, new CoCoMoServiceFactory());
		registerService(EvaluatorFactory.class,
				new CodeDepthMetricEvaluatorFactory());
		registerService(EvaluatorFactory.class,
				new EntropyMetricServiceFactory());
		registerService(EvaluatorFactory.class,
				new HalsteadMetricEvaluatorFactory());
		registerService(EvaluatorFactory.class,
				new MaintainabilityIndexEvaluatorFactory());
		registerService(EvaluatorFactory.class,
				new McCabeMetricServiceFactory());
		registerService(EvaluatorFactory.class,
				new NormalizedMaintainabilityIndexEvaluatorFactory());
		registerService(EvaluatorFactory.class, new SLOCEvaluatorFactory());
	}
}
