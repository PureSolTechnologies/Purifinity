package com.puresoltechnologies.purifinity.server.passwordstore.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.http.HttpEntity;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractClientTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPasswordStoreClientTest extends AbstractClientTest {

    private static Connection connection;

    @BeforeClass
    public static void connectHBase() throws SQLException {
	connection = HBaseHelper.connect();
	PasswordStoreTester.cleanupDatabase(connection);
    }

    @AfterClass
    public static void disconnectHBase() throws SQLException {
	if (connection != null) {
	    connection.close();
	    connection = null;
	}
    }

    @EnhanceDeployment
    public static void additionalResources(JavaArchive javaArchive) {
	javaArchive.addPackages(true, HttpEntity.class.getPackage());
    }
}
