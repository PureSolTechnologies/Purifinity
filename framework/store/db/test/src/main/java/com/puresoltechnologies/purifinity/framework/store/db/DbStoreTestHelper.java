package com.puresoltechnologies.purifinity.framework.store.db;

public class DbStoreTestHelper {

	public static void connectDBs() throws CassandraConnectionException,
			TitanConnectionException {
		CassandraConnection.connect();
		TitanConnection.connect();
	}

	public static void disconnectDBs() throws CassandraConnectionException,
			TitanConnectionException {
		TitanConnection.disconnect();
		CassandraConnection.disconnect();
	}

}
