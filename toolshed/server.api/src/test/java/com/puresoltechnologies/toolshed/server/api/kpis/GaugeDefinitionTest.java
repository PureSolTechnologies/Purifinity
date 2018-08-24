package com.puresoltechnologies.toolshed.server.api.kpis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class GaugeDefinitionTest {

    @Test
    public void testSerialization() throws IOException {
	GaugeDefinition definition = new GaugeDefinition("name", "unit", "description", LevelOfMeasurement.INTERVAL);
	String serialized = JsonSerializer.toString(definition);
	GaugeDefinition deserialized = JsonSerializer.fromString(serialized, GaugeDefinition.class);
	assertEquals(definition, deserialized);
    }

}
