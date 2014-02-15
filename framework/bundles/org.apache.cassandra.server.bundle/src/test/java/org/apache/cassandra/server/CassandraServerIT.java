package org.apache.cassandra.server;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

public class CassandraServerIT {

	@BeforeClass
	public static void setEclipseHomeLocationForCassandraServer() {
		File userDirectory = new File(System.getProperty("user.dir"));
		assertTrue("User directory '" + userDirectory + "' does not exsit.",
				userDirectory.exists());
		File targetDirectory = new File(userDirectory, "target");
		assertTrue(
				"Target directory '" + targetDirectory + "' does not exsit.",
				targetDirectory.exists());
		System.setProperty("eclipse.home.location",
				"file:" + targetDirectory.getPath());
	}

	@Test
	public void test() throws Exception {
		CassandraServer.start();
		assertTrue(CassandraServer.waitForStartup(15000));
		CassandraServer.stop();
	}

}
