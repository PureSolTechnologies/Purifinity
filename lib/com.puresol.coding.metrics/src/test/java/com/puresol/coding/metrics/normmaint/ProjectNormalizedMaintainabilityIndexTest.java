package com.puresol.coding.metrics.normmaint;

import static org.junit.Assert.assertEquals;
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
import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.DirectoryStoreException;
import com.puresol.coding.quality.api.SourceCodeQuality;
import com.puresol.utils.FileSearchConfiguration;

@Ignore
public class ProjectNormalizedMaintainabilityIndexTest {

    private AnalysisRun analyzer = null;

    @Before
    public void setup() throws DirectoryStoreException {
	File runDirectory = new File("test/analysis");
	AnalysisInformation analysisInformation = new AnalysisInformation(
		UUID.randomUUID(), "ProjectAnalyzer", "description", new Date());
	analyzer = AnalysisRunImpl.create(runDirectory, analysisInformation,
		UUID.randomUUID(), new File("src/main/java"),
		new FileSearchConfiguration());
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
	assertEquals(SourceCodeQuality.UNSPECIFIED, metric.getQuality());
	assertNotNull(metric.getResults());
	assertNotNull(metric.getTime());
    }
}
