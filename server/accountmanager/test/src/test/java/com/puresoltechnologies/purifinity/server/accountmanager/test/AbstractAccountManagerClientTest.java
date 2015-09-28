package com.puresoltechnologies.purifinity.server.accountmanager.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.junit.Before;

import com.puresoltechnologies.purifinity.wildfly.test.AbstractClientTest;

public abstract class AbstractAccountManagerClientTest extends AbstractClientTest {

    @Before
    public void cleanup() throws SQLException {
	AccountManagerTester.cleanupDatabase();
    }

    protected URI getAccountManagerRestURI() {
	try {
	    return new URI("http://" + getHost() + ":" + getPort() + "/accountmanager/rest");
	} catch (URISyntaxException e) {
	    throw new RuntimeException("Test implementation issue for building AccountManager REST URI.", e);
	}
    }
}
