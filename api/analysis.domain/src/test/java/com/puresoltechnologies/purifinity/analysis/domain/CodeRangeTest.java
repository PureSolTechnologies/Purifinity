package com.puresoltechnologies.purifinity.analysis.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.puresoltechnologies.commons.math.JSONSerializer;

public class CodeRangeTest {

    @Test
    public void testSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	CodeRange codeRange = new CodeRange("simple", "canonical",
		CodeRangeType.FUNCTION, null);
	String serialized = JSONSerializer.toJSONString(codeRange);
	System.out.println(serialized);
	CodeRange deserialized = JSONSerializer.fromJSONString(serialized,
		CodeRange.class);
	assertEquals(codeRange, deserialized);
    }

}
