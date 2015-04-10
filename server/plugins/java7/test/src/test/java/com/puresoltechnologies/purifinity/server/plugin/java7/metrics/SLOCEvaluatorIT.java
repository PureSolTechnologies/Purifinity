package com.puresoltechnologies.purifinity.server.plugin.java7.metrics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreService;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.metrics.sloc.SLOCMetricCalculator;
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

    @Inject
    private EvaluatorStoreService evaluatorStore;

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
	GenericFileMetrics fileResults = performSLOCEvaluation(analysisRun,
		slocTestSample);
	checkEvaluationResults(fileResults);
    }

    private AnalysisRun performAnalysis() throws AnalysisStoreException,
	    InterruptedException, Exception {
	AnalysisProjectInformation analysisProject = getAnalysisProject();
	// AnalysisRunner analysisRunner = new AnalysisRunner(
	// analysisStore, fileStore, analysisProject.getUUID());
	// assertTrue("Analysis run did not succeed.", analysisRunner.call());
	// AnalysisRun analysisRun = analysisRunner.getAnalysisRun();
	// List<AnalysisInformation> analyzedFiles = analysisRun
	// .getAnalyzedFiles();
	// assertEquals(1, analyzedFiles.size());
	// return analysisRun;
	// FIXME Needs to be used via ProjectAnalysisStartQueue...
	return null;
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

    private GenericFileMetrics performSLOCEvaluation(AnalysisRun analysisRun,
	    AnalysisFileTree slocTestSample) throws InterruptedException,
	    EvaluationStoreException {
	SLOCEvaluator evaluator = new SLOCEvaluator();
	evaluator.evaluate(analysisRun, false);
	GenericFileMetrics fileResults = evaluatorStore.readFileResults(
		slocTestSample.getHashId(), SLOCMetricCalculator.ID);
	assertNotNull("No file results found.", fileResults);
	return fileResults;
    }

    private void checkEvaluationResults(GenericFileMetrics fileResults) {
	List<GenericCodeRangeMetrics> results = fileResults.getCodeRangeMetrics();
	assertNotNull("No results list was returned.", results);
	assertEquals("The number of code ranges does not match.", 4,
		results.size());

	GenericCodeRangeMetrics result0 = results.get(0);
	assertEquals(CodeRangeType.FILE, result0.getCodeRangeType());
	assertEquals("", result0.getCodeRangeName());
	assertEquals(40, result0.getValue(SLOCEvaluatorParameter.PHY_LOC)
		.getValue().intValue());
	assertEquals(9, result0.getValue(SLOCEvaluatorParameter.PRO_LOC)
		.getValue().intValue());
	assertEquals(4, result0.getValue(SLOCEvaluatorParameter.BL_LOC)
		.getValue().intValue());
	assertEquals(29, result0.getValue(SLOCEvaluatorParameter.COM_LOC)
		.getValue().intValue());

	assertEquals(0, result0.getValue(SLOCEvaluatorParameter.MIN).getValue()
		.intValue());
	assertEquals(78, result0.getValue(SLOCEvaluatorParameter.MAX)
		.getValue().intValue());
	assertEquals(23.275, result0.getValue(SLOCEvaluatorParameter.AVG)
		.getValue().intValue(), 1e-8);
	assertEquals(7.5, result0.getValue(SLOCEvaluatorParameter.MEDIAN)
		.getValue().intValue(), 1e-8);
	assertEquals(27.55040834,
		result0.getValue(SLOCEvaluatorParameter.STD_DEV).getValue()
			.intValue(), 1e-8);

	GenericCodeRangeMetrics result1 = results.get(1);
	assertEquals(CodeRangeType.CLASS, result1.getCodeRangeType());
	assertEquals("SLOCTestSample", result1.getCodeRangeName());

	GenericCodeRangeMetrics result2 = results.get(2);
	assertEquals(CodeRangeType.CONSTRUCTOR, result2.getCodeRangeType());
	assertEquals("SLOCTestSample()", result2.getCodeRangeName());

	GenericCodeRangeMetrics result3 = results.get(3);
	assertEquals(CodeRangeType.METHOD, result3.getCodeRangeType());
	assertEquals("multiply(double,double)", result3.getCodeRangeName());
    }
}
