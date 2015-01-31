package com.puresoltechnologies.purifinity.server.test.security;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;

import com.puresoltechnologies.purifinity.server.test.AbstractPurifinityServerClientTest;

public class AuthenticationSecurityIT extends
	AbstractPurifinityServerClientTest {

    @Test
    public void testAuthentication() throws IOException {
	CloseableHttpClient client = createHttpClient();
	try {
	    HttpGet get = new HttpGet();
	    CloseableHttpResponse response = client.execute(getHttpHost(), get);
	    try (InputStream content = response.getEntity().getContent()) {
		IOUtils.copy(content, System.out);
	    }
	} finally {
	    client.close();
	}
    }
}
