package com.puresoltechnologies.purifinity.server.ddl.plugins;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.PluginsKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigrationConnector;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class PluginsDatabaseMigrator extends AbstractDatabaseMigrator {

	public PluginsDatabaseMigrator(String host, int port)
			throws MigrationException {
		super(new CassandraMigrationConnector(host, port));
		PluginsSchema.createSequence(this);
	}

	public void drop() {
		CassandraMigrationConnector connector = (CassandraMigrationConnector) getConnector();
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + PluginsKeyspace.NAME);
		} finally {
			session.close();
		}
	}

}
