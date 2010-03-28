/**
 * Test //
 */
package com.puresol.coding.lang.java;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.puresol.coding.analysis.CodeAnalysisProperties;
import com.puresol.coding.analysis.CodeRange;

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
	public void testRegistrationInCodeAnalysisProperties() {
		Assert.assertTrue(CodeAnalysisProperties.getPropertyValue(
				"CodeAnalysis.Analysers")
				.contains(JavaAnalyser.class.getName()));
	}

	@Test
	public void test() {
		JavaAnalyser analyser = new JavaAnalyser(new File("test"), new File(
				"com/puresol/coding/lang/java/samples/RandomNumbers.java"));
		ArrayList<CodeRange> codeRanges = analyser.getCodeRanges();
		Assert.assertNotNull(codeRanges);
		for (CodeRange codeRange : codeRanges) {
			System.out.println(codeRange.toString());
		}
		Assert.assertTrue(codeRanges.size() > 1);
	}

	public static void main(String[] args) {
		new JavaAnalyserTest().test();
	}
}
