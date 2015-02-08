package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;

public class CreateAccountEntityTest {

    @Test
    public void testSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	CreateAccountEntity entity = new CreateAccountEntity("email",
		"password", "engineer");
	String serialized = JSONSerializer.toJSONString(entity);
	CreateAccountEntity deserialized = JSONSerializer.fromJSONString(
		serialized, CreateAccountEntity.class);
	assertEquals(entity, deserialized);
    }

}
