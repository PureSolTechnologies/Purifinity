package com.puresoltechnologies.purifinity.server.passwordstore.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;

import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreBean;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPasswordStoreServerTest extends AbstractServerTest {

    private Connection connection;

    @EnhanceDeployment
    public static final void enhanceDeployment(JavaArchive archive) {
	archive.addPackages(true, org.apache.commons.io.IOUtils.class.getPackage());
    }

    @Before
    public void connectCassandra() throws SQLException {
	connection = HBaseHelper.connect();
	assertNotNull("Cassandra cluster was not connected.", connection);
	PasswordStoreTester.cleanupDatabase(connection);
    }

    @After
    public void disconnectCassandra() throws SQLException {
	if (connection != null) {
	    connection.close();
	    connection = null;
	}
    }

    protected Connection getConnection() {
	return connection;
    }

    protected ResultSet readAccoutFromDatabase(EmailAddress email) throws SQLException {
	String accountQuery = "SELECT * FROM " + PasswordStoreBean.PASSWORD_TABLE_NAME + " WHERE email=?;";
	PreparedStatement preparedStatement = connection.prepareStatement(accountQuery);
	preparedStatement.setString(1, email.getAddress());
	return preparedStatement.executeQuery();
    }

}
