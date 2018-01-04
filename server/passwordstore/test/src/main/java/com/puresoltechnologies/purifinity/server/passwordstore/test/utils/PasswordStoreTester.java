package com.puresoltechnologies.purifinity.server.passwordstore.test.utils;

import static org.junit.Assert.assertNotNull;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.CassandraClusterHelper;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreBean;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.db.PasswordStoreKeyspace;

/**
 * This class contains several methods which support testing the PasswordStore
 * and which help other facility tests to handle the PasswordStore for testing.
 * 
 * @author Rick-Rainer Ludwig
 */
public class PasswordStoreTester {

    /**
     * This method clean the password store completely, except of the four
     * standard accounts:
     * <ol>
     * <li>user@puresol-technologies.com</li>
     * <li>engineer@puresol-technologies.com</li>
     * <li>administrator@puresol-technologies.com</li>
     * <li>ludwig@puresol-technologies.com</li>
     * </ol>
     */
    public static void cleanupDatabase() {
	try (Cluster cluster = CassandraClusterHelper.connect()) {
	    cleanupDatabase(cluster);
	}
    }

    /**
     * This method clean the password store completely, except of the four
     * standard accounts:
     * <ol>
     * <li>user@puresol-technologies.com</li>
     * <li>engineer@puresol-technologies.com</li>
     * <li>administrator@puresol-technologies.com</li>
     * <li>ludwig@puresol-technologies.com</li>
     * </ol>
     * 
     * @param cluster
     *            is the cluster where the keyspace
     *            {@link PasswordStoreKeyspace#NAME} can be found.
     */
    public static void cleanupDatabase(Cluster cluster) {
	try (Session session = connectKeyspace(cluster)) {
	    cleanupDatabase(session);
	}
    }

    /**
     * This method clean the password store completely, except of the four
     * standard accounts:
     * <ol>
     * <li>user@puresol-technologies.com</li>
     * <li>engineer@puresol-technologies.com</li>
     * <li>administrator@puresol-technologies.com</li>
     * <li>ludwig@puresol-technologies.com</li>
     * </ol>
     * 
     * @param session
     *            is the session opened to the keyspace
     *            {@link PasswordStoreKeyspace#NAME}.
     */
    public static final void cleanupDatabase(Session session) {
	ResultSet resultSet = session.execute("SELECT email FROM "
		+ PasswordStoreBean.PASSWORD_TABLE_NAME);
	PreparedStatement preparedStatement = session.prepare("DELETE FROM "
		+ PasswordStoreBean.PASSWORD_TABLE_NAME + " where email=?;");
	for (Row row : resultSet.all()) {
	    String email = row.getString(0);
	    if (isDefaultAccount(email)) {
		continue;
	    }
	    BoundStatement boundStatement = preparedStatement.bind(email);
	    session.execute(boundStatement);
	}
    }

    public static Session connectKeyspace(Cluster cluster) {
	Session session = cluster.connect(PasswordStoreKeyspace.NAME);
	assertNotNull("Session for '" + PasswordStoreKeyspace.NAME
		+ "' was not opened.", session);
	return session;
    }

    public static boolean isDefaultAccount(String email) {
	if (email == null) {
	    return false;
	}
	if (!email.endsWith("@puresol-technologies.com")) {
	    return false;
	}
	if (email.startsWith("user@") || email.startsWith("engineer@")
		|| email.startsWith("administrator@")
		|| email.startsWith("ludwig@")) {
	    return true;
	}
	return false;
    }
}
