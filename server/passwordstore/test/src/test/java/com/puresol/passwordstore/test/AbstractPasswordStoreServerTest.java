package com.puresol.passwordstore.test;

import java.io.IOException;
import java.sql.SQLException;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.BeforeClass;

import com.puresol.jboss.test.AbstractServerTest;
import com.puresol.jboss.test.arquillian.EnhanceDeployment;

public abstract class AbstractPasswordStoreServerTest extends
		AbstractServerTest {

	@EnhanceDeployment
	public static final void enhanceDeployment(JavaArchive archive) {
		archive.addPackages(true, org.postgresql.Driver.class.getPackage());
		archive.addPackages(true,
				com.puresol.database.postgresql.utils.PostgreSQLHelper.class
						.getPackage());
		archive.addPackages(true,
				org.apache.commons.io.IOUtils.class.getPackage());

		archive.addAsResource(AbstractPasswordStoreServerTest.class
				.getResource("/sql/PasswordStoreCleanup.sql"),
				"/sql/PasswordStoreCleanup.sql");
	}

	@BeforeClass
	public static final void cleanupPasswordStoreDatabase()
			throws SQLException, IOException {
		PasswordStoreDatabaseTestHelper.cleanup();
	}
}
