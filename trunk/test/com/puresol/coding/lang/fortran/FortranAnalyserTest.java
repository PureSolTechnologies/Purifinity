/**
 * Test //
 */
package com.puresol.coding.lang.fortran;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * This test checks the JavaAnalyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyserTest extends TestCase {

	// @Test
	// public void test() {
	// try {
	// fail();
	// Logger.getRootLogger().setLevel(Level.TRACE);
	// FortranAnalyser analyser = new FortranAnalyser(new File(
	// "test/com/puresol/coding/lang/fortran/samples/zgerc.f"));
	// analyser.parse();
	// } catch (FortranException e) {
	// e.printStackTrace();
	// fail("No exception was expected!");
	// }
	// }

	@Test
	public void test2() {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			FortranAnalyser analyser = new FortranAnalyser(new File(
					"test/com/puresol/coding/lang/fortran/samples/FortranTest.f"));
			analyser.parse();
		} catch (FortranException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
