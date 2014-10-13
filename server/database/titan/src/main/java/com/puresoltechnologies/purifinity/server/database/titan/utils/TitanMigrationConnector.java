package com.puresoltechnologies.purifinity.server.database.titan.utils;

import java.io.IOException;

import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;
import com.puresoltechnologies.purifinity.server.database.migration.spi.UniversalMigratorConnector;
import com.puresoltechnologies.purifinity.server.database.titan.TitanGraphHelper;
import com.thinkaurelius.titan.core.TitanGraph;

public class TitanMigrationConnector implements UniversalMigratorConnector {

    private TitanGraph titanGraph;

    @Override
    public void initialize() throws IOException, MigrationException {
	titanGraph = TitanGraphHelper.connect();

    }

    @Override
    public void startMigration() throws MigrationException {
	// TODO Auto-generated method stub

    }

    @Override
    public void finishMigration() throws MigrationException {
	// TODO Auto-generated method stub

    }

    @Override
    public void close() throws IOException {
	titanGraph = null;
    }

}
