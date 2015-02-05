package com.puresoltechnologies.purifinity.server.passwordstore.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordCreationEntity;

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
	httpput.setHeader(HTTP.CONTENT_TYPE, "application/json");

	PasswordCreationEntity passwordCreationEntity = new PasswordCreationEntity(
		EMAIL_ADDRESS, VALID_PASSWORD);
	HttpEntity entity = new StringEntity(
		JSONSerializer.toJSONString(passwordCreationEntity));
	httpput.setEntity(entity);
	HttpResponse response = httpClient.execute(httpput);
	assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
	entity = response.getEntity();
	try (InputStream content = entity.getContent()) {
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    IOUtils.copy(content, byteArrayOutputStream);
	    assertEquals(64, byteArrayOutputStream.size());
	}
    }
}
