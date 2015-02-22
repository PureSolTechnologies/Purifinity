package com.puresoltechnologies.purifinity.server.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;

public class ProcessStateTest {

    @Test
    public void testSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	ProcessState information = new ProcessState("Project: storing", 50,
		100, "unit");
	String serialized = JSONSerializer.toJSONString(information);
	System.out.println(serialized);
	ProcessState deserialized = JSONSerializer.fromJSONString(serialized,
		ProcessState.class);
	assertEquals(information, deserialized);
    }

}
