package com.puresoltechnologies.purifinity.server.plugin.java7.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;

import com.puresoltechnologies.commons.math.statistics.Statistics;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCMetric;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.sloc.SLOCResult;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.FileStoreService;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.common.AnalysisRunner;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.server.test.AbstractMetricTest;
import com.puresoltechnologies.purifinity.server.test.analysis.TestFileSearchConfiguration;

@Ignore("Storage is not available during test, yet.")
public class SLOCEvaluatorIT extends AbstractMetricTest {

	private static final File testProjectDir = new File(
			"src/test/resources/test_project");

	@Inject
	private AnalysisStoreService analysisStore;

	@Inject
	private FileStoreService fileStore;

	public SLOCEvaluatorIT() {
		super(testProjectDir, new TestFileSearchConfiguration());
	}

	@Test
	public void test() throws Exception {
		AnalysisRun analysisRun = performAnalysis();
		AnalysisFileTree slocTestSample = findSLOCTestSample(analysisRun);
		assertEquals("Sample file changed!", "ab48d201610fe5431600caee2240471d"
				+ "902f517895af0d18492df69e92fc5148", slocTestSample
				.getHashId().getHash());
		SLOCFileResults fileResults = performSLOCEvaluation(analysisRun,
				slocTestSample);
		checkEvaluationResults(fileResults);
	}

	private AnalysisRun performAnalysis() throws AnalysisStoreException,
			InterruptedException, Exception {
		AnalysisProjectInformation analysisProject = getAnalysisProject();
		AnalysisRunner analysisRunner = new AnalysisRunner(
				analysisStore, fileStore, analysisProject.getUUID());
		assertTrue("Analysis run did not succeed.", analysisRunner.call());
		AnalysisRun analysisRun = analysisRunner.getAnalysisRun();
		List<AnalysisInformation> analyzedFiles = analysisRun
				.getAnalyzedFiles();
		assertEquals(1, analyzedFiles.size());
		return analysisRun;
	}

	private AnalysisFileTree findSLOCTestSample(AnalysisRun analysisRun) {
		AnalysisFileTree slocTestSample = findSample(analysisRun.getFileTree());
		assertNotNull("SLOCTestSample not found.", slocTestSample);
		return slocTestSample;
	}

	private AnalysisFileTree findSample(AnalysisFileTree node) {
		for (AnalysisFileTree child : node.getChildren()) {
			if ("SLOCTestSample.java".equals(child.getName())) {
				return child;
			}
			if (node.hasChildren()) {
				AnalysisFileTree sample = findSample(child);
				if (sample != null) {
					return sample;
				}
			}
		}
		return null;
	}

	private SLOCFileResults performSLOCEvaluation(AnalysisRun analysisRun,
			AnalysisFileTree slocTestSample) throws InterruptedException,
			EvaluationStoreException {
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
