package com.puresol.purifinity.coding.metrics.sloc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresol.commons.utils.FileSearchConfiguration;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresol.purifinity.coding.metrics.AbstractMetricTest;

public class ProjectSLOCMetricIT extends AbstractMetricTest {

	public ProjectSLOCMetricIT() {
		super(new File("src/test/java"), new FileSearchConfiguration());
	}

	private AnalysisRun analyzer = null;

	@Before
	public void createAnalyzer() throws AnalysisStoreException,
			InterruptedException {
		analyzer = getAnalysisProject().createAnalysisRun();
	}

	@Test
	public void testInstance() {
		assertNotNull(new SLOCEvaluator(analyzer, null));
	}

	@Test
	public void testInitValues() {
		SLOCEvaluator metric = new SLOCEvaluator(analyzer, null);
		assertSame(analyzer, metric.getAnalysisRun());
		assertNotNull(metric.getInformation());
		assertNotNull(metric.getEvaluatedQualityCharacteristics());
		assertNotNull(metric.getStartTime());
	}
}
