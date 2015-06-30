package com.puresoltechnologies.purifinity.server.test.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.parsers.source.SourceFileLocation;

public class TestLanguageAnalyserTest {

	@Test
	public void test() {
		assertNotNull(new TestLanguageAnalyser(new SourceCode(null, null), null));
	}

	@Test
	public void testInitValues() throws IOException {
		String directory = "src/test/resources/com/puresoltechnologies/purifinity/server/test/lang";
		String fileName = "TestFile.test";
		SourceFileLocation sourceFileLocation = new SourceFileLocation(directory, fileName);
		try (InputStream sourceStream = sourceFileLocation.openStream()) {
			TestLanguageAnalyser analyser = new TestLanguageAnalyser(SourceCode.read(sourceStream, sourceFileLocation),
					null);
			assertEquals("TestFile.test:1\t5+5", analyser.getSourceCode().toString().trim());
		}
	}
}
