package com.puresoltechnologies.purifinity.server.ddl.evaluationservice;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.migration.CassandraMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractUniversalMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class EvaluationServiceDatabaseMigrator extends AbstractUniversalMigrator {

	public EvaluationServiceDatabaseMigrator(String host, int port)
			throws MigrationException {
		super(new CassandraMigratorConnector(host, port));
		EvaluationServiceSchema.createSequence(this);
	}

	public void drop() {
		CassandraMigratorConnector connector = (CassandraMigratorConnector) getConnector();
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + EvaluationStoreKeyspace.NAME);
		} finally {
			session.close();
		}
	}

}
