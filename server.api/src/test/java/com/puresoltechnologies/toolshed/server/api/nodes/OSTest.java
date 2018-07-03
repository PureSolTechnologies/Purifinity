package com.puresoltechnologies.toolshed.server.api.nodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OSTest {

    @Test
    public void testLinux() {
	assertEquals(OS.LINUX, OS.byName("linux"));
	assertEquals(OS.LINUX, OS.byName("Linux"));
	assertEquals(OS.LINUX, OS.byName("LINUX"));
    }

}
