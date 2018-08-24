package com.puresoltechnologies.toolshed.server.api.kpis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class MeterDefinitionTest {

    @Test
    public void testSerialization() throws IOException {
	MeterDefinition definition = new MeterDefinition("name", "unit", "description", LevelOfMeasurement.INTERVAL);
	String serialized = JsonSerializer.toString(definition);
	MeterDefinition deserialized = JsonSerializer.fromString(serialized, MeterDefinition.class);
	assertEquals(definition, deserialized);
    }

}
