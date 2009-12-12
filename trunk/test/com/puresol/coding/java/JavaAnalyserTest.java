package com.puresol.coding.java;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;

public class JavaAnalyserTest extends TestCase {

	@Test
	public void testJavaParser() throws IOException {
		File file = new File("test/com/puresol/coding/java/Test.java");
		new JavaAnalyser(file);
	}
}
