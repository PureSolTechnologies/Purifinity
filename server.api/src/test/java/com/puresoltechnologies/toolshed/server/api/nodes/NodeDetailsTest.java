package com.puresoltechnologies.toolshed.server.api.nodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class NodeDetailsTest {

    @Test
    public void testSerialization() throws IOException {
	NodeDetails definition = new NodeDetails("id", OS.LINUX, "x86", "1.2.3", 4, 16 * 1024 * 1024 * 1024,
		4 * 1024 * 1024 * 1024, new HashSet<>());
	String serialized = JsonSerializer.toString(definition);
	NodeDetails deserialized = JsonSerializer.fromString(serialized, NodeDetails.class);
	assertEquals(definition, deserialized);
    }

}
