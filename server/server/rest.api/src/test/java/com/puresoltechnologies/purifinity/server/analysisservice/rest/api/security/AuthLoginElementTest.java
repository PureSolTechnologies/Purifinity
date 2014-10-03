package com.puresoltechnologies.purifinity.server.analysisservice.rest.api.security;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.JSONSerializer;

public class AuthLoginElementTest {

    @Test
    public void test() throws JsonGenerationException, JsonMappingException,
	    IOException {
	AuthLoginElement authLoginElement = new AuthLoginElement("name", "pass");

	String json = JSONSerializer.toJSONString(authLoginElement);
	System.out.println(json);
	assertNotNull(json);
	AuthLoginElement unmarshalled = JSONSerializer.fromJSONString(json,
		AuthLoginElement.class);
	assertNotNull(unmarshalled);
    }

}