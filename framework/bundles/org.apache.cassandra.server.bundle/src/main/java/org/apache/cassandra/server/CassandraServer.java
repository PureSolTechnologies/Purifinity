package org.apache.cassandra.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.cassandra.service.CassandraDaemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CassandraServer {

	private static final Logger logger = LoggerFactory
			.getLogger(CassandraServer.class);

	private static CassandraDaemon daemon = null;

	public static void start() throws IOException {
		if (daemon != null) {
			throw new RuntimeException("Cassandra was already started.");
		}
		logger.info("Cassandra is about to start...");
		setRequiredSystemProperties();
		daemon = new CassandraDaemon();
		Thread serverThread = new Thread(new Runnable() {
			@Override
			public void run() {
				daemon.activate();
			}
		}, "CassandraServerThread");
		serverThread.setDaemon(true);
		serverThread.start();
		logger.info("Cassandra started.");
	}

	private static void setRequiredSystemProperties()
			throws MalformedURLException, FileNotFoundException, IOException {
		File eclipseHome = getEclipseHome();
		// Set data directory
		File dataDirectory = getDataDirectory(eclipseHome);
		System.setProperty("cassandra.data.directory", dataDirectory.getPath());
		// Set configuration file
		File configurationFile = getConfigurationFile(eclipseHome);
		System.setProperty("cassandra.config",
				"file:" + configurationFile.getPath());
	}

	private static File getEclipseHome() throws MalformedURLException {
		String eclipseHomeLocation = System
				.getProperty("eclipse.home.location");
		if (eclipseHomeLocation == null) {
			throw new RuntimeException(
					"Eclipse home location (eclipse.home.location) was not set.");
		}
		URL eclipseHomeLocationURL = new URL(eclipseHomeLocation);
		File eclipseHomeDirectory = new File(eclipseHomeLocationURL.getFile());
		return eclipseHomeDirectory;
	}

	private static File getDataDirectory(File eclipseHome) {
		File dataDirectory = new File(eclipseHome, "db");
		return dataDirectory;
	}

	private static File getConfigurationFile(File eclipseHome)
			throws FileNotFoundException, IOException {
		File configurationDirectory = new File(eclipseHome, "configuration");
		File configurationFile = CassandraConfiguration
				.createConfigurationFile(configurationDirectory);
		return configurationFile;
	}

	public static void stop() {
		if (daemon == null) {
			throw new RuntimeException("Cassandra was not started, yet.");
		}
		logger.info("Cassandra is about to stop...");
		daemon.deactivate();
		daemon = null;
		logger.info("Cassandra stopped.");
	}

	public static boolean isNativeServerRunning() {
		if (daemon == null) {
			return false;
		}
		return daemon.nativeServer == null ? false : daemon.nativeServer
				.isRunning();
	}

	public static boolean isThriftServerRunning() {
		if (daemon == null) {
			return false;
		}
		return daemon.thriftServer == null ? false : daemon.thriftServer
				.isRunning();
	}

	/**
	 * This method blocks until the Cassandra server is started or a cefined
	 * timeout occurs.
	 * 
	 * @param timeout
	 *            is the maximum time to wait for the server to start in
	 *            milliseconds.
	 * @return <code>true</code> is returned if the server was started
	 *         successfully. False is returned otherwise.
	 */
	public static boolean waitForStartup(long timeout) {
		Date start = new Date();
		while ((!CassandraServer.isNativeServerRunning())
				|| (!CassandraServer.isThriftServerRunning())) {
			Date current = new Date();
			if (current.getTime() - start.getTime() >= timeout) {
				break;
			}
			if (logger.isInfoEnabled()) {
				StringBuilder builder = new StringBuilder();
				builder.append("Wait for Cassandra startup... (time: ");
				builder.append((current.getTime() - start.getTime()) / 1000.0);
				builder.append("s; ");
				builder.append("native server: ");
				builder.append(CassandraServer.isNativeServerRunning());
				builder.append("; ");
				builder.append("thrift server: ");
				builder.append(CassandraServer.isThriftServerRunning());
				builder.append(")...");
				logger.info(builder.toString());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
		if ((!isNativeServerRunning()) || (!isThriftServerRunning())) {
			logger.warn("Cassandra not started within " + timeout + "ms.");
			return false;
		}
		Date current = new Date();
		logger.info("Cassandra started. (time: "
				+ (current.getTime() - start.getTime()) / 1000.0 + "s)");
		return true;
	}
}
