package com.puresoltechnologies.purifinity.server.ddl.preferences;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.database.cassandra.PreferencesStoreKeyspace;
import com.puresoltechnologies.purifinity.server.database.cassandra.utils.CassandraMigrationConnector;
import com.puresoltechnologies.purifinity.server.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class PreferencesStoreDatabaseMigrator extends AbstractDatabaseMigrator {

	public PreferencesStoreDatabaseMigrator(String host, int port)
			throws MigrationException {
		super(new CassandraMigrationConnector(host, port));
		PreferencesStoreSchema.createSequence(this);
	}

	public void drop() {
		CassandraMigrationConnector connector = (CassandraMigrationConnector) getConnector();
		Cluster cluster = connector.getCluster();
		Session session = cluster.connect();
		try {
			session.execute("DROP KEYSPACE " + PreferencesStoreKeyspace.NAME);
		} finally {
			session.close();
		}
	}

}
