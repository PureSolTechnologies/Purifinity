package com.puresoltechnologies.purifinity.server.passwordstore.test.utils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.passwordstore.core.impl.PasswordStoreBean;

public class PasswordStoreDatabaseHelper {

	public static void cleanPasswordStore(Cluster cluster) {
		try (Session session = cluster
				.connect(PasswordStoreBean.PASSWORD_STORE_KEYSPACE_NAME)) {
			session.execute("TRUNCATE " + PasswordStoreBean.PASSWORD_TABLE_NAME
					+ ";");
		}
	}

}
