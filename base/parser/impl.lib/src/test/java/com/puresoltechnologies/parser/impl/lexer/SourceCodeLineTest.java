package com.puresoltechnologies.parser.impl.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresoltechnologies.parser.impl.source.SourceCodeLine;
import com.puresoltechnologies.parser.impl.source.SourceFileLocation;
import com.puresoltechnologies.parser.impl.source.UnspecifiedSourceCodeLocation;

public class SourceCodeLineTest {

    @Test
    public void testInstance() {
	assertNotNull(new SourceCodeLine(new UnspecifiedSourceCodeLocation(),
		42, "This is a line!"));
    }

    @Test
    public void testGetters() {
	SourceCodeLine sourceCodeLine = new SourceCodeLine(
		new SourceFileLocation("", "test.file"), 42, "This is a line!");
	assertEquals(new SourceFileLocation("", "test.file"),
		sourceCodeLine.getSource());
	assertEquals(42, sourceCodeLine.getLineNumber());
	assertEquals("This is a line!", sourceCodeLine.getLine());
    }
}
