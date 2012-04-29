package com.puresol.coding.evaluation.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.api.Activator;

public class EvaluationResultsStoreFactory {

    private static EvaluationResultsStore resultsStore = null;

    public static EvaluationResultsStore getInstance() {
	if (resultsStore == null) {
	    createInstance();
	}
	return resultsStore;
    }

    private static synchronized void createInstance() {
	if (resultsStore == null) {
	    BundleContext bundleContext = Activator.getBundleContext();
	    ServiceReference serviceReference = bundleContext
		    .getServiceReference(EvaluationResultsStore.class.getName());
	    if (serviceReference != null) {
		resultsStore = (EvaluationResultsStore) bundleContext
			.getService(serviceReference);
	    }
	}
    }

}
