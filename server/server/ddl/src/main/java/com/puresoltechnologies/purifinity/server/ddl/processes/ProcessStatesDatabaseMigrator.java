package com.puresoltechnologies.purifinity.server.ddl.processes;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.ProcessStatesKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractUniversalMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class ProcessStatesDatabaseMigrator extends AbstractUniversalMigrator {

	public ProcessStatesDatabaseMigrator(String host, int port)
			throws MigrationException {
		super(new CassandraMigratorConnector(host, port));
		ProcessStatesSchema.createSequence(this);
	}

	public void drop() {
		CassandraMigratorConnector connector = (CassandraMigratorConnector) getConnector();
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + ProcessStatesKeyspace.NAME);
		} finally {
			session.close();
		}
	}

}
