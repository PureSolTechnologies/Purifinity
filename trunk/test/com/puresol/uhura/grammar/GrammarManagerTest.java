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
		assertEquals(
				"file:/media/secured/home/ludwig/workspace/NyotaUhura/bin/com/puresol/uhura/grammar/TestGrammar.g",
				url.toString());
		assertEquals("file", url.getProtocol());
		assertEquals(
				"/media/secured/home/ludwig/workspace/NyotaUhura/bin/com/puresol/uhura/grammar/TestGrammar.g",
				url.getFile());
		assertEquals(
				"/media/secured/home/ludwig/workspace/NyotaUhura/bin/com/puresol/uhura/grammar/TestGrammar.g",
				url.getPath());
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
