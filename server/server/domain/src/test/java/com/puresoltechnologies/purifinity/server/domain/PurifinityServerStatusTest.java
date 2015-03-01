package com.puresoltechnologies.purifinity.server.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.math.JSONSerializer;

public class PurifinityServerStatusTest {

    @Test
    public void testSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	PurifinityServerStatus information = new PurifinityServerStatus(1l, 2l,
		3l, 4l, 5l, 6, 7);
	String serialized = JSONSerializer.toJSONString(information);
	System.out.println(serialized);
	PurifinityServerStatus deserialized = JSONSerializer.fromJSONString(
		serialized, PurifinityServerStatus.class);
	assertEquals(information, deserialized);
    }

}
