package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.versioning.Version;

public class GenericDirectoryMetricsTest {

	@Test
	public void testSerialization() throws JsonGenerationException,
			JsonMappingException, IOException {
		GenericDirectoryMetrics metrics = new GenericDirectoryMetrics(
				"evaluatorId", new Version(1, 2, 3),
				HashId.valueOf("SHA-256:abcdef"), new Date(),
				new HashSet<MetricParameter<?>>(),
				new HashMap<String, MetricValue<?>>());
		String serialized = JSONSerializer.toJSONString(metrics);
		System.out.println(serialized);
		GenericDirectoryMetrics deserialized = JSONSerializer.fromJSONString(
				serialized, GenericDirectoryMetrics.class);
		assertEquals(metrics, deserialized);
	}
}
