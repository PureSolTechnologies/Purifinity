package com.puresoltechnologies.purifinity.coding.metrics;

import java.util.Hashtable;

import org.osgi.framework.BundleContext;

import com.puresoltechnologies.commons.osgi.AbstractActivator;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoServiceFactory;
import com.puresoltechnologies.purifinity.coding.metrics.cocomo.intermediate.IntermediateCoCoMoServiceFactory;
import com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthMetricEvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricServiceFactory;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexEvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.metrics.mccabe.McCabeMetricServiceFactory;
import com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCEvaluatorFactory;

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
