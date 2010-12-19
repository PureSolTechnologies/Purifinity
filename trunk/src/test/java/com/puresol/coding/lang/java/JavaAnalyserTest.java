package com.puresol.coding.lang.java;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.trees.TreePrinter;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.FileUtilities;

public class JavaAnalyserTest {

	@Test
	public void test() {
		assertNotNull(new JavaAnalyser(new File("")));
	}

	@Test
	public void testInitValues() {
		try {
			File file = new File("src/test/java", FileUtilities
					.classToRelativePackagePath(this.getClass()).toString());
			assertTrue(file.exists());
			JavaAnalyser analyser = new JavaAnalyser(file);
			analyser.parse();
			ParserTree tree = analyser.getParserTree();
			new TreePrinter(System.out).println(tree);
		} catch (AnalyzerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
