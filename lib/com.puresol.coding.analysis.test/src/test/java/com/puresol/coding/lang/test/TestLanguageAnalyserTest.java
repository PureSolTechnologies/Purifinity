package com.puresol.coding.lang.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

public class TestLanguageAnalyserTest {

    @Test
    public void test() {
	assertNotNull(new TestLanguageAnalyser(new File(""), new File("")));
    }

    @Test
    public void testInitValues() {
	TestLanguageAnalyser analyser = new TestLanguageAnalyser(new File("."),
		new File("TestFile.test"));
	assertEquals(new File("TestFile.test"), analyser.getFile());

    }
}
