package com.puresoltechnologies.toolshed.server.api.nodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class NodeInformationTest {

    @Test
    public void testSerialization() throws IOException {
	NodeInformation definition = new NodeInformation("id", OS.LINUX, "x86", "1.2.3", 4, 16 * 1024 * 1024 * 1024,
		4 * 1024 * 1024 * 1024);
	String serialized = JsonSerializer.toString(definition);
	NodeInformation deserialized = JsonSerializer.fromString(serialized, NodeInformation.class);
	assertEquals(definition, deserialized);
    }

}
