package com.puresoltechnologies.purifinity.framework.evaluation.metrics.entropy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.AbstractMetricTest;

public class ProjectEntropyMetricIT extends AbstractMetricTest {

	private AnalysisRun analyzer = null;

	public ProjectEntropyMetricIT() {
		super(new File("src/test/java"), new FileSearchConfiguration());
	}

	@Before
	public void createAnalyzer() throws AnalysisProjectException {
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
