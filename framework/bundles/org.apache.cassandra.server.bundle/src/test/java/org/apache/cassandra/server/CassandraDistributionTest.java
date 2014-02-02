package org.apache.cassandra.server;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

public class CassandraDistributionTest {

	@Test
	public void testResources() {
		URL resource = CassandraServer.class
				.getResource(CassandraDistribution.DISTRIBUTION_RESOURCE);
		assertNotNull(resource);
	}

	@Test
	public void testUnpackDistribution() throws IOException {
		File targetDirectory = new File("target/extractTest");
		CassandraDistribution.extract(targetDirectory);
	}

}
