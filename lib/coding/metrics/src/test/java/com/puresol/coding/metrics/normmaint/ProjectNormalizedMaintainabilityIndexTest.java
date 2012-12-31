package com.puresol.coding.metrics.normmaint;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.analysis.AnalysisRunImpl;
import com.puresol.coding.analysis.DirectoryRepositoryLocation;
import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.utils.CodeSearchConfiguration;

@Ignore
public class ProjectNormalizedMaintainabilityIndexTest {

    private AnalysisRun analyzer = null;

    @Before
    public void setup() throws ModuleStoreException {
	File runDirectory = new File("test/analysis");
	AnalysisInformation analysisInformation = new AnalysisInformation(
		UUID.randomUUID(), "ProjectAnalyzer", "description", new Date());
	analyzer = AnalysisRunImpl.create(runDirectory, analysisInformation,
		UUID.randomUUID(), new DirectoryRepositoryLocation(
			"ProjectNormalizedMaintainabilityIndexTest", new File(
				"src/main/java")),
		new CodeSearchConfiguration());
    }

    @Test
    public void testInstance() {
	assertNotNull(new NormalizedMaintainabilityIndexEvaluator(analyzer));
    }

    @Test
    public void testInitValues() {
	NormalizedMaintainabilityIndexEvaluator metric = new NormalizedMaintainabilityIndexEvaluator(
		analyzer);
	assertSame(analyzer, metric.getAnalysisRun());
	assertFalse(metric.getName().isEmpty());
	assertNotNull(metric.getInformation());
	assertNotNull(metric.getEvaluatedQualityCharacteristics());
	assertNotNull(metric.getStartTime());
    }
}
