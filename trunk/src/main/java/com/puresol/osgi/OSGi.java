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

import com.puresol.utils.DirectoryUtilities;
import com.puresol.utils.RegExpUtilities;

/**
 * This is a small wrapper and utility class for starting and using a OSGi
 * framework.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class OSGi {

	private static final Logger logger = Logger.getLogger(OSGi.class);

	public static final String OSGI_FRAMEWORK_FACTORY_PROPERTIES = "/META-INF/services/org.osgi.framework.launch.FrameworkFactory";
	public static final String OSGI_PROPERTIES = "/META-INF/services/osgi.properties";
	private static final String INSTALLDIR_KEY = "$installdir";
	private static final String USERDIR_KEY = "$userdir";

	private Framework framework = null;
	private BundleContext context = null;
	private Map<String, Object> configuration;

	/**
	 * Used to force this class into singleton pattern.
	 */
	public OSGi() {
		loadConfiguration();
	}

	public OSGi(Properties properties) {
		setConfiguration(properties);
	}

	public OSGi(Map<String, Object> configuration) {
		setConfiguration(configuration);
	}

	private void setConfiguration(Map<String, Object> configuration) {
		this.configuration = configuration;
	}

	private void setConfiguration(Properties properties) {
		Map<String, Object> configuration = new HashMap<String, Object>();
		for (Object key : properties.keySet()) {
			String value = (String) properties.get(key);
			logger.info("OSGi setting: " + key.toString() + " --> " + value);
			if (value.contains(INSTALLDIR_KEY)) {
				value = value.replaceAll(
						RegExpUtilities.string2RegExp(INSTALLDIR_KEY),
						DirectoryUtilities.getInstallationDirectory(getClass(),
								true).toString());
				logger.info("replacement: " + key.toString() + " --> " + value);
			}
			if (value.contains(USERDIR_KEY)) {
				value = value.replaceAll(
						RegExpUtilities.string2RegExp(USERDIR_KEY),
						DirectoryUtilities.getUserDirectory().toString());
				logger.info("replacement: " + key.toString() + " --> " + value);
			}
			configuration.put((String) key, value);
		}
		setConfiguration(configuration);
	}

	public void start() throws OSGiException, BundleException {
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
		framework = factory.newFramework(configuration);
		framework.start();
		context = framework.getBundleContext();
		logger.info("OSGi framework started.");
		logger.info("Bundle directory is '"
				+ context.getProperty("org.osgi.framework.storage") + "'");
	}

	private void loadConfiguration() {
		try {
			Properties props = new Properties();
			InputStream inputStream = getClass().getResourceAsStream(
					OSGi.OSGI_PROPERTIES);
			if (inputStream == null) {
				logger.warn("No '"
						+ OSGI_PROPERTIES
						+ "' file was found. Defaults from OSGi framework will be used!");
				return;
			}
			props.load(inputStream);
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
			URL url = getClass().getResource(OSGI_FRAMEWORK_FACTORY_PROPERTIES);
			if (url != null) {
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
							return (FrameworkFactory) Class.forName(s)
									.newInstance();
						}
					}
				} finally {
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
		throw new OSGiException("Could not load framework factory.");
	}

	public void stop() throws BundleException {
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

	public boolean isStarted() {
		return (context != null);
	}
}
