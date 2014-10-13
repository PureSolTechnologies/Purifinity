package com.puresoltechnologies.purifinity.server.ddl.accountmanager;

import com.puresoltechnologies.purifinity.server.database.migration.AbstractUniversalMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;

public class AccountManagerDatabaseMigrator extends AbstractUniversalMigrator {

    protected AccountManagerDatabaseMigrator(
	    UniversalMigratorConnector connector) {
	super(connector);
	AccountManagerSchema.createSequence(this);
    }

    public void drop() {
    }

}
