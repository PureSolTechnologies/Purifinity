package com.puresol.coding.metrics.entropy;

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
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.utils.FileSearchConfiguration;

@Ignore
public class ProjectEntropyMetricTest {

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
	assertNotNull(new EntropyEvaluator(analyzer));
    }

    @Test
    public void testInitValues() {
	EntropyEvaluator metric = new EntropyEvaluator(analyzer);
	assertSame(analyzer, metric.getAnalysisRun());
	assertFalse(metric.getName().isEmpty());
	assertNotNull(metric.getEvaluatedQualityCharacteristics());
	assertNotNull(metric.getInformation());
	assertEquals(SourceCodeQuality.UNSPECIFIED, metric.getQuality());
	assertNotNull(metric.getResults());
	assertNotNull(metric.getTimeStamp());
    }

}
