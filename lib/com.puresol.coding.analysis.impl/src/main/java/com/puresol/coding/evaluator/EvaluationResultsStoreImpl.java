package com.puresol.coding.evaluator;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.Activator;
import com.puresol.coding.evaluation.api.DirectoryResults;
import com.puresol.coding.evaluation.api.EvaluationResultsStore;
import com.puresol.coding.evaluation.api.Evaluator;
import com.puresol.coding.evaluation.api.EvaluatorResults;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.FileResults;
import com.puresol.coding.evaluation.api.ProjectResults;

public class EvaluationResultsStoreImpl implements EvaluationResultsStore {

    @Override
    public EvaluatorStore<FileResults, DirectoryResults, ProjectResults> createEvaluatorStore(
	    Class<? extends Evaluator<? extends EvaluatorResults>> evaluator) {
	try {
	    BundleContext bundleContext = Activator.getBundleContext();
	    @SuppressWarnings("rawtypes")
	    Collection<ServiceReference<EvaluatorStore>> serviceReferences = bundleContext
		    .getServiceReferences(EvaluatorStore.class, "(evaluator="
			    + evaluator.getClass().getName() + ")");
	    @SuppressWarnings("rawtypes")
	    ServiceReference<EvaluatorStore> serviceReference = serviceReferences
		    .iterator().next();
	    @SuppressWarnings("unchecked")
	    EvaluatorStore<FileResults, DirectoryResults, ProjectResults> store = bundleContext
		    .getService(serviceReference);
	    return store;
	} catch (InvalidSyntaxException e) {
	    throw new RuntimeException("Could not find store for evaluator '"
		    + evaluator.getClass().getName() + "'!", e);
	}
    }
}
