package com.puresoltechnologies.purifinity.framework.evaluation.commons.impl;

import java.util.Collection;
import java.util.Iterator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorStoreFactory;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EvaluatorStoreFactoryOSGi extends EvaluatorStoreFactory {

	private static final BundleContext bundleContext = Activator
			.getBundleContext();

	@Override
	public EvaluatorStore createInstance(Class<? extends Evaluator> clazz) {
		try {
			Collection<ServiceReference<EvaluatorStore>> serviceReferences = bundleContext
					.getServiceReferences(EvaluatorStore.class, "(evaluator="
							+ clazz.getName() + ")");
			Iterator<ServiceReference<EvaluatorStore>> iterator = serviceReferences
					.iterator();
			if (!iterator.hasNext()) {
				throw new RuntimeException(
						"No evaluator store was registered for '"
								+ clazz.getName() + "'!");
			}
			ServiceReference<EvaluatorStore> serviceReference = iterator.next();
			if (iterator.hasNext()) {
				throw new RuntimeException(
						"More than one evaluator store was registered for '"
								+ clazz.getName() + "'!");
			}
			return bundleContext.getService(serviceReference);
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException("Could not find store for evaluator '"
					+ clazz.getName() + "'!", e);
		}
	}
}
