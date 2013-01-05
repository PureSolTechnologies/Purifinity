package com.puresol.uhura.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;
import com.puresol.uhura.source.SourceFileLocation;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PathUtils;

public class SourceCodeTest {

    @Test
    public void testInstance() {
	assertNotNull(new SourceCode());
    }

    @Test
    public void testRead() throws Throwable {
	File file = new File("src/test/java", PathUtils
		.classToRelativePackagePath(SourceCodeTest.class).getPath());
	assertTrue(file.exists());
	SourceFileLocation fileSource = new SourceFileLocation(file);
	SourceCode sourceCode = fileSource.load();
	assertNotNull(sourceCode);
	List<SourceCodeLine> lines = sourceCode.getLines();
	String sourceString = FileUtilities.readFileToString(file);
	StringBuffer buffer = new StringBuffer();
	int lineNumber = 0;
	for (SourceCodeLine line : lines) {
	    lineNumber++;
	    assertEquals(lineNumber, line.getLineNumber());
	    assertEquals(fileSource, line.getSource());
	    buffer.append(line.getLine());
	}
	assertEquals(sourceString, buffer.toString());
    }

    @Test
    public void testAddAndRemoveLastLinesLineTerminatorWithMissingTerminator() {
	String originalLastLine = "Hallo, world!";
	SourceCode sourceCode = new SourceCode();
	SourceCodeLine line = new SourceCodeLine(new SourceFileLocation(
		new File("test")), 1, originalLastLine);
	sourceCode.addSourceCodeLine(line);

	assertEquals(originalLastLine, sourceCode.getLines().get(0).getLine());
	assertTrue(sourceCode.assureLineTerminatorAtLastLine());
	assertEquals(originalLastLine + "\n", sourceCode.getLines().get(0)
		.getLine());
	assertTrue(sourceCode.removeLineTerminatorAtLastLine());
	assertEquals(originalLastLine, sourceCode.getLines().get(0).getLine());
    }

    @Test
    public void testAddAndRemoveLastLinesLineTerminatorWithTerminator() {
	String originalLastLine = "Hallo, world!\n";
	SourceCode sourceCode = new SourceCode();
	SourceCodeLine line = new SourceCodeLine(new SourceFileLocation(
		new File("test")), 1, originalLastLine);
	sourceCode.addSourceCodeLine(line);

	assertEquals(originalLastLine, sourceCode.getLines().get(0).getLine());
	assertFalse(sourceCode.assureLineTerminatorAtLastLine());
	assertEquals(originalLastLine, sourceCode.getLines().get(0).getLine());
    }

    @Test(expected = IllegalStateException.class)
    public void testAddAndRemoveLastLinesLineTerminatorWithMissingSourceCodeLine() {
	SourceCode sourceCode = new SourceCode();
	sourceCode.assureLineTerminatorAtLastLine();
    }
}
