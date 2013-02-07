package com.puresol.coding.analysis.api;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresol.coding.analysis.api.CodeStore;


/**
 * This is the central factory for a code store. It creates {@link CodeStore}
 * objects.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeStoreFactory {

    private static CodeStore codeStore = null;

    public static CodeStore getInstance() {
	if (codeStore == null) {
	    createInstance();
	}
	return codeStore;
    }

    private static synchronized void createInstance() {
	if (codeStore == null) {
	    BundleContext bundleContext = Activator.getBundleContext();
	    ServiceReference serviceReference = bundleContext
		    .getServiceReference(CodeStore.class.getName());
	    if (serviceReference != null) {
		codeStore = (CodeStore) bundleContext
			.getService(serviceReference);
	    }
	}
    }
}
