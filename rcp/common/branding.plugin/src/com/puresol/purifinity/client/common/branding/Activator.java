package com.puresol.purifinity.client.common.branding;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
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

    @Override
    protected void initializeImageRegistry(ImageRegistry reg) {
	super.initializeImageRegistry(reg);

	for (ClientImages image : ClientImages.values()) {
	    String pathAndKey = image.getPath();
	    URL imageResource = Activator.class.getResource(pathAndKey);
	    if (imageResource == null) {
		throw new RuntimeException("Resource '" + pathAndKey
			+ "' was not found.");
	    }
	    ImageDescriptor imageDescriptor = ImageDescriptor
		    .createFromURL(imageResource);
	    reg.put(pathAndKey, imageDescriptor);
	}
    }
}
