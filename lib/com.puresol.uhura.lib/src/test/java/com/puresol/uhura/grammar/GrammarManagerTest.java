package com.puresol.uhura.grammar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.junit.Ignore;
import org.junit.Test;

import com.puresol.utils.PathUtils;

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
    @Ignore
    public void testTimeHandlingForGrammarModified() throws Throwable {
	File directory = new File("test/", PathUtils
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
    }

}
