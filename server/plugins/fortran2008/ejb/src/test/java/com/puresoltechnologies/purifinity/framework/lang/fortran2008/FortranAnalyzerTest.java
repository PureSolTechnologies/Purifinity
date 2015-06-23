/**
 * Test //
 */
package com.puresoltechnologies.purifinity.framework.lang.fortran2008;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.parsers.source.SourceFileLocation;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.FortranAnalyzer;

/**
 * This test checks the JavaAnalyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranAnalyzerTest {

	@Test
	public void testInstance() {
		assertNotNull(new FortranAnalyzer(new SourceCode("", ""), null, true,
				null, null));
	}

	@Test
	public void testInitValues() throws IOException {
		SourceFileLocation sourceFileLocation = new SourceFileLocation(
				"src/test", "TestFile.f");
		try (InputStream sourceStream = sourceFileLocation.openStream()) {
			FortranAnalyzer analyser = new FortranAnalyzer(SourceCode.read(
					sourceStream, sourceFileLocation), null, true, null, null);
			assertNull(analyser.getAnalysis());
		}
	}

	private void test(File sourceDirectory, File file) throws Exception {
		SourceFileLocation sourceFileLocation = new SourceFileLocation(
				sourceDirectory, file);
		try (InputStream openStream = sourceFileLocation.openStream()) {
			FortranAnalyzer analyser = new FortranAnalyzer(SourceCode.read(
					openStream, sourceFileLocation), null, true, null, null);
			analyser.analyze();
		}
	}

	@Test
	public void testEmptyProgram() throws Exception {
		test(new File("src/test/resources"),
				new File(
						"com/puresoltechnologies/purifinity/framework/lang/fortran2008/samples/EmptyProgram.f"));
	}

	@Test
	public void testEmptySubroutine() throws Exception {
		test(new File("src/test/resources"),
				new File(
						"com/puresoltechnologies/purifinity/framework/lang/fortran2008/samples/EmptySubroutine.f"));
	}

	@Test
	public void test2() throws Exception {
		test(new File("src/test/resources"),
				new File(
						"com/puresoltechnologies/purifinity/framework/lang/fortran2008/samples/FortranTest.f"));
	}

	@Test
	public void testZGERC() throws Exception {
		test(new File("src/test/resources"),
				new File(
						"com/puresoltechnologies/purifinity/framework/lang/fortran2008/samples/zgerc.f"));
	}

	/**
	 * This checks the correct removal of empty productions for Fortran.
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testNDTRAN() throws Exception {
		test(new File("src/test/resources"),
				new File(
						"com/puresoltechnologies/purifinity/framework/lang/fortran2008/samples/ndtran.f"));
	}
}
