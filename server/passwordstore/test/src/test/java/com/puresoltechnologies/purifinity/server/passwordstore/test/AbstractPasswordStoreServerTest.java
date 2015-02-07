package com.puresoltechnologies.purifinity.server.passwordstore.test;

import static org.junit.Assert.assertNotNull;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.purifinity.server.database.cassandra.CassandraClusterHelper;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreBean;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;
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
	cluster = CassandraClusterHelper.connect();
	assertNotNull("Cassandra cluster was not connected.", cluster);
	session = PasswordStoreTester.connectKeyspace(cluster);
	PasswordStoreTester.cleanupDatabase(session);
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

    protected Cluster getCluster() {
	return cluster;
    }

    protected Row readAccoutFromDatabase(EmailAddress email) {
	String accountQuery = "SELECT * FROM "
		+ PasswordStoreBean.PASSWORD_TABLE_NAME + " WHERE email=?;";
	PreparedStatement preparedStatement = session.prepare(accountQuery);
	BoundStatement account = preparedStatement.bind(email.getAddress());
	return session.execute(account).one();
    }

}
