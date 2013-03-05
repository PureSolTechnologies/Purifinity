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
public class EvaluatorStoreFactoryOSGi extends EvaluatorStoreFactory {

    private static final BundleContext bundleContext = Activator
	    .getBundleContext();

    @Override
    public EvaluatorStore createInstance(Class<? extends Evaluator> clazz) {
	try {
	    ServiceReference<?>[] serviceReferences = bundleContext
		    .getAllServiceReferences(EvaluatorStore.class.getName(),
			    null);
	    if ((serviceReferences == null) || (serviceReferences.length == 0)) {
		throw new RuntimeException(
			"No evaluator store was registered for '"
				+ clazz.getName() + "'!");
	    }
	    if (serviceReferences.length > 1) {
		throw new RuntimeException(
			"More than one evaluator store was registered for '"
				+ clazz.getName() + "'!");
	    }
	    ServiceReference<EvaluatorStore> serviceReference = (ServiceReference<EvaluatorStore>) serviceReferences[0];
	    return bundleContext.getService(serviceReference);
	} catch (InvalidSyntaxException e) {
	    throw new RuntimeException("Could not find store for evaluator '"
		    + clazz.getName() + "'!", e);
	}
    }
}
