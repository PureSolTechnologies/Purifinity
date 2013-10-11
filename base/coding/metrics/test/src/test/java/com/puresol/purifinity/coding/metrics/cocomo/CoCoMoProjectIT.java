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
import com.puresol.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoEvaluator;

public class CoCoMoProjectIT extends AbstractMetricTest {

	private AnalysisRun analyzer = null;

	public CoCoMoProjectIT() {
		super(new File("src/test/java"), new FileSearchConfiguration());
	}

	@Before
	public void createanalyzer() throws AnalysisStoreException,
			InterruptedException {
		analyzer = getAnalysisProject().createAnalysisRun();
	}

	@Test
	public void testInstance() {
		assertNotNull(new BasicCoCoMoEvaluator(analyzer, null));
	}

	@Test
	public void testInitValues() {
		BasicCoCoMoEvaluator metric = new BasicCoCoMoEvaluator(analyzer, null);
		assertSame(analyzer, metric.getAnalysisRun());
		assertNotNull(metric.getInformation());
		assertNotNull(metric.getEvaluatedQualityCharacteristics());
		assertNotNull(metric.getStartTime());
	}

}
