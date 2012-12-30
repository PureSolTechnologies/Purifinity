package com.puresol.coding.metrics.halstead;

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
import com.puresol.utils.FileSearchConfiguration;

@Ignore
public class ProjectHalsteadMetricTest {

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
	assertNotNull(new HalsteadMetricEvaluator(analyzer));
    }

    @Test
    public void testInitValues() {
	HalsteadMetricEvaluator metric = new HalsteadMetricEvaluator(analyzer);
	assertSame(analyzer, metric.getAnalysisRun());
	assertFalse(metric.getName().isEmpty());
	assertNotNull(metric.getInformation());
	assertNotNull(metric.getEvaluatedQualityCharacteristics());
	assertNotNull(metric.getTime());
    }
}