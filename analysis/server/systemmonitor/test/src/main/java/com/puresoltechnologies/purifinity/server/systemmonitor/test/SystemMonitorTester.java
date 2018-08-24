package com.puresoltechnologies.purifinity.server.systemmonitor.test;

import static org.junit.Assert.assertNotNull;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.CassandraClusterHelper;
import com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.SystemMonitorKeyspace;
import com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.events.EventLoggerBean;
import com.puresoltechnologies.purifinity.server.systemmonitor.core.impl.metrics.MetricLoggerBean;

/**
 * This class contains functionality for SystemMonitor testing and for helping
 * other facility tests to use SystemMonitor.
 * 
 * @author Rick-Rainer Ludwig
 */
public class SystemMonitorTester {

    public static void cleanupDatabase() {
	try (Cluster cluster = CassandraClusterHelper.connect()) {
	    cleanupDatabase(cluster);
	}
    }

    public static void cleanupDatabase(Cluster cluster) {
	try (Session session = connectKeyspace(cluster)) {
	    session.execute("TRUNCATE " + EventLoggerBean.EVENTS_TABLE_NAME);
	    session.execute("TRUNCATE " + MetricLoggerBean.METRICS_TABLE_NAME);
	}
    }

    public static Session connectKeyspace(Cluster cluster) {
	Session session = cluster.connect(SystemMonitorKeyspace.NAME);
	assertNotNull("Session for '" + SystemMonitorKeyspace.NAME
		+ "' was not opened.", session);
	return session;
    }
}
