/**
 * Test //
 */
package com.puresol.coding.lang.java;

import java.io.File;

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
public class JavaAnalyserTest extends TestCase {

	@Test
	public void test() {
		JavaAnalyser analyser = new JavaAnalyser(new File("test"), new File(
				"com/puresol/coding/lang/java/samples/RandomNumbers.java"));
		CodeRange rootCodeRange = analyser.getRootCodeRange();
		Assert.assertNotNull(rootCodeRange);
		for (CodeRange codeRange : rootCodeRange.getChildCodeRanges()) {
			System.out.println(codeRange.toString());
		}
		Assert.assertTrue(rootCodeRange.getChildCodeRanges().size() > 1);
	}

	public static void main(String[] args) {
		new JavaAnalyserTest().test();
	}
}
