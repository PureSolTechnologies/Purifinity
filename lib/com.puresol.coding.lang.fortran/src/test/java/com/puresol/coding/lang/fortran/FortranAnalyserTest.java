/**
 * Test //
 */
package com.puresol.coding.lang.fortran;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

/**
 * This test checks the JavaAnalyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyserTest {

    @Test
    public void testInstance() {
	assertNotNull(new FortranAnalyzer(new File(""), new File("")));
    }

    @Test
    public void testInitValues() {
	FortranAnalyzer analyser = new FortranAnalyzer(new File("src/test"),
		new File("TestFile.f"));
	assertNull(analyser.getAnalysis());
	assertSame(Fortran.getInstance(), analyser.getLanguage());
    }

    private void test(File sourceDirectory, File file) throws Throwable {
	assertTrue(file.exists());
	FortranAnalyzer analyser = new FortranAnalyzer(sourceDirectory, file);
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
