package com.puresoltechnologies.purifinity.server.accountmanager.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.script.ScriptException;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.purifinity.server.accountmanager.rest.api.CreateAccountEntity;
import com.puresoltechnologies.purifinity.server.accountmanager.test.AbstractAccountManagerClientTest;

/**
 * This test checks the REST interface base on plain HTTP and JSON to assure
 * JavaScript connectivity.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AccountManagerRestIT extends AbstractAccountManagerClientTest {

    private CloseableHttpClient httpClient = null;

    @Before
    public void setupClient() {
	assertNull(httpClient);
	httpClient = createHttpClient();
    }

    @After
    public void tearDownClient() throws IOException {
	assertNotNull(httpClient);
	httpClient.close();
	httpClient = null;
    }

    @Test
    public void testGetRoles() throws URISyntaxException,
	    IllegalStateException, IOException, ScriptException {
	HttpGet getRequest = new HttpGet(new URI(getAccountManagerRestURI()
		.toString() + "/roles"));
	try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
	    assertEquals(HttpStatus.SC_OK, response.getStatusLine()
		    .getStatusCode());
	    HttpEntity entity = response.getEntity();
	    try (InputStream content = entity.getContent()) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		IOUtils.copy(content, byteArray);
		String rolesString = byteArray.toString();
		System.out.println(rolesString);
		assertContainsRole(rolesString, "administrator",
			"Administrator");
		assertContainsRole(rolesString, "unprivileged",
			"Unprivileged User");
		assertContainsRole(rolesString, "engineer", "Engineer");
	    }
	}
    }

    @Test
    public void testGetUsers() throws URISyntaxException,
	    IllegalStateException, IOException, ScriptException {
	HttpGet getRequest = new HttpGet(new URI(getAccountManagerRestURI()
		.toString() + "/users"));
	try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
	    assertEquals(HttpStatus.SC_OK, response.getStatusLine()
		    .getStatusCode());
	    HttpEntity entity = response.getEntity();
	    try (InputStream content = entity.getContent()) {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		IOUtils.copy(content, byteArray);
		String usersString = byteArray.toString();
		System.out.println(usersString);
		assertContainsRole(usersString, "administrator",
			"Administrator");
		assertContainsRole(usersString, "unprivileged",
			"Unprivileged User");
		assertContainsRole(usersString, "engineer", "Engineer");

		assertContainsUser(usersString,
			"administrator@puresol-technologies.com",
			"Administrator", "administrator", "Administrator");
		assertContainsUser(usersString,
			"ludwig@puresol-technologies.com",
			"Rick-Rainer Ludwig", "administrator", "Administrator");
		assertContainsUser(usersString,
			"engineer@puresol-technologies.com", "Engineer",
			"engineer", "Engineer");
		assertContainsUser(usersString,
			"user@puresol-technologies.com", "Unprivileged User",
			"unprivileged", "Unprivileged User");
	    }
	}
    }

    private void assertContainsRole(String rolesString, String roleId,
	    String roleName) {
	String expectedString = "{\"id\":\"" + roleId + "\",\"name\":\""
		+ roleName + "\"}";
	assertTrue("Expected string '" + expectedString
		+ "' is not contained in '" + rolesString + "'.",
		rolesString.contains(expectedString));

    }

    private void assertContainsUser(String usersString, String email,
	    String name, String roleId, String roleName) {
	String expectedString = "{\"email\":\"" + email + "\",\"name\":\""
		+ name + "\",\"role\":{\"id\":\"" + roleId + "\",\"name\":\""
		+ roleName + "\"}}";
	assertTrue("Expected string '" + expectedString
		+ "' is not contained in '" + usersString + "'.",
		usersString.contains(expectedString));
    }

    @Test
    public void testCreateAccount() throws URISyntaxException,
	    JsonGenerationException, JsonMappingException,
	    UnsupportedEncodingException, IOException {
	HttpPut httpPut = new HttpPut(new URI(getAccountManagerRestURI()
		.toString() + "/users"));
	CreateAccountEntity createAccountEntity = new CreateAccountEntity(
		"a@a.de", "aBcD123!", "engineer");
	HttpEntity entity = new StringEntity(
		JSONSerializer.toJSONString(createAccountEntity));
	httpPut.setHeader(HTTP.CONTENT_TYPE, MediaType.APPLICATION_JSON);
	httpPut.setEntity(entity);
	try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
	    assertEquals(HttpStatus.SC_CREATED, response.getStatusLine()
		    .getStatusCode());
	}
    }
}
