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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Ignore;
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

    private void test(File file) throws Throwable {
	assertTrue(file.exists());
	Logger.getRootLogger().setLevel(Level.DEBUG);
	FortranAnalyser analyser = new FortranAnalyser(file);
	analyser.parse();
    }

    @Test
    @Ignore
    public void testEmptyProgram() throws Throwable {
	test(new File(
		"src/test/resources/com/puresol/coding/lang/fortran/samples/EmptyProgram.f"));
    }

    @Test
    @Ignore
    public void testEmptySubroutine() throws Throwable {
	Logger.getRootLogger().setLevel(Level.TRACE);
	test(new File(
		"src/test/resources/com/puresol/coding/lang/fortran/samples/EmptySubroutine.f"));
    }

    @Test
    @Ignore
    public void testZGERC() throws Throwable {
	test(new File(
		"src/test/resources/com/puresol/coding/lang/fortran/samples/zgerc.f"));
    }

    @Test
    @Ignore
    public void test2() throws Throwable {
	test(new File(
		"src/test/resources/com/puresol/coding/lang/fortran/samples/FortranTest.f"));
    }
}
