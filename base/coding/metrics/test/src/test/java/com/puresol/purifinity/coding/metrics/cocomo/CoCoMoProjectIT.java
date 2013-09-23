package com.puresol.purifinity.coding.metrics.cocomo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresol.commons.utils.FileSearchConfiguration;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalysisStoreException;
import com.puresol.purifinity.coding.metrics.AbstractMetricTest;

public class CoCoMoProjectIT extends AbstractMetricTest {

	private AnalysisRun analyzer = null;

	public CoCoMoProjectIT() {
		super(new File("test/analysis"), new FileSearchConfiguration());
	}

	@Before
	public void createanalyzer() throws AnalysisStoreException,
			InterruptedException {
		analyzer = getAnalysisProject().createAnalysisRun();
	}

	@Test
	public void testInstance() {
		assertNotNull(new CoCoMoEvaluator(analyzer, null));
	}

	@Test
	public void testInitValues() {
		CoCoMoEvaluator metric = new CoCoMoEvaluator(analyzer, null);
		assertSame(analyzer, metric.getAnalysisRun());
		assertNotNull(metric.getInformation());
		assertNotNull(metric.getEvaluatedQualityCharacteristics());
		assertNotNull(metric.getStartTime());
	}

}
