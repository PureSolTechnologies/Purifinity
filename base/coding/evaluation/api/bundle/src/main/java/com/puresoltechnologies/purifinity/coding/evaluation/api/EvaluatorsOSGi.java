package com.puresoltechnologies.purifinity.coding.evaluation.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.api.Evaluators;

/**
 * This is a utilities class which provides functionality to access the metrics
 * services more easily.
 * 
 * @author Rick-Rainer Ludwig
 */
public class EvaluatorsOSGi extends Evaluators {

    private static final BundleContext bundleContext = Activator
	    .getBundleContext();

    private final Collection<ServiceReference<EvaluatorFactory>> serviceReferences;
    private final Collection<ServiceReference<EvaluatorFactory>> metricServiceReferences;
    private final Collection<ServiceReference<EvaluatorFactory>> nonMetricServiceReferences;

    public EvaluatorsOSGi() {
	super();
	try {
	    serviceReferences = bundleContext.getServiceReferences(
		    EvaluatorFactory.class, null);
	    metricServiceReferences = bundleContext.getServiceReferences(
		    EvaluatorFactory.class, "(metric=true)");
	    nonMetricServiceReferences = bundleContext.getServiceReferences(
		    EvaluatorFactory.class, "(metric=false)");
	} catch (InvalidSyntaxException e) {
	    throw new RuntimeException(
		    "Could not retrieve the project evaluators!", e);
	}
    }

    /**
     * This method returns a list of all project evaluators available.
     * 
     * @return Returns a list of project evaluator factories.
     */
    @Override
    public List<EvaluatorFactory> getAll() {
	List<EvaluatorFactory> evaluatorFactories = new ArrayList<EvaluatorFactory>();
	for (ServiceReference<EvaluatorFactory> serviceReference : serviceReferences) {
	    evaluatorFactories.add(bundleContext.getService(serviceReference));
	}
	return evaluatorFactories;
    }

    @Override
    public List<EvaluatorFactory> getAllMetrics() {
	List<EvaluatorFactory> metricFactories = new ArrayList<EvaluatorFactory>();
	for (ServiceReference<EvaluatorFactory> metricServiceReference : metricServiceReferences) {
	    metricFactories.add(bundleContext
		    .getService(metricServiceReference));
	}
	return metricFactories;
    }

    @Override
    public List<EvaluatorFactory> getAllNonMetrics() {
	List<EvaluatorFactory> metricFactories = new ArrayList<EvaluatorFactory>();
	for (ServiceReference<EvaluatorFactory> nonMetricServiceReference : nonMetricServiceReferences) {
	    metricFactories.add(bundleContext
		    .getService(nonMetricServiceReference));
	}
	return metricFactories;
    }

    @Override
    public void close() {
	for (ServiceReference<EvaluatorFactory> serviceReference : serviceReferences) {
	    bundleContext.ungetService(serviceReference);
	}
	serviceReferences.clear();
	for (ServiceReference<EvaluatorFactory> metricServiceReference : metricServiceReferences) {
	    bundleContext.ungetService(metricServiceReference);
	}
	metricServiceReferences.clear();
	for (ServiceReference<EvaluatorFactory> nonMetricServiceReference : nonMetricServiceReferences) {
	    bundleContext.ungetService(nonMetricServiceReference);
	}
	nonMetricServiceReferences.clear();
    }
}
