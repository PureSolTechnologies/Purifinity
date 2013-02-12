package com.puresol.coding.evaluation.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EvaluatorStoreFactoryOSGI extends EvaluatorStoreFactory {

	@Override
	public EvaluatorStore createInstance(Class<? extends Evaluator> clazz) {
		try {
			BundleContext bundleContext = Activator.getBundleContext();
			ServiceReference[] serviceReferences = bundleContext
					.getServiceReferences(EvaluatorStore.class.getName(),
							"(evaluator=" + clazz.getName() + ")");
			if ((serviceReferences == null) || (serviceReferences.length == 0)) {
				return null;
			}
			if (serviceReferences.length > 1) {
				throw new RuntimeException(
						"More than one evaluator store was registered for '"
								+ clazz.getName() + "'!");
			}
			ServiceReference serviceReference = serviceReferences[0];
			EvaluatorStore store = (EvaluatorStore) bundleContext
					.getService(serviceReference);
			return store;
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException("Could not find store for evaluator '"
					+ clazz.getName() + "'!", e);
		}
	}
}
