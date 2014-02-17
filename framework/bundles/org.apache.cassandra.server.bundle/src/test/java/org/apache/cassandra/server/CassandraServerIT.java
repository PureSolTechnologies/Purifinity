package org.apache.cassandra.server;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

public class CassandraServerIT {

	private static File targetDirectory;

	@BeforeClass
	public static void setEclipseHomeLocationForCassandraServer() {
		File userDirectory = new File(System.getProperty("user.dir"));
		assertTrue("User directory '" + userDirectory + "' does not exsit.",
				userDirectory.exists());
		File targetDirectory = new File(userDirectory, "target");
		assertTrue(
				"Target directory '" + targetDirectory + "' does not exsit.",
				targetDirectory.exists());
	}

	@Test
	public void test() throws Exception {
		CassandraServer.start(targetDirectory);
		assertTrue(CassandraServer.waitForStartup(15000));
		CassandraServer.stop();
	}

}
