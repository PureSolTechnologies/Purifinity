package com.puresoltechnologies.purifinity.server.common.rest.security;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;

public class AuthElementTest {

	@Test
	public void test() throws JsonGenerationException, JsonMappingException,
			IOException {
		AuthElement authLoginElement = new AuthElement("email@example.com",
				"token", "permission", "message");

		String json = JSONSerializer.toJSONString(authLoginElement);
		System.out.println(json);
		assertNotNull(json);
		AuthElement unmarshalled = JSONSerializer.fromJSONString(json,
				AuthElement.class);
		assertNotNull(unmarshalled);
	}

}
