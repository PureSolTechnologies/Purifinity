package com.puresoltechnologies.purifinity.framework.store.db;

import com.datastax.driver.core.Cluster;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraMigration;
import com.puresoltechnologies.purifinity.framework.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.framework.store.db.internal.CassandraSchemaV100;

public class CassandraSchema {

	public static void migrate(Cluster cluster) throws MigrationException {
		CassandraMigration.initialize(cluster);

		CassandraSchemaV100.migrate(cluster);
	}
}
