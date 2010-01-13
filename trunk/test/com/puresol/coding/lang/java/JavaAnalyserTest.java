/**
 * Test //
 */
package com.puresol.coding.lang.java;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.puresol.coding.CodeRange;

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
	JavaAnalyser analyser =
		new JavaAnalyser(new File("src"), new File(
			"apps/CodeAnalysis.java"));
	ArrayList<CodeRange> codeRanges = analyser.getCodeRanges();
	Assert.assertNotNull(codeRanges);
	for (CodeRange codeRange : codeRanges) {
	    System.out.println(codeRange.toString());
	}
	Assert.assertTrue(codeRanges.size() > 1);
    }
}
