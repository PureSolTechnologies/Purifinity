package com.puresol.coding.metrics.halstead;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.ProjectAnalyzerFactory;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class ProjectHalsteadMetricTest {

    private ProjectAnalyzer analyzer = null;

    @Before
    public void setup() {
	File workspaceDirectory = new File("test/analysis");
	analyzer = ProjectAnalyzerFactory.create(new File("src/main/java"),
		workspaceDirectory);
    }

    @Test
    public void testInstance() {
	assertNotNull(new ProjectHalsteadMetric(analyzer));
    }

    @Test
    public void testInitValues() {
	ProjectHalsteadMetric metric = new ProjectHalsteadMetric(analyzer);
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
		ProjectHalsteadMetric.class).toString()
		+ ".persist");
	ProjectHalsteadMetric metric = new ProjectHalsteadMetric(analyzer);
	PersistenceTester.test(metric, file);
    }

}
