package com.puresoltechnologies.purifinity.server.systemmonitor.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.puresoltechnologies.purifinity.server.database.hbase.HBaseHelper;
import com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.events.EventLoggerBean;
import com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.metrics.MetricLoggerBean;

/**
 * This class contains functionality for SystemMonitor testing and for helping
 * other facility tests to use SystemMonitor.
 * 
 * @author Rick-Rainer Ludwig
 */
public class SystemMonitorTester {

    public static void cleanupDatabase() throws SQLException {
	try (Connection connection = HBaseHelper.connect()) {
	    cleanupDatabase(connection);
	}
    }

    public static void cleanupDatabase(Connection connection) throws SQLException {
	try (Statement statement = connection.createStatement()) {
	    statement.execute("DELETE FROM " + EventLoggerBean.EVENTS_TABLE_NAME);
	    statement.execute("DELETE FROM " + MetricLoggerBean.METRICS_TABLE_NAME);
	}
    }
}
