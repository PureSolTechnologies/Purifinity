package com.puresoltechnologies.purifinity.server.database.cassandra.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.datastax.driver.core.Cluster;
import com.puresoltechnologies.purifinity.server.common.test.AbstractWeldTest;

public class ClusterProducerIT extends AbstractWeldTest {

	@Test
	public void test() {
		Cluster cluster = getInstance(Cluster.class);
		assertNotNull(cluster);
		assertNotNull(cluster.isClosed());
	}

	@Test
	public void testSingleton() {
		Cluster cluster1 = getInstance(Cluster.class);
		assertNotNull(cluster1);
		Cluster cluster2 = getInstance(Cluster.class);
		assertNotNull(cluster2);
		assertSame(cluster1, cluster2);
	}

}
