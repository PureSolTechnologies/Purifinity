package com.puresol.coding.metrics.cocomo;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class CoCoMoTest {

	private ProjectAnalyzer analyzer = null;

	@Before
	public void setup() {
		File workspaceDirectory = new File("test/analysis");
		analyzer = ProjectAnalyzer.create(new File("src/main/java"),
				workspaceDirectory);
	}

	@Test
	public void testInstance() {
		assertNotNull(new CoCoMo(analyzer));
	}

	@Test
	public void testInitValues() {
		CoCoMo metric = new CoCoMo(analyzer);
		assertSame(analyzer, metric.getProjectAnalyzer());
		assertFalse(metric.getName().isEmpty());
		assertFalse(metric.getDescription().isEmpty());
		assertNotNull(metric.getEvaluatedQualityCharacteristics());
		assertNull(metric.getMonitor());
		assertEquals(SourceCodeQuality.UNSPECIFIED, metric.getQuality());
		assertNotNull(metric.getReport());
		assertNotNull(metric.getResults());
		assertNotNull(metric.getTimeStamp());
	}

	@Test
	public void testPersistence() {
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				CoCoMo.class).toString()
				+ ".persist");
		CoCoMo metric = new CoCoMo(analyzer);
		PersistenceTester.test(metric, file);
	}

}
