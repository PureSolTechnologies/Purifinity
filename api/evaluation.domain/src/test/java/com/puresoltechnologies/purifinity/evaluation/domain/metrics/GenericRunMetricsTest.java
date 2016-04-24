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

public class GenericRunMetricsTest {

    @Test
    public void testSerialization() throws JsonGenerationException, JsonMappingException, IOException {
	GenericRunMetrics metrics = new GenericRunMetrics("evaluatorId", new Version(1, 2, 3), new Date(),
		new MetricParameter<?>[] {}, new HashMap<HashId, GenericFileMetrics>(),
		new HashMap<HashId, GenericDirectoryMetrics>());
	String serialized = JSONSerializer.toJSONString(metrics);
	System.out.println(serialized);
	GenericRunMetrics deserialized = JSONSerializer.fromJSONString(serialized, GenericRunMetrics.class);
	assertEquals(metrics, deserialized);
    }
}
