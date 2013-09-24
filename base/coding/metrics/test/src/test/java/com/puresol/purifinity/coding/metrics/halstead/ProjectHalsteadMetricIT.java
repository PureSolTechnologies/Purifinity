package com.puresol.purifinity.coding.metrics.halstead;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresol.commons.utils.FileSearchConfiguration;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresol.purifinity.coding.metrics.AbstractMetricTest;

public class ProjectHalsteadMetricIT extends AbstractMetricTest {

	private AnalysisRun analyzer = null;

	public ProjectHalsteadMetricIT() {
		super(new File("src/test/java"), new FileSearchConfiguration());
	}

	@Before
	public void createAnalyzer() throws AnalysisStoreException,
			InterruptedException {
		analyzer = getAnalysisProject().createAnalysisRun();
	}

	@Test
	public void testInstance() {
		assertNotNull(new HalsteadMetricEvaluator(analyzer, null));
	}

	@Test
	public void testInitValues() {
		HalsteadMetricEvaluator metric = new HalsteadMetricEvaluator(analyzer,
				null);
		assertSame(analyzer, metric.getAnalysisRun());
		assertNotNull(metric.getInformation());
		assertNotNull(metric.getEvaluatedQualityCharacteristics());
		assertNotNull(metric.getStartTime());
	}
}
