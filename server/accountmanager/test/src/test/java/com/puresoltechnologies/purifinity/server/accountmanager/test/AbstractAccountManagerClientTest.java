package com.puresoltechnologies.purifinity.server.accountmanager.test;

import org.junit.Before;

import com.puresoltechnologies.purifinity.wildfly.test.AbstractClientTest;

public abstract class AbstractAccountManagerClientTest extends
	AbstractClientTest {

    @Before
    public void cleanup() {
	AccountManagerTester.cleanupDatabase();
    }
}
