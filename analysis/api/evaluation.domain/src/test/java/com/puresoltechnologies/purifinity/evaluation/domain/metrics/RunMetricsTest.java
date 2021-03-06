package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.versioning.Version;

public class RunMetricsTest {

    @Test
    public void testSerialization() throws JsonGenerationException, JsonMappingException, IOException {
	RunMetricsImpl metrics = new RunMetricsImpl("evaluatorId", new Version(1, 2, 3), new Date(),
		new HashMap<HashId, FileMetricsImpl>(), new HashMap<HashId, DirectoryMetricsImpl>());
	String serialized = JSONSerializer.toJSONString(metrics);
	System.out.println(serialized);
	RunMetricsImpl deserialized = JSONSerializer.fromJSONString(serialized, RunMetricsImpl.class);
	assertEquals(metrics, deserialized);
    }
}
