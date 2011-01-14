package com.puresol.coding.lang.java.metrics;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.lang.java.Java;
import com.puresol.coding.metrics.codedepth.CodeDepthMetric;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.FileUtilities;

public class CodeDepthMetricImplTest {

	@Test
	public void test() {
		try {
			Java java = Java.getInstance();
			assertNotNull(java);
			File file = new File("src/main/java", FileUtilities
					.classToRelativePackagePath(CodeDepthMetricImpl.class)
					.getPath());
			assertTrue(file.exists());
			Analyzer analyzer = java.createAnalyser(file);
			assertNotNull(analyzer);
			analyzer.parse();
			ParserTree tree = analyzer.getParserTree();
			assertNotNull(tree);
			List<CodeRange> codeRanges = java.getAnalyzableCodeRanges(tree);
			assertNotNull(codeRanges);
			assertTrue(codeRanges.size() > 0);
			CodeDepthMetric metric = new CodeDepthMetric(java,
					codeRanges.get(0));
			metric.run();
			assertEquals(2, metric.getMaxDepth());
		} catch (AnalyzerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
