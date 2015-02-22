package com.puresoltechnologies.purifinity.server.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;

public class PurifinityProcessStatesTest {

    @Test
    public void testSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	List<ProcessState> states = new ArrayList<ProcessState>();
	states.add(new ProcessState("process1,", 10, 100, "unit1"));
	states.add(new ProcessState("process2,", 20, 100, "unit2"));
	PurifinityProcessStates information = new PurifinityProcessStates(
		new Date(), states);
	String serialized = JSONSerializer.toJSONString(information);
	System.out.println(serialized);
	PurifinityProcessStates deserialized = JSONSerializer.fromJSONString(
		serialized, PurifinityProcessStates.class);
	assertEquals(information, deserialized);
    }

}
