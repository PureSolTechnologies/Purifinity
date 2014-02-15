package com.puresoltechnologies.purifinity.framework.store.db;

public class DbStoreTestHelper {

	public static void connectDBs() throws CassandraConnectionException {
		CassandraConnection.connect();
		TitanConnection.connect();
	}

	public static void disconnectDBs() throws CassandraConnectionException {
		TitanConnection.disconnect();
		CassandraConnection.disconnect();
	}

}
