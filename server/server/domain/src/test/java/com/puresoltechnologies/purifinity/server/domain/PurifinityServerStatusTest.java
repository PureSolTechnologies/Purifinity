package com.puresoltechnologies.purifinity.server.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.purifinity.server.domain.PurifinityServerStatus;

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
