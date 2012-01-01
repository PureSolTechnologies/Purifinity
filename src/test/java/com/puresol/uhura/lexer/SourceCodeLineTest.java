package com.puresol.uhura.lexer;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class SourceCodeLineTest {

	@Test
	public void testInstance() {
		assertNotNull(new SourceCodeLine(new File("test.file"), 42,
				"This is a line!"));
	}

	@Test
	public void testGetters() {
		SourceCodeLine sourceCodeLine = new SourceCodeLine(
				new File("test.file"), 42, "This is a line!");
		assertEquals(new File("test.file"), sourceCodeLine.getFile());
		assertEquals(42, sourceCodeLine.getLineNumber());
		assertEquals("This is a line!", sourceCodeLine.getLine());
	}

}
