package com.puresoltechnologies.purifinity.framework.database.cassandra.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.CassandraUtils;
import com.puresoltechnologies.purifinity.framework.database.cassandra.utils.ReplicationStrategy;

public class BasicCassandraIT {

	@BeforeClass
	public static void connectCassandra() {
		CassandraTestHelper.connect();
	}

	@AfterClass
	public static void disconnectCassandra() {
		CassandraTestHelper.disconnect();
	}

	@Test
	public void test() {
		Session session = CassandraTestHelper.getSession();
		CassandraUtils.createKeyspace(session, "simplex",
				ReplicationStrategy.SIMPLE_STRATEGY, 3);
		CassandraUtils.dropKeyspace(session, "simplex;");
	}
}
