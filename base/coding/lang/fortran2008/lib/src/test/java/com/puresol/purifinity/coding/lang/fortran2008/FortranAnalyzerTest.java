/**
 * Test //
 */
package com.puresol.purifinity.coding.lang.fortran2008;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Test;

import com.puresol.purifinity.uhura.source.SourceFileLocation;

/**
 * This test checks the JavaAnalyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyzerTest {

	@Test
	public void testInstance() {
		assertNotNull(new FortranAnalyzer(new SourceFileLocation("", "")));
	}

	@Test
	public void testInitValues() {
		FortranAnalyzer analyser = new FortranAnalyzer(new SourceFileLocation(
				"src/test", "TestFile.f"));
		assertNull(analyser.getAnalysis());
		assertSame(Fortran.getInstance(), analyser.getLanguage());
	}

	private void test(File sourceDirectory, File file) throws Exception {
		FortranAnalyzer analyser = new FortranAnalyzer(new SourceFileLocation(
				sourceDirectory, file));
		analyser.analyze();
	}

	@Test
	public void testEmptyProgram() throws Exception {
		test(new File("src/test/resources"), new File(
				"com/puresol/coding/lang/fortran2008/samples/EmptyProgram.f"));
	}

	@Test
	public void testEmptySubroutine() throws Exception {
		test(new File("src/test/resources"),
				new File(
						"com/puresol/coding/lang/fortran2008/samples/EmptySubroutine.f"));
	}

	@Test
	public void test2() throws Exception {
		test(new File("src/test/resources"), new File(
				"com/puresol/coding/lang/fortran2008/samples/FortranTest.f"));
	}

	@Test
	public void testZGERC() throws Exception {
		test(new File("src/test/resources"), new File(
				"com/puresol/coding/lang/fortran2008/samples/zgerc.f"));
	}

	/**
	 * This checks the correct removal of empty productions for Fortran.
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testNDTRAN() throws Exception {
		test(new File("src/test/resources"), new File(
				"com/puresol/coding/lang/fortran2008/samples/ndtran.f"));
	}
}
