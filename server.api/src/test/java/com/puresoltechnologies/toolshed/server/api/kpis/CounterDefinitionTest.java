package com.puresoltechnologies.toolshed.server.api.kpis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class CounterDefinitionTest {

    @Test
    public void testSerialization() throws IOException {
	CounterDefinition definition = new CounterDefinition("name", "description");
	String serialized = JsonSerializer.toString(definition);
	CounterDefinition deserialized = JsonSerializer.fromString(serialized, CounterDefinition.class);
	assertEquals(definition, deserialized);
    }

}
