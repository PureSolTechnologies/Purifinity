package com.puresoltechnologies.purifinity.server.ddl;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.genesis.commons.cassandra.CassandraUtils;
import com.puresoltechnologies.genesis.controller.GenesisController;

/**
 * This migrator starts all services of all services included in Purifinity
 * server.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DatabaseMigrator {

	private static boolean migrate = false;
	private static boolean drop = false;

	private static void showUsage() {
		System.out.println("Usage: " + DatabaseMigrator.class.getSimpleName()
				+ " (--drop | --migrate)");
	}

	private static void dropTitanKeyspace() {
		try (Cluster cluster = CassandraUtils.connectCluster()) {
			try (Session session = cluster.connect()) {
				session.execute("DROP KEYSPACE titan;");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			showUsage();
			return;
		}
		for (String arg : args) {
			if ("--drop".equals(arg)) {
				drop = true;
			} else if ("--migrate".equals(arg)) {
				migrate = true;
			}
		}
		try (GenesisController migrator = new GenesisController()) {
			if (drop) {
				migrator.dropAll();
				dropTitanKeyspace();
			}
			if (migrate) {
				migrator.transform();
			}
		}
	}
}
