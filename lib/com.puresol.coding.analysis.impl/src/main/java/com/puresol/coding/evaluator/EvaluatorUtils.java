package com.puresol.coding.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.Activator;

/**
 * This is a utilities class which provides functionality to access the metrics
 * services more easily.
 * 
 * @author Rick-Rainer Ludwig
 */
public class EvaluatorUtils {

    /**
     * This method returns a list of all project evaluators available.
     * 
     * @return Returns a list of project evaluator factories.
     */
    public static List<ProjectEvaluatorFactory> getProjectEvaluatorFactories() {
	try {
	    BundleContext bundleContext = Activator.getBundleContext();
	    Collection<ServiceReference<ProjectEvaluatorFactory>> serviceReferences = bundleContext
		    .getServiceReferences(ProjectEvaluatorFactory.class, null);
	    List<ProjectEvaluatorFactory> evaluatorFactories = new ArrayList<ProjectEvaluatorFactory>();
	    for (ServiceReference<ProjectEvaluatorFactory> serviceReference : serviceReferences) {
		evaluatorFactories.add(bundleContext
			.getService(serviceReference));
	    }
	    return evaluatorFactories;
	} catch (InvalidSyntaxException e) {
	    throw new RuntimeException(
		    "Could not retrieve the project evaluators!", e);
	}
    }
}
