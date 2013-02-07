package com.puresol.coding.metrics.maintainability;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.analysis.impl.AnalysisInformation;
import com.puresol.coding.analysis.impl.AnalysisRun;
import com.puresol.coding.analysis.impl.AnalysisRunImpl;
import com.puresol.coding.analysis.impl.DirectoryRepositoryLocation;
import com.puresol.coding.analysis.impl.ModuleStoreException;
import com.puresol.utils.FileSearchConfiguration;

@Ignore("We do not have a bundle context during test!")
public class ProjectMaintainabilityIndexTest {

    private AnalysisRun analyzer = null;

    @Before
    public void setup() throws ModuleStoreException {
	File runDirectory = new File("test/analysis");
	AnalysisInformation analysisInformation = new AnalysisInformation(
		UUID.randomUUID(), "ProjectAnalyzer", "description", new Date());
	analyzer = AnalysisRunImpl.create(runDirectory, analysisInformation,
		UUID.randomUUID(), new DirectoryRepositoryLocation(
			"ProjectMaintainabilityIndexTest", new File(
				"src/main/java")),
		new FileSearchConfiguration());
    }

    @Test
    public void testInstance() {
	assertNotNull(new MaintainabilityIndexEvaluator(analyzer));
    }

    @Test
    public void testInitValues() {
	MaintainabilityIndexEvaluator metric = new MaintainabilityIndexEvaluator(
		analyzer);
	assertSame(analyzer, metric.getAnalysisRun());
	assertFalse(metric.getName().isEmpty());
	assertNotNull(metric.getInformation());
	assertNotNull(metric.getEvaluatedQualityCharacteristics());
	assertNotNull(metric.getStartTime());
    }
}
