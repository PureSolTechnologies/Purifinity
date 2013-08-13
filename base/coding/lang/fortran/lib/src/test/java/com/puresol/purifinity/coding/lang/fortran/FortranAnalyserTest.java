/**
 * Test //
 */
package com.puresol.purifinity.coding.lang.fortran;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran.ust.ProgramCreator;
import com.puresol.purifinity.uhura.source.SourceFileLocation;
import com.puresol.purifinity.uhura.ust.USTCreatorFactory;

/**
 * This test checks the JavaAnalyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyserTest {

	@BeforeClass
	public static void initialize() {
		USTCreatorFactory.initialize();
		USTCreatorFactory.register(ProgramCreator.class.getPackage());
	}

	@AfterClass
	public static void destroy() {
		USTCreatorFactory.destroy();
	}

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

	private void test(File sourceDirectory, File file) throws Throwable {
		FortranAnalyzer analyser = new FortranAnalyzer(new SourceFileLocation(
				sourceDirectory, file));
		analyser.analyze();
	}

	@Test
	public void testEmptyProgram() throws Throwable {
		test(new File("src/test/resources"), new File(
				"com/puresol/coding/lang/fortran/samples/EmptyProgram.f"));
	}

	@Test
	public void testEmptySubroutine() throws Throwable {
		test(new File("src/test/resources"), new File(
				"com/puresol/coding/lang/fortran/samples/EmptySubroutine.f"));
	}

	@Test
	public void testZGERC() throws Throwable {
		test(new File("src/test/resources"), new File(
				"com/puresol/coding/lang/fortran/samples/zgerc.f"));
	}

	@Test
	public void test2() throws Throwable {
		test(new File("src/test/resources"), new File(
				"com/puresol/coding/lang/fortran/samples/FortranTest.f"));
	}
}
