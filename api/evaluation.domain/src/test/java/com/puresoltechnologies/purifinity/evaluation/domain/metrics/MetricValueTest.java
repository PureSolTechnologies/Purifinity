package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.puresoltechnologies.commons.math.JSONSerializer;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;

public class MetricValueTest {

    @Test
    public void testSerialization() throws JsonGenerationException,
	    JsonMappingException, IOException {
	MetricParameter<Double> parameter = new MetricParameter<>("name",
		"unit", LevelOfMeasurement.INTERVAL, "description",
		Double.class);
	MetricValue<Double> value = new MetricValue<Double>(1.23, parameter);
	String serialized = JSONSerializer.toJSONString(value);
	System.out.println(serialized);
	MetricValue<?> deserialized = JSONSerializer.fromJSONString(serialized,
		MetricValue.class);
	assertEquals(value, deserialized);
    }
}
