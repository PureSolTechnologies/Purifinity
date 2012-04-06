/**
 * Test //
 */
package com.puresol.coding.lang.fortran;

import static org.junit.Assert.assertEquals;
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
	assertNotNull(new FortranAnalyzer(new File("")));
    }

    @Test
    public void testInitValues() {
	File file = new File("src/test/TestFile.f");
	FortranAnalyzer analyser = new FortranAnalyzer(file);
	assertEquals(file, analyser.getFile());
	assertNotNull(analyser.getTimeStamp());
	assertSame(Fortran.getInstance(), analyser.getLanguage());
	assertNull(analyser.getParserTree());
    }

    private void test(File file) throws Throwable {
	assertTrue(file.exists());
	FortranAnalyzer analyser = new FortranAnalyzer(file);
	analyser.analyze();
    }

    @Test
    public void testEmptyProgram() throws Throwable {
	test(new File(
		"src/test/resources/com/puresol/coding/lang/fortran/samples/EmptyProgram.f"));
    }

    @Test
    public void testEmptySubroutine() throws Throwable {
	test(new File(
		"src/test/resources/com/puresol/coding/lang/fortran/samples/EmptySubroutine.f"));
    }

    @Test
    public void testZGERC() throws Throwable {
	test(new File(
		"src/test/resources/com/puresol/coding/lang/fortran/samples/zgerc.f"));
    }

    @Test
    public void test2() throws Throwable {
	test(new File(
		"src/test/resources/com/puresol/coding/lang/fortran/samples/FortranTest.f"));
    }
}
