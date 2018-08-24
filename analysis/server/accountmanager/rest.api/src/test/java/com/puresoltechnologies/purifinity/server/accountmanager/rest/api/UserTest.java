package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;

public class UserTest {

	@Test
	public void testSerialization() throws JsonGenerationException,
			JsonMappingException, IOException {
		User user = new User("email@domain.com", "name", new Role("id", "name"));
		String serialized = JSONSerializer.toJSONString(user);
		User deserialized = JSONSerializer.fromJSONString(serialized,
				User.class);
		assertEquals(user, deserialized);
	}

}
