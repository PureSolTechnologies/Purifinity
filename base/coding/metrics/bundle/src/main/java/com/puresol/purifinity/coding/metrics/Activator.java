package com.puresol.purifinity.coding.metrics;

import java.util.Hashtable;

import org.osgi.framework.BundleContext;

import com.puresol.commons.osgi.AbstractActivator;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoServiceFactory;
import com.puresol.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoServiceFactory;
import com.puresol.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorFactory;
import com.puresol.purifinity.coding.metrics.entropy.EntropyMetricServiceFactory;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorFactory;
import com.puresol.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorFactory;
import com.puresol.purifinity.coding.metrics.mccabe.McCabeMetricServiceFactory;
import com.puresol.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorFactory;
import com.puresol.purifinity.coding.metrics.sloc.SLOCEvaluatorFactory;

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

		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("metric", "true");

		registerService(EvaluatorFactory.class, new SLOCEvaluatorFactory(),
				properties);
		registerService(EvaluatorFactory.class,
				new BasicCoCoMoServiceFactory(), properties);
		registerService(EvaluatorFactory.class,
				new IntermediateCoCoMoServiceFactory(), properties);
		registerService(EvaluatorFactory.class,
				new HalsteadMetricEvaluatorFactory(), properties);
		registerService(EvaluatorFactory.class,
				new EntropyMetricServiceFactory(), properties);
		registerService(EvaluatorFactory.class,
				new CodeDepthMetricEvaluatorFactory(), properties);
		registerService(EvaluatorFactory.class,
				new McCabeMetricServiceFactory(), properties);
		registerService(EvaluatorFactory.class,
				new MaintainabilityIndexEvaluatorFactory(), properties);
		registerService(EvaluatorFactory.class,
				new NormalizedMaintainabilityIndexEvaluatorFactory(),
				properties);
	}
}
