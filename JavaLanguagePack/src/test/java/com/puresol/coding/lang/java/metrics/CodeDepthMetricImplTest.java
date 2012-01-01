package com.puresol.coding.lang.java.metrics;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.lang.java.Java;
import com.puresol.coding.metrics.codedepth.CodeDepthMetric;
import com.puresol.utils.FileUtilities;

public class CodeDepthMetricImplTest {

	@Test
	public void test() throws Throwable {
		Java java = Java.getInstance();
		assertNotNull(java);
		File file = new File("src/main/java", FileUtilities
				.classToRelativePackagePath(CodeDepthMetricImpl.class)
				.getPath());
		assertTrue(file.exists());
		Analyzer analyzer = java.createAnalyser(file);
		assertNotNull(analyzer);
		analyzer.parse();
		List<CodeRange> codeRanges = analyzer.getAnalyzableCodeRanges();
		assertNotNull(codeRanges);
		assertTrue(codeRanges.size() > 0);
		CodeDepthMetric metric = new CodeDepthMetric(java, codeRanges.get(0));
		metric.run();
		assertEquals(2, metric.getMaxDepth());
	}
}
