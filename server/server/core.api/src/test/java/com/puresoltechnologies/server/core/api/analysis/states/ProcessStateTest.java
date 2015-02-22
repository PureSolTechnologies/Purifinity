package com.puresoltechnologies.server.core.api.analysis.states;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessState;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.ProcessState;

public class ProcessStateTest {

    @Test
    public void testSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	ProcessState information = new ProcessState("Project",
		AnalysisProcessState.STORING, 50, 100, "unit");
	String serialized = JSONSerializer.toJSONString(information);
	System.out.println(serialized);
	ProcessState deserialized = JSONSerializer.fromJSONString(serialized,
		ProcessState.class);
	assertEquals(information, deserialized);
    }

}
