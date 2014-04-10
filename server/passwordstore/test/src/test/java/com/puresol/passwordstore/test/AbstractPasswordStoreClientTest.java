package com.puresol.passwordstore.test;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.http.HttpEntity;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresol.passwordstore.core.impl.PasswordStoreBean;
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
	}

	@AfterClass
	public static void disconnectCassandra() {
		session.close();
		cluster.close();
	}

	@BeforeClass
	public static final void cleanupPasswordStoreDatabase()
			throws SQLException, IOException {
		session.execute("DELETE FROM " + PasswordStoreBean.PASSWORD_TABLE_NAME
				+ " WHERE user_id > 0");
	}

	@EnhanceDeployment
	public static void additionalResources(JavaArchive javaArchive) {
		javaArchive.addPackages(true, HttpEntity.class.getPackage());
	}
}
