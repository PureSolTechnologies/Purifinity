package com.puresoltechnologies.purifinity.server.test.database.cassandra;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import javax.inject.Inject;

import org.junit.Test;

import com.datastax.driver.core.Cluster;
import com.puresoltechnologies.purifinity.server.test.AbstractPurifinityServerServerTest;
import com.puresoltechnologies.purifinity.server.wildfly.utils.BeanUtilities;

public class ClusterProducerIT extends AbstractPurifinityServerServerTest {

	@Inject
	private Cluster cluster;

	@Test
	public void test() {
		assertNotNull(cluster);
		assertNotNull(cluster.isClosed());
	}

	@Test
	public void testSingleton() {
		assertNotNull(cluster);
		Cluster cluster2 = BeanUtilities.getBean(Cluster.class);
		assertNotNull(cluster2);
		assertSame(cluster, cluster2);
	}

}
