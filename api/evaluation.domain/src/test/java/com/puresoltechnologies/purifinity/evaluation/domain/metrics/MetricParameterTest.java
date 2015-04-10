package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.domain.JSONSerializer;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;

public class MetricParameterTest {

	@Test
	public void testSerialization() throws JsonGenerationException,
			JsonMappingException, IOException {
		MetricParameter<Float> parameter = new MetricParameter<Float>("name",
				"unit", LevelOfMeasurement.INTERVAL, "description", Float.class);
		String serialized = JSONSerializer.toJSONString(parameter);
		System.out.println(serialized);
		MetricParameter<?> deserialized = JSONSerializer.fromJSONString(
				serialized, MetricParameter.class);
		assertEquals(parameter, deserialized);
	}
}
