package com.puresol.purifinity.coding.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresol.purifinity.coding.lang.test.TestLanguageAnalyser;
import com.puresol.purifinity.uhura.source.SourceFileLocation;

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