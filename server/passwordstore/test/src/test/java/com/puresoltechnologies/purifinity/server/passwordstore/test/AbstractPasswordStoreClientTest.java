package com.puresoltechnologies.purifinity.server.passwordstore.test;

import org.apache.http.HttpEntity;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.CassandraClusterHelper;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;
import com.puresoltechnologies.purifinity.webui.test.AbstractClientTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPasswordStoreClientTest extends
	AbstractClientTest {

    private static Cluster cluster;
    private static Session session;

    @BeforeClass
    public static void connectCassandra() {
	cluster = CassandraClusterHelper.connect();
	session = PasswordStoreTester.connectKeyspace(cluster);
	PasswordStoreTester.cleanupDatabase(session);
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

    @EnhanceDeployment
    public static void additionalResources(JavaArchive javaArchive) {
	javaArchive.addPackages(true, HttpEntity.class.getPackage());
    }
}
