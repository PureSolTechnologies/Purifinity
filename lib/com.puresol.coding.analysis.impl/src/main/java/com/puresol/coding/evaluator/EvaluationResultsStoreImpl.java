package com.puresol.coding.evaluator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.Activator;
import com.puresol.coding.evaluation.api.EvaluationResultsStore;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.coding.evaluation.api.EvaluatorStore;

public class EvaluationResultsStoreImpl implements EvaluationResultsStore {

    @Override
    public EvaluatorStore createEvaluatorStore(
	    Class<? extends Evaluator<? extends EvaluatorResults>> evaluator) {
	try {
	    BundleContext bundleContext = Activator.getBundleContext();
	    ServiceReference[] serviceReferences = bundleContext
		    .getServiceReferences(EvaluatorStore.class.getName(),
			    "(evaluator=" + evaluator.getClass().getName()
				    + ")");
	    ServiceReference serviceReference = serviceReferences[0];
	    EvaluatorStore store = (EvaluatorStore) bundleContext
		    .getService(serviceReference);
	    return store;
	} catch (InvalidSyntaxException e) {
	    throw new RuntimeException("Could not find store for evaluator '"
		    + evaluator.getClass().getName() + "'!", e);
	}
    }
}
