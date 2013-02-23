package com.puresol.coding.evaluation.api;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EvaluatorStoreFactoryOSGi extends EvaluatorStoreFactory {

	@Override
	public EvaluatorStore createInstance(Class<? extends Evaluator> clazz) {
		try {
			BundleContext bundleContext = Activator.getBundleContext();
			Collection<ServiceReference<EvaluatorStore>> serviceReferences = bundleContext
					.getServiceReferences(EvaluatorStore.class, "(evaluator="
							+ clazz.getName() + ")");
			if ((serviceReferences == null) || (serviceReferences.size() == 0)) {
				return null;
			}
			if (serviceReferences.size() > 1) {
				throw new RuntimeException(
						"More than one evaluator store was registered for '"
								+ clazz.getName() + "'!");
			}
			ServiceReference<EvaluatorStore> serviceReference = serviceReferences
					.iterator().next();
			return bundleContext.getService(serviceReference);
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException("Could not find store for evaluator '"
					+ clazz.getName() + "'!", e);
		}
	}
}
