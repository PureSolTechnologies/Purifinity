package com.puresoltechnologies.toolshed.server.api.kpis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class TimerDefinitionTest {

    @Test
    public void testSerialization() throws IOException {
	TimerDefinition definition = new TimerDefinition("name", "unit", "description");
	String serialized = JsonSerializer.toString(definition);
	TimerDefinition deserialized = JsonSerializer.fromString(serialized, TimerDefinition.class);
	assertEquals(definition, deserialized);
    }

}
