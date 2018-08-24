package com.puresoltechnologies.purifinity.framework.lang.java7;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.puresoltechnologies.commons.misc.io.PathUtils;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.parsers.source.SourceFileLocation;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.server.plugin.java7.JavaAnalyzer;

public class JavaAnalyserTest {

	@Test
	public void testInstance() {
		assertNotNull(new JavaAnalyzer(new SourceCode("", ""), null));
	}

	@Test
	public void testInitValues() throws IOException {
		SourceFileLocation sourceFileLocation = new SourceFileLocation(
				"src/test/java", PathUtils.classToRelativePackagePath(
						this.getClass()).getPath());
		try (InputStream sourceStream = sourceFileLocation.openStream()) {
			JavaAnalyzer analyser = new JavaAnalyzer(SourceCode.read(
					sourceStream, sourceFileLocation), null);
			assertNull(analyser.getAnalysis());
		}
	}

	@Test
	public void testParse() throws Throwable {
		String filePath = PathUtils.classToRelativePackagePath(this.getClass())
				.getPath();
		String directory = "src/test/java";
		SourceFileLocation sourceFileLocation = new SourceFileLocation(
				directory, filePath);
		try (InputStream sourceStream = sourceFileLocation.openStream()) {
			JavaAnalyzer analyser = new JavaAnalyzer(SourceCode.read(
					sourceStream, sourceFileLocation), null);
			analyser.analyze();
			UniversalSyntaxTree tree = analyser.getAnalysis()
					.getUniversalSyntaxTree();
			assertNotNull(tree);
			// new TreePrinter(System.out).println(tree);
		}
	}
}
