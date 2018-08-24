package com.puresoltechnologies.toolshed.server.api.nodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class ProcessInformationTest {

    @Test
    public void testSerialization() throws IOException {
	ProcessInformation definition = new ProcessInformation(12, 11, "name", ProcessStatus.RUNNING, "bash");
	String serialized = JsonSerializer.toString(definition);
	ProcessInformation deserialized = JsonSerializer.fromString(serialized, ProcessInformation.class);
	assertEquals(definition, deserialized);
    }
}
