package com.puresoltechnologies.purifinity.client.lang.c11;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {

    // The shared instance
    private static Activator plugin = null;

    @Override
    public void start(BundleContext context) throws Exception {
	super.start(context);
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

}
