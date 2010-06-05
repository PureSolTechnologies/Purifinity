package com.puresol.osgi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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

/**
 * This is a small wrapper and utility class for starting and using a OSGi
 * framework.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class OSGi {

    private static final Logger logger = Logger.getLogger(OSGi.class);

    public static final String OSGI_CONFIGURATION_RESOURCE = "/META-INF/services/osgi.properties";

    private static OSGi instance = null;

    /**
     * This method creates a new instance of an OSGi framework and returns its
     * reference. During this process the framework is automatically started and
     * ready for usage.
     * 
     * @return An instance of OSGi is returned.
     * @throws BundleException
     * @throws OSGiException
     */
    public static OSGi getStartedInstance() throws OSGiException,
	    BundleException {
	if (instance == null) {
	    createAndStartInstance();
	}
	return instance;
    }

    private static synchronized void createAndStartInstance()
	    throws OSGiException, BundleException {
	if (instance == null) {
	    instance = new OSGi();
	    instance.start();
	}
    }

    /**
     * This method stops the OSGi framework and all its services. Aftwards the
     * singleton instance is killed and goes the way of the dodo.
     * 
     * @throws BundleException
     */
    public static synchronized void stopAndKillInstance()
	    throws BundleException {
	if (instance != null) {
	    instance.stop();
	    instance = null;
	}
    }

    /**
     * This mehtod is used to check the current status of the OSGi framework.
     * 
     * @return True is returned if the framework is running. Otherwise false is
     *         returned.
     */
    public static boolean isStarted() {
	return (instance != null);
    }

    private Framework framework = null;
    private BundleContext context = null;

    /**
     * Used to force this class into singleton pattern.
     */
    private OSGi() {
    }

    private void start() throws OSGiException, BundleException {
	logger.info("Starting OSGi framework...");
	if (framework != null) {
	    stop();
	}
	FrameworkFactory factory = null;
	try {
	    factory = getFrameworkFactory();
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	    throw new OSGiException(
		    "Could not create factory for OSGi framwork!");
	}
	framework = factory.newFramework(getConfiguration());
	framework.start();
	context = framework.getBundleContext();
	logger.info("OSGi framework started.");
	System.out.println(context.getProperty("org.osgi.framework.storage"));
    }

    private Map<String, Object> getConfiguration() {
	try {
	    Map<String, Object> map = new HashMap<String, Object>();
	    Properties props = new Properties();
	    InputStream inputStream = getClass().getResourceAsStream(
		    OSGi.OSGI_CONFIGURATION_RESOURCE);
	    if (inputStream == null) {
		logger
			.warn("No '"
				+ OSGI_CONFIGURATION_RESOURCE
				+ "' file was found. Defaults from OSGi framework will be used!");
		return null;
	    }
	    props.load(inputStream);
	    for (Object key : props.keySet()) {
		logger.info("OSGi setting: " + key.toString() + " --> "
			+ props.getProperty(key.toString()));
		map.put((String) key, props.getProperty((String) key));
	    }
	    return map;
	} catch (IOException e) {
	    logger
		    .warn("No '"
			    + OSGI_CONFIGURATION_RESOURCE
			    + "' file was found. Defaults from OSGi framework will be used!");
	    return null;
	}
    }

    private FrameworkFactory getFrameworkFactory() throws OSGiException {
	try {
	    URL url = OSGi.class
		    .getClassLoader()
		    .getResource(
			    "META-INF/services/org.osgi.framework.launch.FrameworkFactory");
	    if (url != null) {
		BufferedReader br = new BufferedReader(new InputStreamReader(
			url.openStream()));
		try {
		    for (String s = br.readLine(); s != null; s = br.readLine()) {
			s = s.trim();
			logger
				.info("Found class for OSGi FrameworkFactory in "
					+ "'META-INF/services/org.osgi.framework.launch.FrameworkFactory': '"
					+ s + "'");
			logger.info("Creating instance...");
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
	    }
	} catch (InstantiationException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (ClassNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	throw new OSGiException("Could not find framework factory.");
    }

    private void stop() throws BundleException {
	logger.info("Stopping OSGi framework...");
	context = null;
	framework.stop();
	framework = null;
	logger.info("OSGi framework stopped.");
    }

    /**
     * This method returns the frameworks BundleContext.
     * 
     * @return The bundle context is returned.
     */
    public BundleContext getContext() {
	return context;
    }
}
