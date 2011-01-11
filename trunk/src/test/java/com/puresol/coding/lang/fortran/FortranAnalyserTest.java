/**
 * Test //
 */
package com.puresol.coding.lang.fortran;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.coding.analysis.AnalyzerException;

/**
 * This test checks the JavaAnalyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyserTest {

	@Test
	public void testInstance() {
		assertNotNull(new FortranAnalyser(new File("")));
	}

	@Test
	public void testInitValues() {
		File file = new File("src/test/TestFile.f");
		FortranAnalyser analyser = new FortranAnalyser(file);
		assertEquals(file, analyser.getFile());
		assertNotNull(analyser.getTimeStamp());
		assertSame(Fortran.getInstance(), analyser.getLanguage());
		assertNull(analyser.getParserTree());
	}

	private void test(File file) {
		try {
			assertTrue(file.exists());
			Logger.getRootLogger().setLevel(Level.DEBUG);
			FortranAnalyser analyser = new FortranAnalyser(file);
			analyser.parse();
		} catch (AnalyzerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	// @Test
	// public void testEmptyProgram() {
	// test(new File(
	// "test/com/puresol/coding/lang/fortran/samples/EmptyProgram.f"));
	// }
	//
	// @Test
	// public void testEmptySubroutine() {
	// Logger.getRootLogger().setLevel(Level.TRACE);
	// test(new File(
	// "test/com/puresol/coding/lang/fortran/samples/EmptySubroutine.f"));
	// }
	//
	// @Test
	// public void testZGERC() {
	// test(new File("test/com/puresol/coding/lang/fortran/samples/zgerc.f"));
	// }

	@Test
	public void test2() {
		test(new File(
				"src/test/resources/com/puresol/coding/lang/fortran/samples/FortranTest.f"));
	}
}
