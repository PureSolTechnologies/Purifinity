package com.puresol.coding.metrics.mccabe;

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
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.utils.FileSearchConfiguration;

@Ignore
public class ProjectMcCabeMetricTest {

    private AnalysisRun analyzer = null;

    @Before
    public void setup() throws ModuleStoreException {
	File runDirectory = new File("test/analysis");
	AnalysisProjectInformation analysisInformation = new AnalysisProjectInformation(
		UUID.randomUUID(), new Date());
	analyzer = AnalysisRunFactory.getInstance().create(
		runDirectory,
		analysisInformation,
		UUID.randomUUID(),
		new DirectoryRepositoryLocation("ProjectMcCabeMetricTest",
			new File("src/main/java")),
		new FileSearchConfiguration());
    }

    @Test
    public void testInstance() {
	assertNotNull(new McCabeMetricEvaluator(analyzer));
    }

    @Test
    public void testInitValues() {
	McCabeMetricEvaluator metric = new McCabeMetricEvaluator(analyzer);
	assertSame(analyzer, metric.getAnalysisRun());
	assertNotNull(metric.getInformation());
	assertNotNull(metric.getEvaluatedQualityCharacteristics());
	assertNotNull(metric.getStartTime());
    }
}
