package com.puresoltechnologies.purifinity.server.passwordstore.rest.api;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;

public class PasswordAuthenticationEntityTest {

	@Test
	public void test() throws JsonGenerationException, JsonMappingException,
			IOException {
		PasswordAuthenticationEntity entity = new PasswordAuthenticationEntity(
				"a@a.de", "pw");
		String jsonString = JSONSerializer.toJSONString(entity);
		System.out.println(jsonString);
	}
}
