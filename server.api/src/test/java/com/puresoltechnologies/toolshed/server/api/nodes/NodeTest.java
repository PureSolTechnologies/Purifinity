package com.puresoltechnologies.toolshed.server.api.nodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.commons.JsonSerializer;

public class NodeTest {

    @Test
    public void testSerialization() throws IOException {
	Node definition = new Node("id", OS.LINUX, "x86", "1.2.3", 4, new HashSet<>());
	String serialized = JsonSerializer.toString(definition);
	Node deserialized = JsonSerializer.fromString(serialized, Node.class);
	assertEquals(definition, deserialized);
    }

}
