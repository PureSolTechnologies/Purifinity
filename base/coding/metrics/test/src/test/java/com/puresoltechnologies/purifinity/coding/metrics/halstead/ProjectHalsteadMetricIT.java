package com.puresoltechnologies.purifinity.coding.metrics.halstead;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.utils.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.coding.metrics.AbstractMetricTest;
import com.puresoltechnologies.purifinity.coding.metrics.halstead.HalsteadMetricEvaluator;

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
