package com.puresoltechnologies.server.core.api.analysis.states;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessState;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.ProcessState;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.PurifinityProcessStates;

public class PurifinityProcessStatesTest {

    @Test
    public void testSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	List<ProcessState> states = new ArrayList<ProcessState>();
	states.add(new ProcessState("process1,", AnalysisProcessState.STORING,
		10, 100, "unit1"));
	states.add(new ProcessState("process2,",
		AnalysisProcessState.ANALYZING, 20, 100, "unit2"));
	PurifinityProcessStates information = new PurifinityProcessStates(
		new Date(), states);
	String serialized = JSONSerializer.toJSONString(information);
	System.out.println(serialized);
	PurifinityProcessStates deserialized = JSONSerializer.fromJSONString(
		serialized, PurifinityProcessStates.class);
	assertEquals(information, deserialized);
    }

}
