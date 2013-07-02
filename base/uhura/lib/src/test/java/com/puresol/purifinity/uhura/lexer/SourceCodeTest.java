package com.puresol.purifinity.uhura.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.commons.utils.FileUtilities;
import com.puresol.commons.utils.PathUtils;
import com.puresol.purifinity.uhura.source.SourceCode;
import com.puresol.purifinity.uhura.source.SourceCodeLine;
import com.puresol.purifinity.uhura.source.SourceFileLocation;

public class SourceCodeTest {

	@Test
	public void testInstance() {
		assertNotNull(new SourceCode());
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
