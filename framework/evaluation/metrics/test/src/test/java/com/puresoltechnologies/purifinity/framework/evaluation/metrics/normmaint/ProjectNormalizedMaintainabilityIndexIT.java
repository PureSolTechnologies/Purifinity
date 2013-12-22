package com.puresoltechnologies.purifinity.framework.evaluation.metrics.normmaint;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.AbstractMetricTest;

public class ProjectNormalizedMaintainabilityIndexIT extends AbstractMetricTest {

	public ProjectNormalizedMaintainabilityIndexIT() {
		super(new File("src/test/java"), new FileSearchConfiguration());
	}

	private AnalysisRun analyzer = null;

	@Before
	public void createAnalyzer() throws AnalysisProjectException {
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
