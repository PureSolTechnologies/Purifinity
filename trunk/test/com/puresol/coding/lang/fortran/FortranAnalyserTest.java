/**
 * Test //
 */
package com.puresol.coding.lang.fortran;

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
public class FortranAnalyserTest extends TestCase {

    @Test
    public void test() {
	FortranAnalyser analyser =
		new FortranAnalyser(new File("test"), new File(
			"com/puresol/coding/lang/fortran/samples/zgerc.f"));
	ArrayList<CodeRange> codeRanges = analyser.getCodeRanges();
	Assert.assertNotNull(codeRanges);
	for (CodeRange codeRange : codeRanges) {
	    System.out.println(codeRange.toString());
	}
	Assert.assertTrue(codeRanges.size() > 0);
    }
}
