package com.puresol.coding.lang.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.PathUtils;

public class JavaAnalyserTest {

    @Test
    public void testInstance() {
	assertNotNull(new JavaAnalyzer(new File("")));
    }

    @Test
    public void testInitValues() {
	File file = new File("src/test/java", PathUtils
		.classToRelativePackagePath(this.getClass()).toString());
	assertTrue(file.exists());
	JavaAnalyzer analyser = new JavaAnalyzer(file);
	assertEquals(file, analyser.getFile());
	assertNull(analyser.getAnalysis());
	assertSame(Java.getInstance(), analyser.getLanguage());
    }

    @Test
    public void testParse() throws Throwable {
	File file = new File("src/test/java", PathUtils
		.classToRelativePackagePath(this.getClass()).toString());
	assertTrue(file.exists());
	JavaAnalyzer analyser = new JavaAnalyzer(file);
	analyser.analyze();
	ParserTree tree = analyser.getAnalysis().getParserTree();
	assertNotNull(tree);
	// new TreePrinter(System.out).println(tree);
    }
}
