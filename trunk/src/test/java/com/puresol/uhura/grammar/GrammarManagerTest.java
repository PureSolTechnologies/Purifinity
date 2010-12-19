package com.puresol.uhura.grammar;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.puresol.utils.FileUtilities;

public class GrammarManagerTest {

	@Test
	public void testURLHandlingForClass() {
		URL url = GrammarManagerTest.class
				.getResource("/com/puresol/uhura/grammar/TestGrammar.g");
		assertNotNull(url);
		assertTrue(url.toString().endsWith(
				"/com/puresol/uhura/grammar/TestGrammar.g"));
		assertEquals("file", url.getProtocol());
	}

	@Test
	public void testTimeHandlingForGrammarModified() {
		try {
			File directory = new File("test/", FileUtilities
					.classToRelativePackagePath(getClass()).getParentFile()
					.toString());
			assertEquals("test/com/puresol/uhura/grammar", directory.toString());
			File file = new File(directory, "test.test");
			assertFalse(file.exists());
			assertTrue(file.createNewFile());
			long modified = file.lastModified();
			long currentTime = System.currentTimeMillis();
			assertTrue(modified <= currentTime);
			assertTrue(file.delete());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
