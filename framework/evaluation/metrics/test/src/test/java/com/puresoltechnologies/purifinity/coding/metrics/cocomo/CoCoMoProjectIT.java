package com.puresoltechnologies.purifinity.coding.metrics.cocomo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.coding.metrics.AbstractMetricTest;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.cocomo.basic.BasicCoCoMoEvaluator;

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
