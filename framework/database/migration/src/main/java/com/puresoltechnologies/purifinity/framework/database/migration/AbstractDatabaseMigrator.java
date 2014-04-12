package com.puresoltechnologies.purifinity.framework.database.migration;

import java.io.IOException;

/**
 * This is the abstract base class of a database migrator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractDatabaseMigrator extends MigrationSequence {

	private final DatabaseMigrationConnector connector;

	protected AbstractDatabaseMigrator(DatabaseMigrationConnector connector) {
		super();
		this.connector = connector;
	}

	protected DatabaseMigrationConnector getConnector() {
		return connector;
	}

	@Override
	public void migrate() throws IOException, MigrationException {
		getConnector().initialize();
		try {
			getConnector().startMigration();
			try {
				super.migrate();
			} finally {
				getConnector().finishMigration();
			}
		} finally {
			getConnector().close();
		}
	}

}
