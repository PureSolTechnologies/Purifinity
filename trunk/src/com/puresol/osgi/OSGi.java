package com.puresol.osgi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

public class OSGi {

	private static final Logger logger = Logger.getLogger(OSGi.class);

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

	public static synchronized void stopAndKillInstance()
			throws BundleException {
		if (instance != null) {
			instance.stop();
			instance = null;
		}
	}

	private Framework framework = null;
	private BundleContext context = null;

	private OSGi() {
	}

	private void start() throws OSGiException, BundleException {
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
	}

	private Map<String, Object> getConfiguration() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.FRAMEWORK_STORAGE, "plugins");
		return map;
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
		context = null;
		framework.stop();
		framework = null;
	}

	public BundleContext getContext() {
		return context;
	}
}
