package com.puresoltechnologies.purifinity.server.accountmanager.test;

import java.io.IOException;
import java.sql.SQLException;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;

import com.google.protobuf.ServiceException;
import com.puresoltechnologies.purifinity.server.accountmanager.login.PasswordStoreLoginModule;
import com.puresoltechnologies.purifinity.server.passwordstore.test.utils.PasswordStoreTester;
import com.puresoltechnologies.purifinity.wildfly.test.AbstractServerTest;
import com.puresoltechnologies.purifinity.wildfly.test.arquillian.EnhanceDeployment;

public abstract class AbstractAccountManagerServerTest extends AbstractServerTest {

    @EnhanceDeployment
    public static void enhanceDeployment(JavaArchive archive) {
	archive.addPackage(PasswordStoreTester.class.getPackage());
	archive.addPackage(PasswordStoreLoginModule.class.getPackage());
    }

    @Before
    public void cleanup() throws SQLException, IOException, ServiceException {
	AccountManagerTester.cleanupDatabase();
    }

}
