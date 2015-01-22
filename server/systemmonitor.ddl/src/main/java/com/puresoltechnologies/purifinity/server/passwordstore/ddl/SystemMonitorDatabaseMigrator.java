package com.puresoltechnologies.purifinity.server.passwordstore.ddl;

import com.puresoltechnologies.genesis.controller.GenesisController;

/**
 * This migrator starts all services of all services included in Purifinity
 * server.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SystemMonitorDatabaseMigrator {

	private static boolean migrate = false;
	private static boolean drop = false;

	private static void showUsage() {
		System.out.println("Usage: " + SystemMonitorDatabaseMigrator.class.getSimpleName()
				+ " (--drop | --migrate)");
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
			}
			if (migrate) {
				migrator.transform();
			}
		}
	}
}
