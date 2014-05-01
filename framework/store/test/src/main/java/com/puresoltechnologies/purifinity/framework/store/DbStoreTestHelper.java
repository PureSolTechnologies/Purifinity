package com.puresoltechnologies.purifinity.framework.store;

import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnectionException;
import com.puresoltechnologies.purifinity.framework.store.db.TitanConnection;

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
