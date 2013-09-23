package com.puresol.purifinity.coding.lang.java.metrics;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.puresol.commons.utils.FileSearchConfiguration;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.metrics.AbstractMetricTest;

public class SLOCEvaluatorIT extends AbstractMetricTest {

	private static final File testProjectDir = new File(
			"src/test/resources/test_project");

	public SLOCEvaluatorIT() {
		super(testProjectDir, new FileSearchConfiguration());
	}

	@Test
	public void test() throws Exception {
		AnalysisRun analysisRun = getAnalysisProject().createAnalysisRun();
		Boolean result = analysisRun.call();
		assertTrue(result);
	}
}
