package com.puresoltechnologies.purifinity.server.database.cassandra.utils;

import com.datastax.driver.core.Cluster;
import com.puresoltechnologies.purifinity.server.database.migration.DatabaseMigrationConnector;
import com.puresoltechnologies.purifinity.server.database.migration.MigrationException;

public class CassandraMigrationConnector implements DatabaseMigrationConnector {

    private final boolean closeCluster;
    private final Cluster cluster;

    public CassandraMigrationConnector(String host, int port) {
	super();
	cluster = Cluster.builder().addContactPoint(host).withPort(port)
		.build();
	closeCluster = true;
    }

    public CassandraMigrationConnector(Cluster cluster) {
	super();
	this.cluster = cluster;
	closeCluster = false;
    }

    @Override
    public void initialize() throws MigrationException {
	CassandraMigration.initialize(cluster);
    }

    @Override
    public void startMigration() {
	// TODO Auto-generated method stub
    }

    @Override
    public void finishMigration() {
	// TODO Auto-generated method stub
    }

    @Override
    public void close() {
	if (closeCluster) {
	    cluster.close();
	}
    }

    public Cluster getCluster() {
	return cluster;
    }

}
