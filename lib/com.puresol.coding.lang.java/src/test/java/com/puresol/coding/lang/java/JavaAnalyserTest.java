package com.puresol.coding.lang.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Test;

import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.PathUtils;

public class JavaAnalyserTest {

    @Test
    public void testInstance() {
	assertNotNull(new JavaAnalyzer(new File(""), new File("")));
    }

    @Test
    public void testInitValues() {
	JavaAnalyzer analyser = new JavaAnalyzer(new File("src/test/java"),
		PathUtils.classToRelativePackagePath(this.getClass()));
	assertNull(analyser.getAnalysis());
	assertSame(Java.getInstance(), analyser.getLanguage());
    }

    @Test
    public void testParse() throws Throwable {
	JavaAnalyzer analyser = new JavaAnalyzer(new File("src/test/java"),
		PathUtils.classToRelativePackagePath(this.getClass()));
	analyser.analyze();
	ParserTree tree = analyser.getAnalysis().getParserTree();
	assertNotNull(tree);
	// new TreePrinter(System.out).println(tree);
    }
}
