package com.puresoltechnologies.purifinity.coding.lang.java7.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.puresoltechnologies.commons.math.statistics.Statistics;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.coding.analysis.test.TestFileSearchConfiguration;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresoltechnologies.purifinity.coding.metrics.AbstractMetricTest;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCResult;

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
		assertEquals("Sample file changed!", "ab48d201610fe5431600caee2240471d"
				+ "902f517895af0d18492df69e92fc5148", slocTestSample
				.getHashId().getHash());
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

		SLOCResult result0 = results.get(0);
		assertEquals(CodeRangeType.FILE, result0.getCodeRangeType());
		assertEquals("", result0.getCodeRangeName());
		assertEquals(SourceCodeQuality.HIGH, result0.getQuality());
		SLOCMetric slocMetric0 = result0.getSLOCMetric();
		assertEquals(40, slocMetric0.getPhyLOC());
		assertEquals(9, slocMetric0.getProLOC());
		assertEquals(4, slocMetric0.getBlLOC());
		assertEquals(29, slocMetric0.getComLOC());

		Statistics lineStatistics0 = slocMetric0.getLineStatistics();
		assertEquals(40, lineStatistics0.getCount());
		assertEquals(0.0, lineStatistics0.getMin(), 1e-8);
		assertEquals(78.0, lineStatistics0.getMax(), 1e-8);
		assertEquals(23.275, lineStatistics0.getAvg(), 1e-8);
		assertEquals(7.5, lineStatistics0.getMedian(), 1e-8);
		assertEquals(27.55040834, lineStatistics0.getStdDev(), 1e-8);

		SLOCResult result1 = results.get(1);
		assertEquals(CodeRangeType.CLASS, result1.getCodeRangeType());
		assertEquals("SLOCTestSample", result1.getCodeRangeName());
		assertEquals(SourceCodeQuality.HIGH, result1.getQuality());

		SLOCResult result2 = results.get(2);
		assertEquals(CodeRangeType.CONSTRUCTOR, result2.getCodeRangeType());
		assertEquals("SLOCTestSample()", result2.getCodeRangeName());
		assertEquals(SourceCodeQuality.HIGH, result2.getQuality());

		SLOCResult result3 = results.get(3);
		assertEquals(CodeRangeType.METHOD, result3.getCodeRangeType());
		assertEquals("multiply(double,double)", result3.getCodeRangeName());
		assertEquals(SourceCodeQuality.HIGH, result3.getQuality());
	}

}
