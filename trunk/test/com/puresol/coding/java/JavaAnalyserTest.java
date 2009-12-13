package com.puresol.coding.java;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

public class JavaAnalyserTest extends TestCase {

	@Test
	public void testJavaParser() throws IOException {
		// File file = new File("test/com/puresol/coding/java/Test.java");
		Logger.getRootLogger().setLevel(Level.TRACE);
		File file = new File("test/com/puresol/coding/java/Test.java");
		new JavaAnalyser(file);
	}
}
