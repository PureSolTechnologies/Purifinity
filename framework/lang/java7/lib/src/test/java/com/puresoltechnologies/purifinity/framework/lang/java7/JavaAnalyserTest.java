package com.puresoltechnologies.purifinity.framework.lang.java7;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.impl.source.SourceFileLocation;
import com.puresoltechnologies.purifinity.framework.commons.utils.PathUtils;
import com.puresoltechnologies.purifinity.framework.lang.java7.Java;
import com.puresoltechnologies.purifinity.framework.lang.java7.JavaAnalyzer;

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