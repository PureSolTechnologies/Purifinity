package com.puresoltechnologies.parsers.impl.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresoltechnologies.parsers.api.source.SourceCodeLine;
import com.puresoltechnologies.parsers.impl.source.SourceCodeLineImpl;
import com.puresoltechnologies.parsers.impl.source.SourceFileLocation;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;

public class SourceCodeLineTest {

	@Test
	public void testInstance() {
		assertNotNull(new SourceCodeLineImpl(
				new UnspecifiedSourceCodeLocation(), 42, "This is a line!"));
	}

	@Test
	public void testGetters() {
		SourceCodeLine sourceCodeLine = new SourceCodeLineImpl(
				new SourceFileLocation("", "test.file"), 42, "This is a line!");
		assertEquals(new SourceFileLocation("", "test.file"),
				sourceCodeLine.getSource());
		assertEquals(42, sourceCodeLine.getLineNumber());
		assertEquals("This is a line!", sourceCodeLine.getLine());
	}
}
