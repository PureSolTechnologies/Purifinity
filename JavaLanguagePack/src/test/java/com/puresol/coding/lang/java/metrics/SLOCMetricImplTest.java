package com.puresol.coding.lang.java.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.lang.java.Java;
import com.puresol.coding.metrics.sloc.SLOCMetric;
import com.puresol.coding.metrics.sloc.SLOCResult;
import com.puresol.utils.FileUtilities;

public class SLOCMetricImplTest {

    @Test
    @Ignore
    public void test() {
	try {
	    Java java = Java.getInstance();
	    assertNotNull(java);
	    File file = new File("src/main/java", FileUtilities
		    .classToRelativePackagePath(SLOCMetricImpl.class).getPath());
	    assertTrue(file.exists());
	    Analyzer analyzer = java.createAnalyser(file);
	    assertNotNull(analyzer);
	    analyzer.parse();
	    List<CodeRange> codeRanges = analyzer.getAnalyzableCodeRanges();
	    assertNotNull(codeRanges);
	    assertTrue(codeRanges.size() > 0);
	    SLOCMetric metric = new SLOCMetric(java, codeRanges.get(2));
	    metric.run();
	    SLOCResult sloc = metric.getResult();
	    System.out.println("phyLOC: " + sloc.getPhyLOC());
	    System.out.println("proLOC: " + sloc.getProLOC());
	    System.out.println("comLOC: " + sloc.getComLOC());
	    System.out.println("blLOC: " + sloc.getBlLOC());
	    System.out.println("line length: " + sloc.getLineStatistics());
	    assertEquals(15, sloc.getPhyLOC());
	    assertEquals(13, sloc.getProLOC());
	    assertEquals(0, sloc.getComLOC());
	    assertEquals(2, sloc.getBlLOC());
	} catch (AnalyzerException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
    }
}
