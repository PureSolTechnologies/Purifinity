package com.puresoltechnologies.purifinity.server.common.rest.security;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;

public class AuthLoginElementTest {

	@Test
	public void test() throws JsonGenerationException, JsonMappingException,
			IOException {
		AuthLoginElement authLoginElement = new AuthLoginElement(
				"name@example.com", "pass");

		String json = JSONSerializer.toJSONString(authLoginElement);
		System.out.println(json);
		assertNotNull(json);
		AuthLoginElement unmarshalled = JSONSerializer.fromJSONString(json,
				AuthLoginElement.class);
		assertNotNull(unmarshalled);
	}

}
