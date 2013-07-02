package com.puresol.purifinity.coding.metrics.normmaint;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.puresol.commons.utils.FileSearchConfiguration;
import com.puresol.purifinity.coding.analysis.api.AnalysisProjectInformation;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalysisRunFactory;
import com.puresol.purifinity.coding.analysis.api.DirectoryRepositoryLocation;
import com.puresol.purifinity.coding.analysis.api.DirectoryStoreException;

@Ignore
public class ProjectNormalizedMaintainabilityIndexTest {

	private AnalysisRun analyzer = null;

	@Before
	public void setup() throws DirectoryStoreException {
		File runDirectory = new File("test/analysis");
		AnalysisProjectInformation analysisInformation = new AnalysisProjectInformation(
				UUID.randomUUID(), new Date());
		analyzer = AnalysisRunFactory.getInstance().create(
				runDirectory,
				analysisInformation,
				UUID.randomUUID(),
				new DirectoryRepositoryLocation(
						"ProjectNormalizedMaintainabilityIndexTest", new File(
								"src/main/java")),
				new FileSearchConfiguration());
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
