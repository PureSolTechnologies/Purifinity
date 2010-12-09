package com.puresol.coding.lang.java.metrics;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.lang.java.Java;
import com.puresol.coding.metrics.sloc.SLOCMetric;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.utils.FileUtilities;

public class SLOCMetricImplTest {

	@Test
	public void test() {
		try {
			Java java = Java.getInstance();
			assertNotNull(java);
			File file = new File("src", FileUtilities
					.classToRelativePackagePath(SLOCMetricImpl.class).getPath());
			assertTrue(file.exists());
			Analyzer analyzer = java.createAnalyser(file);
			assertNotNull(analyzer);
			analyzer.parse();
			ParserTree tree = analyzer.getParserTree();
			assertNotNull(tree);
			List<CodeRange> codeRanges = java.getAnalyzableCodeRanges(tree);
			assertNotNull(codeRanges);
			assertTrue(codeRanges.size() > 0);
			SLOCMetric metric = new SLOCMetric(java, codeRanges.get(2));
			metric.run();
			System.out.println("phyLOC: " + metric.getPhyLOC());
			System.out.println("proLOC: " + metric.getProLOC());
			System.out.println("comLOC: " + metric.getComLOC());
			System.out.println("blLOC: " + metric.getBlLOC());
			System.out.println("line length: " + metric.getLineStatistics());
			assertEquals(15, metric.getPhyLOC());
			assertEquals(13, metric.getProLOC());
			assertEquals(0, metric.getComLOC());
			assertEquals(2, metric.getBlLOC());
		} catch (AnalyzerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
