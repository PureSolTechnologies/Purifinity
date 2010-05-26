/**
 * Test //
 */
package com.puresol.coding.lang.java;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.analysis.AnalyserException;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.lang.java.source.grammar.CompilationUnitTest;
import com.puresol.utils.Files;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * This test checks the JavaAnalyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyserTest extends TestCase {

	@Test
	public void test() {
		try {
			Logger.getRootLogger().setLevel(Level.DEBUG);
			JavaAnalyser analyser = new JavaAnalyser(new File("test"), Files
					.classToRelativePackagePath(CompilationUnitTest.class));
			analyser.parse();
			CodeRange rootCodeRange = analyser.getRootCodeRange();
			Assert.assertNotNull(rootCodeRange);
			for (CodeRange codeRange : rootCodeRange.getChildCodeRanges()) {
				System.out.println(codeRange.toString());
			}
			Assert.assertTrue(rootCodeRange.getChildCodeRanges().size() > 1);
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	public static void main(String[] args) {
		new JavaAnalyserTest().test();
	}
}
