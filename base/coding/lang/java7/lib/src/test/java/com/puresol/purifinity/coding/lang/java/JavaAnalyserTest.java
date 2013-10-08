package com.puresol.purifinity.coding.lang.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresol.commons.utils.PathUtils;
import com.puresol.purifinity.coding.lang.java7.Java;
import com.puresol.purifinity.coding.lang.java7.JavaAnalyzer;
import com.puresol.purifinity.uhura.source.SourceFileLocation;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;

public class JavaAnalyserTest {

	@Test
	public void testInstance() {
		assertNotNull(new JavaAnalyzer(new SourceFileLocation("", "")));
	}

	@Test
	public void testInitValues() {
		JavaAnalyzer analyser = new JavaAnalyzer(new SourceFileLocation(
				"src/test/java", PathUtils.classToRelativePackagePath(
						this.getClass()).getPath()));
		assertNull(analyser.getAnalysis());
		assertSame(Java.getInstance(), analyser.getLanguage());
	}

	@Test
	public void testParse() throws Throwable {
		JavaAnalyzer analyser = new JavaAnalyzer(new SourceFileLocation(
				"src/test/java", PathUtils.classToRelativePackagePath(
						this.getClass()).getPath()));
		analyser.analyze();
		UniversalSyntaxTree tree = analyser.getAnalysis()
				.getUniversalSyntaxTree();
		assertNotNull(tree);
		// new TreePrinter(System.out).println(tree);
	}
}
