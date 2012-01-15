package com.puresol.coding.metrics.sloc;

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

public class ProjectSLOCMetricTest {

    private ProjectAnalyzer analyzer = null;

    @Before
    public void setup() {
	File workspaceDirectory = new File("test/analysis");
	analyzer = ProjectAnalyzerFactory.create(new File("src/main/java"),
		workspaceDirectory);
    }

    @Test
    public void testInstance() {
	assertNotNull(new ProjectSLOCMetric(analyzer));
    }

    @Test
    public void testInitValues() {
	ProjectSLOCMetric metric = new ProjectSLOCMetric(analyzer);
	assertSame(analyzer, metric.getProjectAnalyzer());
	assertFalse(metric.getName().isEmpty());
	assertFalse(metric.getDescription().isEmpty());
	assertNotNull(metric.getEvaluatedQualityCharacteristics());
	assertNull(metric.getMonitor());
	assertEquals(SourceCodeQuality.UNSPECIFIED, metric.getQuality());
	assertNotNull(metric.getResults());
	assertNotNull(metric.getTimeStamp());
    }

    @Test
    public void testPersistence() {
	File file = new File("test", FileUtilities.classToRelativePackagePath(
		ProjectSLOCMetric.class).toString()
		+ ".persist");
	ProjectSLOCMetric metric = new ProjectSLOCMetric(analyzer);
	PersistenceTester.test(metric, file);
    }

}
