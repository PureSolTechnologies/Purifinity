package com.puresol.coding.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import com.puresol.uhura.source.SourceFileLocation;

public class TestLanguageAnalyserTest {

    @Test
    public void test() {
	assertNotNull(new TestLanguageAnalyser(new SourceFileLocation(new File(""))));
    }

    @Test
    public void testInitValues() {
	TestLanguageAnalyser analyser = new TestLanguageAnalyser(
		new SourceFileLocation(new File(".", "TestFile.test")));
	assertEquals(new SourceFileLocation(new File(".", "TestFile.test")),
		analyser.getSource());

    }
}
