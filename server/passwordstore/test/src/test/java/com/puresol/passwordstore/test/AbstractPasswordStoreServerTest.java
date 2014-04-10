package com.puresol.passwordstore.test;

import java.io.IOException;
import java.sql.SQLException;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.BeforeClass;

import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractPasswordStoreServerTest extends
		AbstractServerTest {

	@EnhanceDeployment
	public static final void enhanceDeployment(JavaArchive archive) {
		archive.addPackages(true,
				org.apache.commons.io.IOUtils.class.getPackage());
	}

	@BeforeClass
	public static final void cleanupPasswordStoreDatabase()
			throws SQLException, IOException {
		PasswordStoreDatabaseTestHelper.cleanup();
	}
}
