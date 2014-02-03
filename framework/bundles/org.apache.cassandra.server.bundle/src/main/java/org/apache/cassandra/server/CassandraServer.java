package org.apache.cassandra.server;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CassandraServer {

	private static final Logger logger = LoggerFactory
			.getLogger(CassandraServer.class);

	private static final int THRIFT_PORT = 9042;

	private static Process cassandraProcess = null;

	public static void start() throws IOException {
		if (cassandraProcess != null) {
			throw new RuntimeException("Cassandra was already started.");
		}
		logger.info("Cassandra is about to start...");

		if (cassandraProcess != null) {
			throw new RuntimeException("Cassandra was already started.");
		}

		File eclipseHome = getEclipseHome();
		File databaseDirectory = getDatabaseDirectory(eclipseHome);
		CassandraDistribution.extract(databaseDirectory);

		File cassandraConfiguration = new File(databaseDirectory, "conf");
		System.setProperty("cassandra.data.directory",
				databaseDirectory.getPath());
		CassandraConfiguration.createConfigurationFile(cassandraConfiguration);

		File cassandraLib = new File(databaseDirectory, "lib");

		String javaAgent = "-javaagent:"
				+ new File(cassandraLib, "jamm-0.2.5.jar").getPath();

		String[] jarFiles = cassandraLib.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar");
			}
		});
		StringBuilder classPath = new StringBuilder(
				cassandraConfiguration.getPath());
		for (String jarFile : jarFiles) {
			classPath.append(File.pathSeparatorChar);
			classPath.append(new File(cassandraLib, jarFile).getPath());
		}

		List<String> jvmParams = new ArrayList<>();
		jvmParams.add("-XX:+UseThreadPriorities");
		jvmParams.add("-XX:ThreadPriorityPolicy=42");
		jvmParams.add("-Xms1024M");
		jvmParams.add("-Xmx1024M");
		jvmParams.add("-Xmn256M");
		jvmParams.add("-XX:+HeapDumpOnOutOfMemoryError");
		jvmParams.add("-Xss256k");
		jvmParams.add("-XX:StringTableSize=1000003");
		jvmParams.add("-XX:+UseParNewGC");
		jvmParams.add("-XX:+UseConcMarkSweepGC");
		jvmParams.add("-XX:+CMSParallelRemarkEnabled");
		jvmParams.add("-XX:SurvivorRatio=8");
		jvmParams.add("-XX:MaxTenuringThreshold=1");
		jvmParams.add("-XX:CMSInitiatingOccupancyFraction=75");
		jvmParams.add("-XX:+UseCMSInitiatingOccupancyOnly");
		jvmParams.add("-XX:+UseTLAB");
		jvmParams.add("-XX:+UseCondCardMark");

		List<String> properties = new ArrayList<>();
		properties.add("-Djava.net.preferIPv4Stack=true");
		properties.add("-Dcom.sun.management.jmxremote.port=7199");
		properties.add("-Dcom.sun.management.jmxremote.ssl=false");
		properties.add("-Dcom.sun.management.jmxremote.authenticate=false");
		properties.add("-Dlog4j.configuration=log4j-server.properties");
		properties.add("-Dlog4j.defaultInitOverride=true");

		String mainClass = "org.apache.cassandra.service.CassandraDaemon";

		List<String> command = new ArrayList<>();
		command.add("java");
		command.add("-ea");
		command.add(javaAgent);
		command.addAll(jvmParams);
		command.addAll(properties);
		command.add("-cp");
		command.add(classPath.toString());
		command.add(mainClass);
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.inheritIO();
		cassandraProcess = processBuilder.start();
		logger.info("Cassandra started.");
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

	private static File getDatabaseDirectory(File eclipseHome) {
		File dataDirectory = new File(eclipseHome, "db");
		return dataDirectory;
	}

	public static void stop() {
		if (cassandraProcess != null) {
			logger.info("Cassandra is about to stop...");
			cassandraProcess.destroy();
			cassandraProcess = null;
			logger.info("Cassandra stopped.");
		}
	}

	public static boolean waitForStartup(long timeout) {
		Date start = new Date();
		while (!isStarted()) {
			Date current = new Date();
			if (current.getTime() - start.getTime() >= timeout) {
				return false;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
		return isStarted();
	}

	private static boolean isStarted() {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress("localhost", THRIFT_PORT));
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
