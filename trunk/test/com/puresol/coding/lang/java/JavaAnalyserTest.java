package com.puresol.coding.lang.java;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.utils.FileUtilities;

public class JavaAnalyserTest {

	@Test
	public void test() {
		assertNotNull(new JavaAnalyser(new File("")));
	}

	@Test
	public void testInitValues() {
		try {
			File file = new File("test", FileUtilities
					.classToRelativePackagePath(this.getClass()).toString());
			System.out.println(file.toString());
			assertTrue(file.exists());
			JavaAnalyser analyser = new JavaAnalyser(file);
			analyser.parse();
		} catch (AnalyzerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
