package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.math.JSONSerializer;

public class ChangePasswordEntityTest {

    @Test
    public void testSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	ChangePasswordEntity entity = new ChangePasswordEntity("old", "new");
	String serialized = JSONSerializer.toJSONString(entity);
	ChangePasswordEntity deserialized = JSONSerializer.fromJSONString(
		serialized, ChangePasswordEntity.class);
	assertEquals(entity, deserialized);
    }

}
