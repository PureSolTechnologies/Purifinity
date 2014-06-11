package com.puresoltechnologies.purifinity.server.ddl.evaluationservice;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.EvaluationStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigrationConnector;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class EvaluationServiceDatabaseMigrator extends AbstractDatabaseMigrator {

	public EvaluationServiceDatabaseMigrator(String host, int port)
			throws MigrationException {
		super(new CassandraMigrationConnector(host, port));
		EvaluationServiceSchema.createSequence(this);
	}

	public void drop() {
		CassandraMigrationConnector connector = (CassandraMigrationConnector) getConnector();
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + EvaluationStoreKeyspace.NAME);
		} finally {
			session.close();
		}
	}

}
