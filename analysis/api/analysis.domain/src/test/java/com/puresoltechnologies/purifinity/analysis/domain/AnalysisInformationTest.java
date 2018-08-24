package com.puresoltechnologies.purifinity.analysis.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.versioning.Version;

public class AnalysisInformationTest {

    @Test
    public void testJSONSerialization() throws JsonGenerationException, JsonMappingException, IOException {
	AnalysisInformation analysisInformation = new AnalysisInformation(HashId.valueOf("SHA-256:1234567890abcdef"),
		Instant.now(), Duration.ofMillis(1234), true, "Language Name", "Language Version", "analyzerId",
		Version.valueOf("1.2.3"));
	String serialized = JSONSerializer.toJSONString(analysisInformation);
	AnalysisInformation deserialized = JSONSerializer.fromJSONString(serialized, AnalysisInformation.class);
	assertEquals(analysisInformation, deserialized);
    }
}
