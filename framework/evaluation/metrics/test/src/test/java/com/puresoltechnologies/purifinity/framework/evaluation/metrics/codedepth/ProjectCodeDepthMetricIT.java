package com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.AbstractMetricTest;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.codedepth.CodeDepthMetricEvaluator;

public class ProjectCodeDepthMetricIT extends AbstractMetricTest {

	private AnalysisRun analyzer = null;

	public ProjectCodeDepthMetricIT() {
		super(new File("src/test/java"), new FileSearchConfiguration());
	}

	@Before
	public void createAnalyzer() throws AnalysisStoreException,
			InterruptedException {
		analyzer = getAnalysisProject().createAnalysisRun();
	}

	@Test
	public void testInstance() {
		assertNotNull(new CodeDepthMetricEvaluator(analyzer, null));
	}

	@Test
	public void testInitValues() {
		CodeDepthMetricEvaluator metric = new CodeDepthMetricEvaluator(
				analyzer, null);
		assertSame(analyzer, metric.getAnalysisRun());
		assertNotNull(metric.getInformation());
		assertNotNull(metric.getEvaluatedQualityCharacteristics());
		assertNotNull(metric.getStartTime());
	}
}
