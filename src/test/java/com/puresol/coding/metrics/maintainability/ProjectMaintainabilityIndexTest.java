package com.puresol.coding.metrics.maintainability;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class ProjectMaintainabilityIndexTest {

	private ProjectAnalyzer analyzer = null;

	@Before
	public void setup() {
		File workspaceDirectory = new File("test/analysis");
		analyzer = ProjectAnalyzer.create(new File("src/main/java"),
				workspaceDirectory);
	}

	@Test
	public void testInstance() {
		assertNotNull(new ProjectMaintainabilityIndex(analyzer));
	}

	@Test
	public void testInitValues() {
		ProjectMaintainabilityIndex metric = new ProjectMaintainabilityIndex(
				analyzer);
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
				ProjectMaintainabilityIndex.class).toString()
				+ ".persist");
		ProjectMaintainabilityIndex metric = new ProjectMaintainabilityIndex(
				analyzer);
		PersistenceTester.test(metric, file);
	}

}
