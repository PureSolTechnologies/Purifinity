package com.puresoltechnologies.purifinity.client.storage.db;

import java.io.IOException;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator extends AbstractUIPlugin {

	private static final Logger logger = LoggerFactory
			.getLogger(Activator.class);

	// The shared instance
	private static Activator plugin = null;

	// private CassandraDaemon daemon = null;

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
		// if (daemon != null) {
		// throw new RuntimeException("Cassandra was already initialized.");
		// }
		// daemon = new CassandraDaemon();
		logger.info("Cassandra is about to start...");
		// daemon.init(null);
		// daemon.start();
		logger.info("Cassandra started.");
	}

	private void stopCassandra() {
		// if (daemon == null) {
		// throw new RuntimeException("Cassandra was never initialized.");
		// }
		logger.info("Cassandra is about to stop...");
		// daemon.stop();
		// daemon = null;
		logger.info("Cassandra was stopped.");
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
