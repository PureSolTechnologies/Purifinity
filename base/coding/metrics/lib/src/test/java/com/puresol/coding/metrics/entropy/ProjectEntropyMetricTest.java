package com.puresol.coding.metrics.entropy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.analysis.api.AnalysisProjectInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunFactory;
import com.puresol.coding.analysis.api.DirectoryRepositoryLocation;
import com.puresol.coding.analysis.api.DirectoryStoreException;
import com.puresol.utils.FileSearchConfiguration;

@Ignore
public class ProjectEntropyMetricTest {

    private AnalysisRun analyzer = null;

    @Before
    public void setup() throws DirectoryStoreException {
	File runDirectory = new File("test/analysis");
	AnalysisProjectInformation analysisInformation = new AnalysisProjectInformation(
		UUID.randomUUID(), new Date());
	analyzer = AnalysisRunFactory.getInstance().create(
		runDirectory,
		analysisInformation,
		UUID.randomUUID(),
		new DirectoryRepositoryLocation("ProjectEntropyMetricTest",
			new File("src/main/java")),
		new FileSearchConfiguration());
    }

    @Test
    public void testInstance() {
	assertNotNull(new EntropyEvaluator(analyzer, null));
    }

    @Test
    public void testInitValues() {
	EntropyEvaluator metric = new EntropyEvaluator(analyzer, null);
	assertSame(analyzer, metric.getAnalysisRun());
	assertNotNull(metric.getEvaluatedQualityCharacteristics());
	assertNotNull(metric.getInformation());
	assertNotNull(metric.getStartTime());
    }

}
