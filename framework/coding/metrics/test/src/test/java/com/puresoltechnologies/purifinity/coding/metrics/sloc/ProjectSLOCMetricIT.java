package com.puresoltechnologies.purifinity.coding.metrics.sloc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.coding.metrics.AbstractMetricTest;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCEvaluator;

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
