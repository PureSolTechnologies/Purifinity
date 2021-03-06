package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;

public class RoleTest {

	@Test
	public void testSerialization() throws JsonGenerationException,
			JsonMappingException, IOException {
		Role role = new Role("id", "name");
		String serialized = JSONSerializer.toJSONString(role);
		Role deserialized = JSONSerializer.fromJSONString(serialized,
				Role.class);
		assertEquals(role, deserialized);
	}

}
