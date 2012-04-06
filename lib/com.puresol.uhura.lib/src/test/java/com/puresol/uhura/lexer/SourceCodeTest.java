package com.puresol.uhura.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

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
	SourceCode sourceCode = SourceCode.read(file);
	assertNotNull(sourceCode);
	List<SourceCodeLine> lines = sourceCode.getSource();
	String sourceString = FileUtilities.readFileToString(file);
	StringBuffer buffer = new StringBuffer();
	int lineNumber = 0;
	for (SourceCodeLine line : lines) {
	    lineNumber++;
	    assertEquals(lineNumber, line.getLineNumber());
	    assertEquals(file, line.getFile());
	    buffer.append(line.getLine());
	}
	assertEquals(sourceString, buffer.toString());
    }
}
