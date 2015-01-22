package com.puresoltechnologies.purifinity.framework.lang.java7;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.io.PathUtils;
import com.puresoltechnologies.parsers.source.SourceFileLocation;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.server.plugin.java7.JavaAnalyzer;

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
