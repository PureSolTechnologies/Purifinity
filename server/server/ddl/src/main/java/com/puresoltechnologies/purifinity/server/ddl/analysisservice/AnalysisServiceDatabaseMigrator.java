package com.puresoltechnologies.purifinity.server.ddl.analysisservice;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.AnalysisStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigrationConnector;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class AnalysisServiceDatabaseMigrator extends AbstractDatabaseMigrator {

	public AnalysisServiceDatabaseMigrator(String host, int port)
			throws MigrationException {
		super(new CassandraMigrationConnector(host, port));
		AnalysisServiceSchema.createSequence(this);
	}

	public void drop() {
		CassandraMigrationConnector connector = (CassandraMigrationConnector) getConnector();
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + AnalysisStoreKeyspace.NAME);
		} finally {
			session.close();
		}
	}

}
