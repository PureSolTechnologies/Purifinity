package com.puresoltechnologies.purifinity.server.passwordstore.rest.impl;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.JSONSerializer;
import com.puresoltechnologies.commons.types.EmailAddress;
import com.puresoltechnologies.commons.types.Password;
import com.puresoltechnologies.purifinity.server.passwordstore.rest.api.PasswordAuthenticationEntity;

public class PasswordAuthenticationEntityTest {

    @Test
    public void test() throws JsonGenerationException, JsonMappingException,
	    IOException {
	PasswordAuthenticationEntity entity = new PasswordAuthenticationEntity(
		new EmailAddress("a@a.de"), new Password("pw"));
	String jsonString = JSONSerializer.toJSONString(entity);
	System.out.println(jsonString);
    }
}
