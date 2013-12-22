package com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.AbstractMetricTest;

public class ProjectMaintainabilityIndexIT extends AbstractMetricTest {

	private AnalysisRun analyzer = null;

	public ProjectMaintainabilityIndexIT() {
		super(new File("src/test/java"), new FileSearchConfiguration());
	}

	@Before
	public void createAnalyzer() throws AnalysisProjectException {
		analyzer = getAnalysisProject().createAnalysisRun();
	}

	@Test
	public void testInstance() {
		assertNotNull(new MaintainabilityIndexEvaluator(analyzer, null));
	}

	@Test
	public void testInitValues() {
		MaintainabilityIndexEvaluator metric = new MaintainabilityIndexEvaluator(
				analyzer, null);
		assertSame(analyzer, metric.getAnalysisRun());
		assertNotNull(metric.getInformation());
		assertNotNull(metric.getEvaluatedQualityCharacteristics());
		assertNotNull(metric.getStartTime());
	}
}
