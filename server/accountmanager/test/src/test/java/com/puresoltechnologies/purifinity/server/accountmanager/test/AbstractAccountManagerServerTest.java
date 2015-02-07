package com.puresoltechnologies.purifinity.server.accountmanager.test;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;

import com.puresoltechnologies.purifinity.server.accountmanager.login.PasswordStoreLoginModule;
import com.puresoltechnologies.purifinity.server.passwordstore.client.PasswordStoreClient;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordStoreRestInterface;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractAccountManagerServerTest extends
	AbstractServerTest {

    @EnhanceDeployment
    public static void addDeployments(JavaArchive javaArchive) {
	javaArchive.addPackages(true, PasswordStoreClient.class.getPackage());
	javaArchive.addPackages(true,
		PasswordCreationException.class.getPackage());
	javaArchive.addPackages(true, "org.jboss.resteasy");
	javaArchive.addPackages(true,
		PasswordStoreRestInterface.class.getPackage());
	javaArchive.addPackages(true,
		PasswordStoreLoginModule.class.getPackage());
    }

    @Before
    public void cleanup() {
	AccountManagerTester.cleanupDatabase();
    }

}
