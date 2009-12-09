package com.puresol.coding.java;

import java.io.File;

import org.junit.Test;

import junit.framework.TestCase;

public class JavaAnalyserTest extends TestCase {

	@Test
	public void testJavaParser() {
		File file = new File(
				"test/com/puresol/coding/java/AnalyserTestClass.java");
		JavaAnalyser analyser = new JavaAnalyser(file);
	}

//	@Test
//	public void testJavaParser2() {
//		File file = new File(
//				"src/com/puresol/coding/java/JavaAnalyser.java");
//		JavaAnalyser analyser = new JavaAnalyser(file);
//	}
}
