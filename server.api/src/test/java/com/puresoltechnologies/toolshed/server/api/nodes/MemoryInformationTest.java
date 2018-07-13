package com.puresoltechnologies.toolshed.server.api.nodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class MemoryInformationTest {

    @Test
    public void testSerialization() throws IOException {
	MemoryInformation definition = new MemoryInformation(16 * 1024 * 1024 * 1024, 4 * 1024 * 1024 * 1024);
	String serialized = JsonSerializer.toString(definition);
	MemoryInformation deserialized = JsonSerializer.fromString(serialized, MemoryInformation.class);
	assertEquals(definition, deserialized);
    }

}
