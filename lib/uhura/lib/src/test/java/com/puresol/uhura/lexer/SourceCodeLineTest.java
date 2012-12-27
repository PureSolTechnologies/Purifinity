package com.puresol.uhura.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import com.puresol.uhura.source.FileSource;
import com.puresol.uhura.source.SourceCodeLine;
import com.puresol.uhura.source.UnspecifiedSource;

public class SourceCodeLineTest {

    @Test
    public void testInstance() {
	assertNotNull(new SourceCodeLine(new UnspecifiedSource(), 42,
		"This is a line!"));
    }

    @Test
    public void testGetters() {
	SourceCodeLine sourceCodeLine = new SourceCodeLine(new FileSource(
		new File("test.file")), 42, "This is a line!");
	assertEquals(new FileSource(new File("test.file")),
		sourceCodeLine.getSource());
	assertEquals(42, sourceCodeLine.getLineNumber());
	assertEquals("This is a line!", sourceCodeLine.getLine());
    }
}
