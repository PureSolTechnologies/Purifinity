package com.puresoltechnologies.purifinity.commons.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.cassandra.server.CassandraServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * This is an abstract base class for all tests which need a running Cassandra
 * database. The standalone version for Prufinity uses {@link CassandraServer}
 * to unpack, configure and start a Cassandra installation. This functionality
 * is used here.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AbstractCassandraTest {

	/**
	 * Wait time out for Cassandra to be available in [ms].
	 */
	private static final long DEFAULT_TIMEOUT = 600000;

	@BeforeClass
	public static void startCassandra() throws IOException {
		CassandraServer.start(getEclipseHomeLocationForCassandraServer());
		assertTrue("Waiting for Cassandra startup was not successful.",
				CassandraServer.waitForStartup(DEFAULT_TIMEOUT));
	}

	private static File getEclipseHomeLocationForCassandraServer() {
		File userDirectory = new File(System.getProperty("user.dir"));
		assertTrue("User directory '" + userDirectory + "' does not exsit.",
				userDirectory.exists());
		File targetDirectory = new File(userDirectory, "target");
		assertTrue(
				"Target directory '" + targetDirectory + "' does not exsit.",
				targetDirectory.exists());
		return targetDirectory;
	}

	@AfterClass
	public static void stopCassandra() {
		CassandraServer.stop();
		assertTrue("Waiting for Cassandra shutdown was not successful.",
				CassandraServer.waitForShutdown(DEFAULT_TIMEOUT));
	}

}
