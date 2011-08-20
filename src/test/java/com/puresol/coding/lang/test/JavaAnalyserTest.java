package com.puresol.coding.lang.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class JavaAnalyserTest {

	@Test
	public void test() {
		assertNotNull(new TestLanguageAnalyser(new File("")));
	}

	@Test
	public void testInitValues() {
		TestLanguageAnalyser analyser = new TestLanguageAnalyser(new File(
				"TestFile.test"));
		assertEquals(new File("TestFile.test"), analyser.getFile());

	}
}
