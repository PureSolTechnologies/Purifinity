package com.puresoltechnologies.purifinity.server.ddl.analysisservice;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractUniversalMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class AnalysisServiceDatabaseMigrator extends AbstractUniversalMigrator {

	public AnalysisServiceDatabaseMigrator(String host, int port)
			throws MigrationException {
		super(new CassandraMigratorConnector(host, port));
		AnalysisServiceSchema.createSequence(this);
	}

	public void drop() {
		CassandraMigratorConnector connector = (CassandraMigratorConnector) getConnector();
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + AnalysisStoreKeyspace.NAME);
		} finally {
			session.close();
		}
	}

}
