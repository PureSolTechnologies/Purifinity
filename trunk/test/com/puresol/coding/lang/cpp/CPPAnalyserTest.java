/**
 * Test //
 */
package com.puresol.coding.lang.cpp;

import java.io.File;
import java.util.List;

import org.junit.Test;

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
		CPPAnalyser analyser = new CPPAnalyser(new File("test"), new File(
				"com/puresol/coding/lang/cpp/samples/String.cpp"));
		List<CodeRange> codeRanges = analyser.getCodeRanges();
		Assert.assertNotNull(codeRanges);
		for (CodeRange codeRange : codeRanges) {
			System.out.println(codeRange.toString());
		}
		Assert.assertTrue(codeRanges.size() > 0);
	}

	public static void main(String[] args) {
		new CPPAnalyserTest().test();
	}
}
