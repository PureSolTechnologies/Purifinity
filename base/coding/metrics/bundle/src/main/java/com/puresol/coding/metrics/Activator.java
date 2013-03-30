package com.puresol.coding.metrics;

import java.util.Hashtable;

import org.osgi.framework.BundleContext;

import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.metrics.cocomo.CoCoMoServiceFactory;
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

	Hashtable<String, String> properties = new Hashtable<String, String>();
	properties.put("metric", "true");

	registerService(EvaluatorFactory.class, new SLOCEvaluatorFactory(),
		properties);
	registerService(EvaluatorFactory.class, new CoCoMoServiceFactory(),
		properties);
	// registerService(EvaluatorFactory.class,
	// new EntropyMetricServiceFactory(), properties);
	// registerService(EvaluatorFactory.class,
	// new CodeDepthMetricEvaluatorFactory(), properties);
	// registerService(EvaluatorFactory.class,
	// new HalsteadMetricEvaluatorFactory(), properties);
	// registerService(EvaluatorFactory.class,
	// new McCabeMetricServiceFactory(), properties);
	// registerService(EvaluatorFactory.class,
	// new MaintainabilityIndexEvaluatorFactory(), properties);
	// registerService(EvaluatorFactory.class,
	// new NormalizedMaintainabilityIndexEvaluatorFactory(),
	// properties);
    }
}
