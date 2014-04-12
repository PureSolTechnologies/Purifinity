package com.puresol.accountmanager.test;

import org.jboss.shrinkwrap.api.spec.JavaArchive;

import com.puresol.accountmanager.login.PasswordStoreLoginModule;
import com.puresol.passwordstore.client.PasswordStoreClient;
import com.puresol.passwordstore.domain.AccountCreationException;
import com.puresol.passwordstore.rest.PasswordStoreRestInterface;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractAccountManagerTest extends AbstractServerTest {

	@EnhanceDeployment
	public static void addDeployments(JavaArchive javaArchive) {
		javaArchive.addPackages(true, PasswordStoreClient.class.getPackage());
		javaArchive.addPackages(true,
				AccountCreationException.class.getPackage());
		javaArchive.addPackages(true, "org.jboss.resteasy");
		javaArchive.addPackages(true,
				PasswordStoreRestInterface.class.getPackage());
		javaArchive.addPackages(true,
				PasswordStoreLoginModule.class.getPackage());
	}

}
