package com.puresoltechnologies.purifinity.server.database.hbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class provides convenient helpers for HBase and Phoenix connections.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class HBaseHelper {

    public static Connection connect() throws SQLException {
	try {
	    Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
	} catch (ClassNotFoundException e) {
	    throw new RuntimeException("Could not find Phoenix driver class.", e);
	}
	return DriverManager.getConnection("jdbc:phoenix:localhost");
    }

}
