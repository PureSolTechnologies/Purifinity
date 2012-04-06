package com.puresol.coding.metrics.codedepth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.analysis.AnalysisRunImpl;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.utils.FileSearchConfiguration;

public class ProjectCodeDepthMetricTest {

    private AnalysisRun analyzer = null;

    @Before
    public void setup() {
	File workspaceDirectory = new File("test/analysis");
	analyzer = AnalysisRunImpl.create("ProjectAnalyzer",
		new File("src/main/java"), workspaceDirectory,
		new FileSearchConfiguration());
    }

    @Test
    public void testInstance() {
	assertNotNull(new ProjectCodeDepthMetric(analyzer));
    }

    @Test
    public void testInitValues() {
	ProjectCodeDepthMetric metric = new ProjectCodeDepthMetric(analyzer);
	assertSame(analyzer, metric.getProjectAnalyzer());
	assertFalse(metric.getName().isEmpty());
	assertFalse(metric.getDescription().isEmpty());
	assertNotNull(metric.getEvaluatedQualityCharacteristics());
	assertEquals(SourceCodeQuality.UNSPECIFIED, metric.getQuality());
	assertNotNull(metric.getResults());
	assertNotNull(metric.getTimeStamp());
    }
}
