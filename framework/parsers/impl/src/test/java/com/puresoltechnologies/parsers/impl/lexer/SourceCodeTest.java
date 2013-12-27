package com.puresoltechnologies.parsers.impl.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresoltechnologies.parsers.api.source.SourceCode;
import com.puresoltechnologies.parsers.api.source.SourceCodeLine;
import com.puresoltechnologies.parsers.impl.source.SourceCodeImpl;
import com.puresoltechnologies.parsers.impl.source.SourceFileLocation;
import com.puresoltechnologies.purifinity.framework.commons.utils.FileUtilities;
import com.puresoltechnologies.purifinity.framework.commons.utils.PathUtils;

public class SourceCodeTest {

	@Test
	public void testInstance() {
		assertNotNull(new SourceCodeImpl());
	}

	@Test
	public void testRead() throws Throwable {
		File file = new File("src/test/java", PathUtils
				.classToRelativePackagePath(SourceCodeTest.class).getPath());
		SourceFileLocation fileSource = new SourceFileLocation(".",
				file.getPath());
		SourceCode sourceCode = fileSource.loadSourceCode();
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

}