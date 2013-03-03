package com.puresol.coding.evaluation.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

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

    public EvaluatorsOSGi() {
	super();
	try {
	    serviceReferences = bundleContext.getServiceReferences(
		    EvaluatorFactory.class, null);
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
    public void close() {
	for (ServiceReference<EvaluatorFactory> serviceReference : serviceReferences) {
	    bundleContext.ungetService(serviceReference);
	}
    }
}
