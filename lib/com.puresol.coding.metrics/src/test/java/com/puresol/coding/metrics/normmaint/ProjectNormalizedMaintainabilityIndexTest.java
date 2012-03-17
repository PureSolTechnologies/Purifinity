package com.puresol.coding.metrics.normmaint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.ProjectAnalyzerFactory;
import com.puresol.coding.quality.SourceCodeQuality;
import com.puresol.utils.FileSearchConfiguration;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class ProjectNormalizedMaintainabilityIndexTest {

    private ProjectAnalyzer analyzer = null;

    @Before
    public void setup() {
	File workspaceDirectory = new File("test/analysis");
	analyzer = ProjectAnalyzerFactory.create(new File("src/main/java"),
		workspaceDirectory, new FileSearchConfiguration());
    }

    @Test
    public void testInstance() {
	assertNotNull(new ProjectNormalizedMaintainabilityIndex(analyzer));
    }

    @Test
    public void testInitValues() {
	ProjectNormalizedMaintainabilityIndex metric = new ProjectNormalizedMaintainabilityIndex(
		analyzer);
	assertSame(analyzer, metric.getProjectAnalyzer());
	assertFalse(metric.getName().isEmpty());
	assertFalse(metric.getDescription().isEmpty());
	assertNotNull(metric.getEvaluatedQualityCharacteristics());
	assertEquals(SourceCodeQuality.UNSPECIFIED, metric.getQuality());
	assertNotNull(metric.getResults());
	assertNotNull(metric.getTimeStamp());
    }

    @Test
    public void testPersistence() {
	File file = new File("test", FileUtilities.classToRelativePackagePath(
		ProjectNormalizedMaintainabilityIndex.class).toString()
		+ ".persist");
	ProjectNormalizedMaintainabilityIndex metric = new ProjectNormalizedMaintainabilityIndex(
		analyzer);
	PersistenceTester.test(metric, file);
    }

}
