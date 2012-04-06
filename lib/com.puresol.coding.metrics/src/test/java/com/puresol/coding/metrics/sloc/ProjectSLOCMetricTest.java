package com.puresol.coding.metrics.sloc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.analysis.FileSystemAnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.utils.FileSearchConfiguration;

public class ProjectSLOCMetricTest {

    private AnalysisRun analyzer = null;

    @Before
    public void setup() {
	File workspaceDirectory = new File("test/analysis");
	analyzer = FileSystemAnalysisRun.create("ProjectAnalyzer",
		new File("src/main/java"), workspaceDirectory,
		new FileSearchConfiguration());
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
	assertEquals(SourceCodeQuality.UNSPECIFIED, metric.getQuality());
	assertNotNull(metric.getResults());
	assertNotNull(metric.getTimeStamp());
    }
}
