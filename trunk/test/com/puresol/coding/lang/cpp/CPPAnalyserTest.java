/**
 * Test //
 */
package com.puresol.coding.lang.cpp;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.analysis.AnalyserException;
import com.puresol.coding.analysis.CodeRange;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * This test checks the JavaAnalyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CPPAnalyserTest extends TestCase {

	@Test
	public void test() {
		try {
			CPPAnalyser analyser = new CPPAnalyser(new File("test"), new File(
					"com/puresol/coding/lang/cpp/samples/String.cpp"));
			analyser.parse();
			Assert.assertNotNull(analyser.getRootCodeRange());
			List<CodeRange> codeRanges = analyser.getRootCodeRange()
					.getChildCodeRanges();
			Assert.assertNotNull(codeRanges);
		} catch (AnalyserException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	public static void main(String[] args) {
		new CPPAnalyserTest().test();
	}
}
