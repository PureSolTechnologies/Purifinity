package com.puresol.passwordstore.test;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.http.HttpEntity;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.BeforeClass;

import com.puresol.jboss.test.AbstractClientTest;
import com.puresol.jboss.test.arquillian.EnhanceDeployment;

public abstract class AbstractPasswordStoreClientTest extends
		AbstractClientTest {

	@BeforeClass
	public static final void cleanupPasswordStoreDatabase()
			throws SQLException, IOException {
		PasswordStoreDatabaseTestHelper.cleanup();
	}

	@EnhanceDeployment
	public static void additionalResources(JavaArchive javaArchive) {
		javaArchive.addPackages(true, HttpEntity.class.getPackage());
	}
}
