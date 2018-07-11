package com.puresoltechnologies.toolshed.server.api.kpis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class HistogramDefinitionTest {

    @Test
    public void testSerialization() throws IOException {
	HistogramDefinition definition = new HistogramDefinition("nam", "unit", "description",
		LevelOfMeasurement.INTERVAL);
	String serialized = JsonSerializer.toString(definition);
	HistogramDefinition deserialized = JsonSerializer.fromString(serialized, HistogramDefinition.class);
	assertEquals(definition, deserialized);
    }

}
