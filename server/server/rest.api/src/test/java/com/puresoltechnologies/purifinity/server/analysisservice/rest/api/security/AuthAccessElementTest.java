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
	AuthAccessElement authLoginElement = new AuthAccessElement("id",
		"token", "permission");

	String json = JSONSerializer.toJSONString(authLoginElement);
	System.out.println(json);
	assertNotNull(json);
	AuthAccessElement unmarshalled = JSONSerializer.fromJSONString(json,
		AuthAccessElement.class);
	assertNotNull(unmarshalled);
    }

}
