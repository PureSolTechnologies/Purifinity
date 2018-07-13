package com.puresoltechnologies.toolshed.server.api.nodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class ProcessDetailsTest {

    @Test
    public void testSerialization() throws IOException {
	ProcessDetails definition = new ProcessDetails(12, 11, "name", ProcessStatus.RUNNING, "bash");
	String serialized = JsonSerializer.toString(definition);
	ProcessDetails deserialized = JsonSerializer.fromString(serialized, ProcessDetails.class);
	assertEquals(definition, deserialized);
    }
}
