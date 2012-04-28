package com.puresol.coding.analysis;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.puresol.coding.analysis.api.AnalysisStore;
import com.puresol.coding.analysis.api.DirectoryStore;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.coding.evaluation.api.EvaluationResultsStore;
import com.puresol.coding.evaluator.EvaluationResultsStoreImpl;

public class Activator implements BundleActivator {

    private static BundleContext context = null;
    private ServiceRegistration<AnalysisStore> analysisStoreService;
    private ServiceRegistration<FileStore> fileStoreService;
    private ServiceRegistration<DirectoryStore> directoryStoreService;
    private ServiceRegistration<EvaluationResultsStore> evaluationResultsStoreService;

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
	analysisStoreService = context.registerService(AnalysisStore.class,
		new AnalysisStoreImpl(), new Hashtable<String, Object>());
	fileStoreService = context.registerService(FileStore.class,
		new FileStoreImpl(), new Hashtable<String, Object>());
	directoryStoreService = context.registerService(DirectoryStore.class,
		new DirectoryStoreImpl(), new Hashtable<String, Object>());
	evaluationResultsStoreService = context.registerService(
		EvaluationResultsStore.class, new EvaluationResultsStoreImpl(),
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
	context.ungetService(evaluationResultsStoreService.getReference());
	analysisStoreService = null;
	fileStoreService = null;
	directoryStoreService = null;
	evaluationResultsStoreService = null;
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
