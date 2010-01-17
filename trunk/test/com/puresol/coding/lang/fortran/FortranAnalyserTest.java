/**
 * Test //
 */
package com.puresol.coding.lang.fortran;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.i18n4j.FileSearch;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.lang.fortran.source.literals.FormatLiteral;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * This test checks the JavaAnalyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyserTest extends TestCase {

//	@Test
//	public void test() {
//		FortranAnalyser analyser = new FortranAnalyser(new File("test"),
//				new File("com/puresol/coding/lang/fortran/samples/zgerc.f"));
//		ArrayList<CodeRange> codeRanges = analyser.getCodeRanges();
//		Assert.assertNotNull(codeRanges);
//		for (CodeRange codeRange : codeRanges) {
//			System.out.println(codeRange.toString());
//		}
//		Assert.assertTrue(codeRanges.size() > 0);
//	}

//	@Test
//	public void test2() {
//		ArrayList<File> files = FileSearch
//				.find("/usr/src/compile/ATLAS/**/*.f");
//		for (File file : files) {
//			System.out.println(file.getPath());
//			FortranAnalyser analyser = new FortranAnalyser(new File("/"), file);
//			ArrayList<CodeRange> codeRanges = analyser.getCodeRanges();
//			Assert.assertNotNull(codeRanges);
//			Assert.assertTrue(codeRanges.size() > 0);
//		}
//	}

	// @Test
	// public void test3() {
	// String s = "10x, ";
	// FormatLiteral f = new FormatLiteral();
	//
	// Pattern p = f.getStartPattern();
	// Matcher m = p.matcher(s);
	// Assert.assertTrue(m.find());
	// System.out.println(m.group());
	// Assert.assertTrue(f.atStart(s));
	// Logger.getRootLogger().setLevel(Level.TRACE);
	// FortranAnalyser analyser = new FortranAnalyser(new File("/"), new File(
	// "/usr/src/compile/ATLAS/interfaces/blas/C/testing/c_cblat2.f"));
	// ArrayList<CodeRange> codeRanges = analyser.getCodeRanges();
	// Assert.assertNotNull(codeRanges);
	// for (CodeRange codeRange : codeRanges) {
	// System.out.println(codeRange.toString());
	// }
	// Assert.assertTrue(codeRanges.size() > 0);
	// }
}
