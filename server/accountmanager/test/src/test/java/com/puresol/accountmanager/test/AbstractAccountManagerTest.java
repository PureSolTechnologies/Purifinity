package com.puresol.accountmanager.test;

import static org.junit.Assert.assertNotNull;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresol.accountmanager.login.PasswordStoreLoginModule;
import com.puresol.passwordstore.client.PasswordStoreClient;
import com.puresol.passwordstore.core.impl.PasswordStoreBean;
import com.puresol.passwordstore.domain.AccountCreationException;
import com.puresol.passwordstore.rest.PasswordStoreRestInterface;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractAccountManagerTest extends AbstractServerTest {

	private Cluster cluster;
	private Session session;

	@EnhanceDeployment
	public static void addDeployments(JavaArchive javaArchive) {
		javaArchive.addPackages(true, PasswordStoreClient.class.getPackage());
		javaArchive.addPackages(true,
				AccountCreationException.class.getPackage());
		javaArchive.addPackages(true, "org.jboss.resteasy");
		javaArchive.addPackages(true,
				PasswordStoreRestInterface.class.getPackage());
		javaArchive.addPackages(true,
				PasswordStoreLoginModule.class.getPackage());
	}

	@Before
	public void connectCassandra() {
		cluster = Cluster.builder()
				.addContactPoint(PasswordStoreBean.CASSANDRA_HOST)
				.withPort(PasswordStoreBean.CASSANDRA_CQL_PORT).build();
		assertNotNull("Cassandra cluster was not connected.", cluster);
		session = cluster
				.connect(PasswordStoreBean.PASSWORD_STORE_KEYSPACE_NAME);
		assertNotNull("Session for '"
				+ PasswordStoreBean.PASSWORD_STORE_KEYSPACE_NAME
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
		session.execute("TRUNCATE " + PasswordStoreBean.PASSWORD_TABLE_NAME
				+ ";");
		assertNotNull(
				"Session for '"
						+ PasswordStoreBean.PASSWORD_STORE_KEYSPACE_NAME
						+ "' is null.", session);
	}
}
