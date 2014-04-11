package com.puresol.passwordstore.test;

import java.io.IOException;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresol.passwordstore.core.impl.PasswordStoreBean;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPasswordStoreServerTest extends
		AbstractServerTest {

	private static Cluster cluster;
	private static Session session;

	@EnhanceDeployment
	public static final void enhanceDeployment(JavaArchive archive) {
		archive.addPackages(true,
				org.apache.commons.io.IOUtils.class.getPackage());
	}

	@BeforeClass
	public static void connectCassandra()  {
		cluster = Cluster.builder()
				.addContactPoint(PasswordStoreBean.CASSANDRA_HOST)
				.withPort(PasswordStoreBean.CASSANDRA_CQL_PORT).build();
		session = cluster
				.connect(PasswordStoreBean.PASSWORD_STORE_KEYSPACE_NAME);
		cleanupPasswordStoreDatabase();
	}

	@AfterClass
	public static void disconnectCassandra() {
		if (session != null) {
			session.close();
			session = null;
		}
		if (cluster != null) {
			cluster.close();
			cluster = null;
		}
	}

	public static final void cleanupPasswordStoreDatabase() {
		session.execute("DELETE FROM " + PasswordStoreBean.PASSWORD_TABLE_NAME
				+ " WHERE user_id > 0");
	}

}
