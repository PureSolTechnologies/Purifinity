package com.puresoltechnologies.purifinity.client.storage.db;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.cassandra.CassandraConfiguration;
import org.apache.cassandra.service.CassandraDaemon;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.purifinity.framework.commons.utils.PropertiesUtils;

public class Activator extends AbstractUIPlugin {

	private static final Logger logger = LoggerFactory
			.getLogger(Activator.class);

	// The shared instance
	private static Activator plugin = null;

	private CassandraDaemon daemon = null;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		if (plugin != null) {
			throw new RuntimeException("A " + getClass().getName()
					+ " plugin was already started!");
		}
		plugin = this;
		startCassandra();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		stopCassandra();
		super.stop(context);
		if (plugin == null) {
			throw new RuntimeException("A " + getClass().getName()
					+ " plugin was never started!");
		}
		plugin = null;
	}

	private void startCassandra() throws IOException {
		if (daemon != null) {
			throw new RuntimeException("Cassandra was already started.");
		}

		System.err.println(PropertiesUtils.toString(System.getProperties()));

		logger.info("Cassandra is about to start...");
		String eclipseHomeLocation = System
				.getProperty("eclipse.home.location");
		if (eclipseHomeLocation == null) {
			throw new RuntimeException(
					"Eclipse home location (eclipse.home.location) was not set.");
		}
		URL eclipseHomeLocationURL = new URL(eclipseHomeLocation);
		File dataDirectory = new File(eclipseHomeLocationURL.getFile(), "db");
		String dataDirectoryString = dataDirectory.getPath();
		System.setProperty("cassandra.data.directory", dataDirectoryString);
		File configurationDirectory = new File(
				eclipseHomeLocationURL.getFile(), "configuration");
		File configurationFile = CassandraConfiguration
				.createConfigurationFile(configurationDirectory);
		System.setProperty("cassandra.config",
				"file:" + configurationFile.getPath());
		daemon = new CassandraDaemon();
		daemon.init(null);
		daemon.start();
		logger.info("Cassandra started.");
	}

	private void stopCassandra() {
		if (daemon == null) {
			throw new RuntimeException("Cassandra was not started, yet.");
		}
		logger.info("Cassandra is about to stop...");
		daemon.stop();
		daemon = null;
		logger.info("Cassandra stopped.");
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
