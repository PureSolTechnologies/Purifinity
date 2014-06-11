package com.puresoltechnologies.purifinity.server.ddl.processes;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.ProcessStatesKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigrationConnector;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class ProcessStatesDatabaseMigrator extends AbstractDatabaseMigrator {

	public ProcessStatesDatabaseMigrator(String host, int port)
			throws MigrationException {
		super(new CassandraMigrationConnector(host, port));
		ProcessStatesSchema.createSequence(this);
	}

	public void drop() {
		CassandraMigrationConnector connector = (CassandraMigrationConnector) getConnector();
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + ProcessStatesKeyspace.NAME);
		} finally {
			session.close();
		}
	}

}
