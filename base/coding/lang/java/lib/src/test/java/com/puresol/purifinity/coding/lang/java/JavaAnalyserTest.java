package com.puresol.purifinity.coding.lang.java;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.commons.utils.PathUtils;
import com.puresol.purifinity.coding.lang.java.ust.CompilationUnitCreator;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.source.SourceFileLocation;
import com.puresol.purifinity.uhura.ust.USTCreatorFactory;

public class JavaAnalyserTest {

	@BeforeClass
	public static void initialize() {
		USTCreatorFactory.initialize();
		USTCreatorFactory.register(CompilationUnitCreator.class.getPackage());
	}

	@AfterClass
	public static void destroy() {
		USTCreatorFactory.destroy();
	}

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
		ParserTree tree = analyser.getAnalysis().getParserTree();
		assertNotNull(tree);
		// new TreePrinter(System.out).println(tree);
	}
}
