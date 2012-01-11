package com.puresol.osgi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

import com.puresol.WeakReferenceSet;
import com.puresol.utils.JARUtilities;

/**
 * This is a small wrapper and utility class for starting and using a OSGi
 * framework.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class OSGi {

    private static final Logger logger = Logger.getLogger(OSGi.class);

    /**
     * This is the resource path for the class name of the OSGi framework
     * factory.
     */
    public static final String OSGI_FRAMEWORK_FACTORY_PROPERTIES = "/META-INF/services/org.osgi.framework.launch.FrameworkFactory";
    /**
     * This is the resource path to an additional properties file which defines
     * some options for framework startup.
     */
    public static final String OSGI_PROPERTIES = "/META-INF/services/osgi.properties";

    private final Map<String, String> configuration = new HashMap<String, String>();

    private Framework framework = null;
    private BundleContext context = null;

    private final WeakReferenceSet<OSGiFrameworkListener> frameworkListeners = new WeakReferenceSet<OSGiFrameworkListener>();

    /**
     * Used to force this class into singleton pattern.
     */
    public OSGi() {
	loadConfiguration();
    }

    public OSGi(Properties properties) {
	setConfiguration(properties);
    }

    public OSGi(Map<String, String> configuration) {
	setConfiguration(configuration);
    }

    private void setConfiguration(Map<String, String> configuration) {
	this.configuration.putAll(configuration);
    }

    private void setConfiguration(Properties properties) {
	for (Object key : properties.keySet()) {
	    String value = (String) properties.get(key);
	    logger.debug("OSGi setting: " + key.toString() + " --> " + value);
	    configuration.put((String) key, value);
	}
    }

    public synchronized void start() throws OSGiException, BundleException {
	logger.info("Starting OSGi framework...");
	preStart();
	actualStart();
	postStart();
	logger.info("OSGi framework started.");
	logger.info("Bundle directory is '"
		+ context.getProperty("org.osgi.framework.storage") + "'");
    }

    private void preStart() {
	for (OSGiFrameworkListener listener : frameworkListeners) {
	    listener.preStart();
	}
    }

    private void actualStart() throws OSGiException, BundleException {
	FrameworkFactory factory = getFrameworkFactory();
	framework = factory.newFramework(configuration);
	framework.start();
	context = framework.getBundleContext();
    }

    private void postStart() {
	for (OSGiFrameworkListener listener : frameworkListeners) {
	    listener.postStart();
	}
    }

    private void loadConfiguration() {
	try {
	    Properties props = JARUtilities.readPropertyFile(getClass()
		    .getResource(OSGi.OSGI_PROPERTIES));
	    setConfiguration(props);
	} catch (IOException e) {
	    logger.warn("No '"
		    + OSGI_PROPERTIES
		    + "' file was found. Defaults from OSGi framework will be used!");
	    return;
	}
    }

    private FrameworkFactory getFrameworkFactory() throws OSGiException {
	try {
	    return (FrameworkFactory) Class.forName(
		    getFrameworkFactoryClassName()).newInstance();
	} catch (InstantiationException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (ClassNotFoundException e) {
	    logger.error(e.getMessage(), e);
	}
	throw new OSGiException("Could not load framework factory.");
    }

    private String getFrameworkFactoryClassName() throws OSGiException {
	URL url = getFrameworkFactoryConfigurationResource();
	return readFrameworkFactoryClassName(url);
    }

    private URL getFrameworkFactoryConfigurationResource() {
	URL url = getClass().getResource(OSGI_FRAMEWORK_FACTORY_PROPERTIES);
	if (url == null) {
	    throw new RuntimeException("Resource "
		    + OSGI_FRAMEWORK_FACTORY_PROPERTIES + " is not available. "
		    + "The resource is essential for defining the framework!");
	}
	return url;
    }

    private String readFrameworkFactoryClassName(URL url) throws OSGiException {
	try {
	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    url.openStream()));
	    try {
		for (String s = br.readLine(); s != null; s = br.readLine()) {
		    s = s.trim();
		    logger.info("Found class for OSGi FrameworkFactory in "
			    + "'META-INF/services/org.osgi.framework.launch.FrameworkFactory': '"
			    + s + "'");
		    logger.info("Creating instance...");
		    // Try to load first non-empty, non-commented line.
		    if ((s.length() > 0) && (s.charAt(0) != '#')) {
			return s;
		    }
		}
	    } finally {
		br.close();
	    }
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	throw new OSGiException("Could not load framework factory.");
    }

    public synchronized void stop() throws BundleException {
	if (framework == null) {
	    return;
	}
	logger.info("Stopping OSGi framework...");
	preStop();
	actualStop();
	postStop();
	logger.info("OSGi framework stopped.");
    }

    private void preStop() {
	for (OSGiFrameworkListener listener : frameworkListeners) {
	    listener.preStop();
	}
    }

    private void actualStop() throws BundleException {
	context = null;
	framework.stop();
	framework = null;
    }

    private void postStop() {
	for (OSGiFrameworkListener listener : frameworkListeners) {
	    listener.postStop();
	}
    }

    /**
     * This method returns the frameworks BundleContext.
     * 
     * @return The bundle context is returned.
     */
    public BundleContext getContext() {
	return context;
    }

    public boolean isStarted() {
	return (context != null);
    }

    public void addOSGiFrameworkListener(OSGiFrameworkListener listener) {
	frameworkListeners.add(listener);
    }

    public void removeOSGiFrameworkListener(OSGiFrameworkListener listener) {
	frameworkListeners.remove(listener);
    }

}
