package com.puresoltechnologies.purifinity.server.passwordstore.rest.impl;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordAuthenticationEntity;

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
