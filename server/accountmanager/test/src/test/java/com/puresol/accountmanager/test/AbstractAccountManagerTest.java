package com.puresol.accountmanager.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.i18n4java.Translator;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.BeforeClass;

import com.puresol.accountmanager.login.PasswordStoreLoginModule;
import com.puresol.commons.utils.ExtendedException;
import com.puresol.jboss.test.AbstractServerTest;
import com.puresol.jboss.test.arquillian.EnhanceDeployment;
import com.puresol.passwordstore.client.PasswordStoreClient;
import com.puresol.passwordstore.domain.AccountCreationException;
import com.puresol.passwordstore.rest.PasswordStoreRestInterface;
import com.puresol.passwordstore.test.PasswordStoreDatabaseTestHelper;

public abstract class AbstractAccountManagerTest extends AbstractServerTest {

	@EnhanceDeployment
	public static void addDeployments(JavaArchive javaArchive) {
		javaArchive.addPackages(true, PasswordStoreClient.class.getPackage());
		javaArchive.addPackages(true, ExtendedException.class.getPackage());
		javaArchive.addPackages(true,
				AccountCreationException.class.getPackage());
		javaArchive.addPackages(true, "org.jboss.resteasy");
		javaArchive.addPackages(true,
				PasswordStoreRestInterface.class.getPackage());
		javaArchive.addPackages(true, Translator.class.getPackage());
		javaArchive.addPackages(true,
				PasswordStoreLoginModule.class.getPackage());
	}

	@BeforeClass
	public static void clearPasswordStoreDatabase() throws SQLException,
			IOException {
		PasswordStoreDatabaseTestHelper.cleanup();
		AccountManagerDatabaseTestHelper.cleanup();
	}

}
