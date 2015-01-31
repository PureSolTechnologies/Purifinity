package com.puresoltechnologies.purifinity.server.accountmanager.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreBean;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractClientTest;

public abstract class AbstractAccountManagerClientTest extends
	AbstractClientTest {

    private static Cluster cluster = null;
    private static Session session = null;

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
	session.execute("TRUNCATE " + PasswordStoreBean.PASSWORD_TABLE_NAME
		+ ";");
    }

}
