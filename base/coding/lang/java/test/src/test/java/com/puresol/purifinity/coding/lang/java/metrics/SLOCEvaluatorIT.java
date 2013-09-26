package com.puresol.purifinity.coding.lang.java.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.analysis.test.TestFileSearchConfiguration;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.purifinity.coding.metrics.AbstractMetricTest;
import com.puresol.purifinity.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.purifinity.coding.metrics.sloc.SLOCFileResults;
import com.puresol.purifinity.coding.metrics.sloc.SLOCResult;

public class SLOCEvaluatorIT extends AbstractMetricTest {

	private static final File testProjectDir = new File(
			"src/test/resources/test_project");

	public SLOCEvaluatorIT() {
		super(testProjectDir, new TestFileSearchConfiguration());
	}

	@Test
	public void test() throws Exception {
		AnalysisRun analysisRun = performAnalysis();
		HashIdFileTree slocTestSample = findSLOCTestSample(analysisRun);
		SLOCFileResults fileResults = performSLOCEvaluation(analysisRun,
				slocTestSample);
		checkEvaluationResults(fileResults);
	}

	private AnalysisRun performAnalysis() throws AnalysisStoreException,
			InterruptedException, Exception {
		AnalysisRun analysisRun = getAnalysisProject().createAnalysisRun();
		assertTrue("Analysis run did not succeed.", analysisRun.call());
		List<AnalyzedCode> analyzedFiles = analysisRun.getAnalyzedFiles();
		assertEquals(1, analyzedFiles.size());
		return analysisRun;
	}

	private HashIdFileTree findSLOCTestSample(AnalysisRun analysisRun) {
		HashIdFileTree slocTestSample = findSample(analysisRun.getFileTree());
		assertNotNull("SLOCTestSample not found.", slocTestSample);
		return slocTestSample;
	}

	private HashIdFileTree findSample(HashIdFileTree node) {
		for (HashIdFileTree child : node.getChildren()) {
			if ("SLOCTestSample.java".equals(child.getName())) {
				return child;
			}
			if (node.hasChildren()) {
				HashIdFileTree sample = findSample(child);
				if (sample != null) {
					return sample;
				}
			}
		}
		return null;
	}

	private SLOCFileResults performSLOCEvaluation(AnalysisRun analysisRun,
			HashIdFileTree slocTestSample) throws InterruptedException {
		SLOCEvaluator evaluator = new SLOCEvaluator(analysisRun, slocTestSample);
		assertTrue("Evaluator call did not succeed.", evaluator.call());
		EvaluatorStore store = EvaluatorStoreFactory.getFactory()
				.createInstance(SLOCEvaluator.class);
		SLOCFileResults fileResults = (SLOCFileResults) store
				.readFileResults(slocTestSample.getHashId());
		assertNotNull("No file results found.", fileResults);
		return fileResults;
	}

	private void checkEvaluationResults(SLOCFileResults fileResults) {
		List<SLOCResult> results = fileResults.getResults();
		assertNotNull("No results list was returned.", results);
		assertEquals("The number of code ranges does not match.", 4,
				results.size());
	}

}
