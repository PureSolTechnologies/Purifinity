package com.puresol.coding.client;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.puresol.coding.client.controls.EvaluatorGUIFactory;
import com.puresol.coding.client.evaluation.sloc.SLOCEvaluatorGUIFactory;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "com.puresol.coding.client.plugin"; //$NON-NLS-1$

    // The shared instance
    private static Activator plugin = null;

    @Override
    public void start(BundleContext context) throws Exception {
	super.start(context);
	Dictionary<String, String> dictionary = new Hashtable<String, String>();
	Dictionary<String, String> headers = context.getBundle().getHeaders();
	Enumeration<String> keys = headers.keys();
	while (keys.hasMoreElements()) {
	    String key = keys.nextElement();
	    dictionary.put(key, headers.get(key));
	}
	dictionary.put("evaluator", SLOCEvaluator.class.getName());
	context.registerService(EvaluatorGUIFactory.class,
		new SLOCEvaluatorGUIFactory(), dictionary);
	if (plugin != null) {
	    throw new RuntimeException("A " + getClass().getName()
		    + " plugin was already started!");
	}
	plugin = this;
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	super.stop(context);
	if (plugin == null) {
	    throw new RuntimeException("A " + getClass().getName()
		    + " plugin was never started!");
	}
	plugin = null;
    }

    @Override
    protected void initializeImageRegistry(ImageRegistry reg) {
	super.initializeImageRegistry(reg);
	add(reg, ClientImages.DATABASE_REFRESH_16x16);

	add(reg, ClientImages.FOLDER_16x16);
	add(reg, ClientImages.FOLDERS_16x16);
	add(reg, ClientImages.FOLDERS_EXPLORER_16x16);
	add(reg, ClientImages.FOLDER_ADD_16x16);
	add(reg, ClientImages.FOLDER_EDIT_16x16);
	add(reg, ClientImages.FOLDER_DELETE_16x16);
	add(reg, ClientImages.DATABASE_FOLDER_16x16);
	add(reg, ClientImages.DOCUMENT_EMPTY_16x16);

	add(reg, ClientImages.ANALYSIS_16x16);
	add(reg, ClientImages.ANALYZES_VIEW_16x16);
	add(reg, ClientImages.ANALYSIS_ADD_16x16);
	add(reg, ClientImages.ANALYSIS_EDIT_16x16);
	add(reg, ClientImages.ANALYSIS_DELETE_16x16);

	add(reg, ClientImages.ANALYSIS_RUN_16x16);
	add(reg, ClientImages.ANALYSIS_RUNS_VIEW_16x16);
	add(reg, ClientImages.ANALYSIS_RUN_ADD_16x16);
	add(reg, ClientImages.ANALYSIS_RUN_EDIT_16x16);
	add(reg, ClientImages.ANALYSIS_RUN_DELETE_16x16);
    }

    private void add(ImageRegistry reg, String keyAndPath) {
	reg.put(keyAndPath, ImageDescriptor.createFromURL(Activator.class
		.getResource(keyAndPath)));
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static Activator getDefault() {
	if (plugin == null) {
	    throw new RuntimeException("A " + Activator.class.getName()
		    + " plugin was never started!");
	}
	return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
	return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }
}
