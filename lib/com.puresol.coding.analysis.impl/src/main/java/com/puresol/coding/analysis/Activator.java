package com.puresol.coding.analysis;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.DirectoryStore;
import com.puresol.coding.analysis.api.FileStore;

public class Activator implements BundleActivator {

    private static BundleContext context = null;
    private ServiceRegistration analysisStoreService;
    private ServiceRegistration fileStoreService;
    private ServiceRegistration directoryStoreService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
     * )
     */
    @Override
    public void start(BundleContext context) throws Exception {
	if (Activator.context != null) {
	    throw new RuntimeException("Plugin " + getClass().getName()
		    + " was already activated!");
	}
	Activator.context = context;
	analysisStoreService = context.registerService(
		AnalysisStore.class.getName(), new AnalysisStoreImpl(),
		new Hashtable<String, Object>());
	fileStoreService = context.registerService(FileStore.class.getName(),
		new FileStoreImpl(), new Hashtable<String, Object>());
	directoryStoreService = context.registerService(
		DirectoryStore.class.getName(), new DirectoryStoreImpl(),
		new Hashtable<String, Object>());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
	if (Activator.context == null) {
	    throw new RuntimeException("Plugin " + getClass().getName()
		    + " was never activated!");
	}
	context.ungetService(analysisStoreService.getReference());
	context.ungetService(fileStoreService.getReference());
	context.ungetService(directoryStoreService.getReference());
	analysisStoreService = null;
	fileStoreService = null;
	directoryStoreService = null;
	Activator.context = null;
    }

    public static BundleContext getBundleContext() {
	if (Activator.context == null) {
	    throw new RuntimeException("Plugin " + Activator.class.getName()
		    + " was never activated!");
	}
	return context;
    }

}
