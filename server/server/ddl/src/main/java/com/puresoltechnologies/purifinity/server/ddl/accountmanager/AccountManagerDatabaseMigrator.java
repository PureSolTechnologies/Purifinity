package com.puresoltechnologies.purifinity.server.ddl.accountmanager;

import com.puresoltechnologies.purifinity.server.database.migration.AbstractDatabaseMigrator;
import com.puresoltechnologies.purifinity.server.database.migration.DatabaseMigrationConnector;

public class AccountManagerDatabaseMigrator extends AbstractDatabaseMigrator {

    protected AccountManagerDatabaseMigrator(
	    DatabaseMigrationConnector connector) {
	super(connector);
	AccountManagerSchema.createSequence(this);
    }

    public void drop() {
    }

}
