package com.puresoltechnologies.purifinity.server.passwordstore.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordStoreRestIT extends AbstractPasswordStoreClientTest {

	private static final String EMAIL_ADDRESS = "ludwig@puresol-technologies.com";
	private static final String INVALID_EMAIL_ADDRESS = "@puresol-technologies.com";
	private static final String VALID_PASSWORD = "IAmAPassword!:-)3";
	private static final String TOO_WEAK_PASSWORD = "123456";

	private CloseableHttpClient httpClient;

	@Before
	public void setup() {
		httpClient = HttpClientBuilder.create().build();
	}

	@After
	public void teardown() throws IOException {
		httpClient.close();
	}

	@Test
	public void testCreateAccount() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPut httpput = new HttpPut(
				"http://localhost:8080/passwordstore/rest/createAccount");
		HttpEntity entity = new StringEntity(EMAIL_ADDRESS + "\n"
				+ VALID_PASSWORD);
		httpput.setEntity(entity);
		HttpResponse response = httpClient.execute(httpput);
		assertEquals(HttpStatus.SC_CREATED, response.getStatusLine()
				.getStatusCode());
		entity = response.getEntity();
		Header[] activationKeys = response.getHeaders("activation-key");
		assertEquals(1, activationKeys.length);
		assertEquals(64, activationKeys[0].getValue().length());
	}
}
