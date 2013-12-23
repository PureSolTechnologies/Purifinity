package com.puresoltechnologies.purifinity.framework.store.db;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class AbstractDbStoreTest {

	@BeforeClass
	public static void connect() throws CassandraConnectionException,
			TitanConnectionException {
		DbStoreTestHelper.connectDBs();
	}

	@AfterClass
	public static void disconnect() throws CassandraConnectionException,
			TitanConnectionException {
		DbStoreTestHelper.disconnectDBs();
	}

}
