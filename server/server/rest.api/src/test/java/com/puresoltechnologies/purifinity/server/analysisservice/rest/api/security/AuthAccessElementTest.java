package com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.JSONSerializer;

public class AuthAccessElementTest {

	@Test
	public void test() throws JsonGenerationException, JsonMappingException,
			IOException {
		AuthElement authLoginElement = new AuthElement("id", "token",
				"permission", "message");

		String json = JSONSerializer.toJSONString(authLoginElement);
		System.out.println(json);
		assertNotNull(json);
		AuthElement unmarshalled = JSONSerializer.fromJSONString(json,
				AuthElement.class);
		assertNotNull(unmarshalled);
	}

}
