package com.puresoltechnologies.purifinity.server.analysisservice.test;

import org.apache.http.HttpEntity;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;
import com.puresoltechnologies.purifinity.server.analysisservice.core.impl.store.AnalysisStoreServiceBean;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractClientTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractAnalysisStoreServiceClientTest extends
		AbstractClientTest {

	private static Cluster cluster;
	private static Session session;

	@BeforeClass
	public static void connectCassandra() {
		cluster = Cluster.builder()
				.addContactPoint(AnalysisStoreServiceBean.CASSANDRA_HOST)
				.withPort(AnalysisStoreServiceBean.CASSANDRA_CQL_PORT).build();
		session = cluster.connect(AnalysisStoreServiceBean.KEYSPACE_NAME);
		cleanupAnalysisStoreDatabase();
	}

	@AfterClass
	public static void disconnectCassandra() {
		session.close();
		cluster.close();
	}

	public static final void cleanupAnalysisStoreDatabase() {
		KeyspaceMetadata metadata = cluster.getMetadata().getKeyspace(
				AnalysisStoreServiceBean.KEYSPACE_NAME);
		for (TableMetadata tableMetadata : metadata.getTables()) {
			session.execute("TRUNCATE " + tableMetadata.getName() + ";");
		}
	}

	@EnhanceDeployment
	public static void additionalResources(JavaArchive javaArchive) {
		javaArchive.addPackages(true, HttpEntity.class.getPackage());
	}
}
