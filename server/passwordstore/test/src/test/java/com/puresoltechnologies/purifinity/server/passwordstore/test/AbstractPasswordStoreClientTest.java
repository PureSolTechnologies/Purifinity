package com.puresoltechnologies.purifinity.server.passwordstore.test;

import org.apache.http.HttpEntity;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreBean;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractClientTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPasswordStoreClientTest extends
		AbstractClientTest {

	private static Cluster cluster;
	private static Session session;

	@BeforeClass
	public static void connectCassandra() {
		cluster = Cluster.builder()
				.addContactPoint(PasswordStoreBean.CASSANDRA_HOST)
				.withPort(PasswordStoreBean.CASSANDRA_CQL_PORT).build();
		session = cluster
				.connect(PasswordStoreBean.PASSWORD_STORE_KEYSPACE_NAME);
		cleanupPasswordStoreDatabase();
	}

	@AfterClass
	public static void disconnectCassandra() {
		session.close();
		cluster.close();
	}

	public static final void cleanupPasswordStoreDatabase() {
		session.execute("TRUNCATE " + PasswordStoreBean.PASSWORD_TABLE_NAME
				+ ";");
	}

	@EnhanceDeployment
	public static void additionalResources(JavaArchive javaArchive) {
		javaArchive.addPackages(true, HttpEntity.class.getPackage());
	}
}
