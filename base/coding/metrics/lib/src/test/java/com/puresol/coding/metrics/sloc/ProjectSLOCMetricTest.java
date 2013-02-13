package com.puresol.coding.metrics.sloc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunFactory;
import com.puresol.coding.analysis.api.DirectoryRepositoryLocation;
import com.puresol.coding.analysis.api.ModuleStoreException;
import com.puresol.utils.FileSearchConfiguration;

@Ignore("We do not have a bundle context during test.")
public class ProjectSLOCMetricTest {

	private AnalysisRun analyzer = null;

	@Before
	public void setup() throws ModuleStoreException {
		File runDirectory = new File("test/analysis");
		AnalysisInformation analysisInformation = new AnalysisInformation(
				UUID.randomUUID(), "ProjectAnalyzer", "description", new Date());
		analyzer = AnalysisRunFactory.getInstance().create(
				runDirectory,
				analysisInformation,
				UUID.randomUUID(),
				new DirectoryRepositoryLocation("ProjectSLOCMetricTest",
						new File("src/main/java")),
				new FileSearchConfiguration());
	}

	@Test
	public void testInstance() {
		assertNotNull(new SLOCEvaluator(analyzer));
	}

	@Test
	public void testInitValues() {
		SLOCEvaluator metric = new SLOCEvaluator(analyzer);
		assertSame(analyzer, metric.getAnalysisRun());
		assertNotNull(metric.getInformation());
		assertNotNull(metric.getEvaluatedQualityCharacteristics());
		assertNotNull(metric.getStartTime());
	}
}
