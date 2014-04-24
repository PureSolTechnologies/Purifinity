package com.puresoltechnologies.purifinity.server.analysisservice.test;

import static org.junit.Assert.assertNotNull;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;
import com.puresoltechnologies.purifinity.server.analysisservice.core.impl.AnalysisStoreServiceBean;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractAnalysisStoreServiceServerTest extends
		AbstractServerTest {

	private Cluster cluster;
	private Session session;

	@EnhanceDeployment
	public static final void enhanceDeployment(JavaArchive archive) {
		archive.addPackages(true,
				org.apache.commons.io.IOUtils.class.getPackage());
	}

	@Before
	public void connectCassandra() {
		cluster = Cluster.builder()
				.addContactPoint(AnalysisStoreServiceBean.CASSANDRA_HOST)
				.withPort(AnalysisStoreServiceBean.CASSANDRA_CQL_PORT).build();
		assertNotNull("Cassandra cluster was not connected.", cluster);
		session = cluster.connect(AnalysisStoreServiceBean.KEYSPACE_NAME);
		assertNotNull("Session for '" + AnalysisStoreServiceBean.KEYSPACE_NAME
				+ "' was not opened.", session);
		cleanupPasswordStoreDatabase();
	}

	@After
	public void disconnectCassandra() {
		if (session != null) {
			session.close();
			session = null;
		}
		if (cluster != null) {
			cluster.close();
			cluster = null;
		}
	}

	public final void cleanupPasswordStoreDatabase() {
		KeyspaceMetadata metadata = cluster.getMetadata().getKeyspace(
				AnalysisStoreServiceBean.KEYSPACE_NAME);
		for (TableMetadata tableMetadata : metadata.getTables()) {
			session.execute("TRUNCATE " + tableMetadata.getName() + ";");
		}
	}

}
