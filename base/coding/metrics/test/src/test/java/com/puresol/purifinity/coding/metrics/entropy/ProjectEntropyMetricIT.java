package com.puresol.purifinity.coding.metrics.entropy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresol.commons.utils.FileSearchConfiguration;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresol.purifinity.coding.metrics.AbstractMetricTest;

public class ProjectEntropyMetricIT extends AbstractMetricTest {

	private AnalysisRun analyzer = null;

	public ProjectEntropyMetricIT() {
		super(new File("test/analysis"), new FileSearchConfiguration());
	}

	@Before
	public void createAnalyzer() throws AnalysisStoreException,
			InterruptedException {
		analyzer = getAnalysisProject().createAnalysisRun();
	}

	@Test
	public void testInstance() {
		assertNotNull(new EntropyMetricEvaluator(analyzer, null));
	}

	@Test
	public void testInitValues() {
		EntropyMetricEvaluator metric = new EntropyMetricEvaluator(analyzer,
				null);
		assertSame(analyzer, metric.getAnalysisRun());
		assertNotNull(metric.getEvaluatedQualityCharacteristics());
		assertNotNull(metric.getInformation());
		assertNotNull(metric.getStartTime());
	}

}
