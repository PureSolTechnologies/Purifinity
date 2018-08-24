package com.puresoltechnologies.commons.domain;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ConfigurationParameterTest {

	@Test
	public void testJSONSerialization() throws JsonGenerationException,
			JsonMappingException, IOException {
		ConfigurationParameter<String> configurationParameter = new ConfigurationParameter<>(
				"name", "unit", LevelOfMeasurement.INTERVAL, "description",
				String.class, "property.key", "/path", "default");
		String serialized = JSONSerializer.toJSONString(configurationParameter);
		System.out.println(serialized);
		ConfigurationParameter<?> deserialized = JSONSerializer.fromJSONString(
				serialized, ConfigurationParameter.class);
		assertEquals(configurationParameter, deserialized);
	}

}
