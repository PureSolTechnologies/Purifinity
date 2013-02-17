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
public class Evaluators {

	/**
	 * This method returns a list of all project evaluators available.
	 * 
	 * @return Returns a list of project evaluator factories.
	 */
	public static List<EvaluatorFactory> getAllFactories() {
		try {
			BundleContext bundleContext = Activator.getBundleContext();
			Collection<ServiceReference<EvaluatorFactory>> serviceReferences = bundleContext
					.getServiceReferences(EvaluatorFactory.class, null);
			List<EvaluatorFactory> evaluatorFactories = new ArrayList<EvaluatorFactory>();
			if (serviceReferences != null) {
				for (ServiceReference<EvaluatorFactory> serviceReference : serviceReferences) {
					evaluatorFactories.add(bundleContext
							.getService(serviceReference));
				}
			}
			return evaluatorFactories;
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException(
					"Could not retrieve the project evaluators!", e);
		}
	}
}
