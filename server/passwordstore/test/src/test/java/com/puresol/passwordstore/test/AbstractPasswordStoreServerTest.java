package com.puresol.passwordstore.test;

import static org.junit.Assert.assertNotNull;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresol.passwordstore.core.impl.PasswordStoreBean;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPasswordStoreServerTest extends
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

	protected Row readAccoutFromDatabase(String email) {
		Cluster cluster = Cluster.builder()
				.addContactPoint(PasswordStoreBean.CASSANDRA_HOST)
				.withPort(PasswordStoreBean.CASSANDRA_CQL_PORT).build();
		Session session = cluster.connect();
		String accountQuery = "SELECT * FROM "
				+ PasswordStoreBean.PASSWORD_STORE_KEYSPACE_NAME + "."
				+ PasswordStoreBean.PASSWORD_TABLE_NAME + " WHERE email=?;";
		PreparedStatement preparedStatement = session.prepare(accountQuery);
		BoundStatement account = preparedStatement.bind(email);
		return session.execute(account).one();
	}

}
