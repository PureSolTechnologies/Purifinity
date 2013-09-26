package com.puresol.purifinity.coding.lang.java.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguages;
import com.puresol.purifinity.coding.analysis.test.TestFileSearchConfiguration;
import com.puresol.purifinity.coding.lang.java.Java;
import com.puresol.purifinity.coding.metrics.AbstractMetricTest;

public class SLOCEvaluatorIT extends AbstractMetricTest {

	private static final File testProjectDir = new File(
			"src/test/resources/test_project");

	public SLOCEvaluatorIT() {
		super(testProjectDir, new TestFileSearchConfiguration());
	}

	@Test
	public void test() throws Exception {
		List<AnalyzableProgrammingLanguage> languages = ProgrammingLanguages
				.createInstance().getAll();
		assertNotNull(languages);
		assertTrue("No programming languages found!", languages.size() > 0);
		assertTrue(languages.contains(Java.getInstance()));

		AnalysisRun analysisRun = getAnalysisProject().createAnalysisRun();
		Boolean result = analysisRun.call();
		assertTrue(result);
		List<AnalyzedCode> analyzedFiles = analysisRun.getAnalyzedFiles();
		assertEquals(1, analyzedFiles.size());

	}
}
