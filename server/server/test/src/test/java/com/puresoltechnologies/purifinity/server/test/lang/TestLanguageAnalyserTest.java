package com.puresoltechnologies.purifinity.server.test.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresoltechnologies.parsers.source.SourceFileLocation;

public class TestLanguageAnalyserTest {

	@Test
	public void test() {
		assertNotNull(new TestLanguageAnalyser(new SourceFileLocation("", "")));
	}

	@Test
	public void testInitValues() {
		TestLanguageAnalyser analyser = new TestLanguageAnalyser(
				new SourceFileLocation(".", "TestFile.test"));
		assertEquals(new SourceFileLocation(".", "TestFile.test"),
				analyser.getSource());

	}
}
