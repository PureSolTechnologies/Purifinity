package com.puresoltechnologies.purifinity.coding.metrics.normmaint;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.coding.metrics.AbstractMetricTest;
import com.puresoltechnologies.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexEvaluator;

public class ProjectNormalizedMaintainabilityIndexIT extends AbstractMetricTest {

	public ProjectNormalizedMaintainabilityIndexIT() {
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
		assertNotNull(new NormalizedMaintainabilityIndexEvaluator(analyzer,
				null));
	}

	@Test
	public void testInitValues() {
		NormalizedMaintainabilityIndexEvaluator metric = new NormalizedMaintainabilityIndexEvaluator(
				analyzer, null);
		assertSame(analyzer, metric.getAnalysisRun());
		assertNotNull(metric.getInformation());
		assertNotNull(metric.getEvaluatedQualityCharacteristics());
		assertNotNull(metric.getStartTime());
	}
}
