package com.puresoltechnologies.purifinity.server.accountmanager.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;

import com.puresoltechnologies.purifinity.webui.test.AbstractClientTest;

public abstract class AbstractAccountManagerClientTest extends
	AbstractClientTest {

    @Before
    public void cleanup() {
	AccountManagerTester.cleanupDatabase();
    }

    protected URI getAccountManagerRestURI() {
	try {
	    return new URI("http://" + getHost() + ":" + getPort()
		    + "/accountmanager/rest");
	} catch (URISyntaxException e) {
	    throw new RuntimeException(
		    "Test implementation issue for building AccountManager REST URI.",
		    e);
	}
    }
}
