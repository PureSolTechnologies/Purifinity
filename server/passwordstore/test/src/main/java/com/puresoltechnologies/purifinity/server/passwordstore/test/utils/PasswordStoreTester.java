package com.puresoltechnologies.purifinity.server.passwordstore.test.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;
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
     * 
     * @throws SQLException
     */
    public static void cleanupDatabase() throws SQLException {
	try (Connection connection = HBaseHelper.connect()) {
	    cleanupDatabase(connection);
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
     * @param connection
     *            is the session opened to the keyspace
     *            {@link PasswordStoreKeyspace#NAME}.
     * @throws SQLException
     */
    public static final void cleanupDatabase(Connection connection) throws SQLException {
	try (Statement selectEmailStatement = connection.createStatement();
		PreparedStatement deletePasswordPreparedStatement = connection
			.prepareStatement("DELETE FROM " + PasswordStoreBean.PASSWORD_TABLE_NAME + " where email=?;");
		ResultSet resultSet = selectEmailStatement
			.executeQuery("SELECT email FROM " + PasswordStoreBean.PASSWORD_TABLE_NAME)) {
	    while (resultSet.next()) {
		String email = resultSet.getString(1);
		if (isDefaultAccount(email)) {
		    continue;
		}
		deletePasswordPreparedStatement.setString(1, email);
		deletePasswordPreparedStatement.execute();
		connection.commit();
	    }
	}
    }

    public static boolean isDefaultAccount(String email) {
	if (email == null) {
	    return false;
	}
	if (!email.endsWith("@puresol-technologies.com")) {
	    return false;
	}
	if (email.startsWith("user@") || email.startsWith("engineer@") || email.startsWith("administrator@")
		|| email.startsWith("ludwig@")) {
	    return true;
	}
	return false;
    }
}
