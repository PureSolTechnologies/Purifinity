package com.puresol.osgi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.felix.main.AutoProcessor;
import org.apache.log4j.Logger;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

public class FelixOSGi extends AbstractOSGi {

    private static final Logger logger = Logger.getLogger(FelixOSGi.class);

    private class ShutdownHook extends Thread {

	public void run() {
	    try {
		stopAndDisposeFramework();
	    } catch (Exception e) {
		logger.error("Error stopping framework: " + e.getMessage(), e);
	    }
	}

    }

    private Framework framework = null;
    private Thread shutdownHook = null;

    @Override
    protected Framework createAndStartFramework() throws OSGiException {
	try {
	    framework = getFrameworkFactory().newFramework(null);
	    framework.init();
	    AutoProcessor.process(null, framework.getBundleContext());
	    framework.start();
	    return framework;
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    throw new OSGiException("Could not create framework!");
	}
    }

    @Override
    protected void stopAndDisposeFramework() throws OSGiException {
	try {
	    removeShutdownHook();
	    framework.waitForStop(10);
	} catch (InterruptedException e) {
	    logger.error(e.getMessage(), e);
	    throw new OSGiException("Could not create framework!");
	}
    }

    private FrameworkFactory getFrameworkFactory() throws OSGiException {
	try {
	    URL url = FelixOSGi.class
		    .getClassLoader()
		    .getResource(
			    "META-INF/services/org.osgi.framework.launch.FrameworkFactory");
	    if (url == null) {
		throw new OSGiException("Could not find framework factory.");
	    }
	    BufferedReader br = new BufferedReader(new InputStreamReader(url
		    .openStream()));
	    try {
		for (String s = br.readLine(); s != null; s = br.readLine()) {
		    s = s.trim();
		    // Try to load first non-empty, non-commented line.
		    if ((s.length() > 0) && (s.charAt(0) != '#')) {
			return (FrameworkFactory) Class.forName(s)
				.newInstance();
		    }
		}
	    } finally {
		if (br != null)
		    br.close();
	    }
	    addShutdownHook();
	} catch (InstantiationException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (ClassNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	throw new OSGiException("Could not start framework.");
    }

    private void addShutdownHook() {
	removeShutdownHook();
	shutdownHook = new ShutdownHook();
	Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    private void removeShutdownHook() {
	if (shutdownHook != null) {
	    Runtime.getRuntime().removeShutdownHook(shutdownHook);
	    shutdownHook = null;
	}
    }
}
