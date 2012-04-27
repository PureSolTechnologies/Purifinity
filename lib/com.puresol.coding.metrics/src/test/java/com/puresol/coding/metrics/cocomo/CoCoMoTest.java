package com.puresol.coding.metrics.cocomo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.puresol.coding.analysis.AnalysisRunImpl;
import com.puresol.coding.analysis.api.AnalysisInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.DirectoryStoreException;
import com.puresol.utils.FileSearchConfiguration;

@Ignore
public class CoCoMoTest {

    private AnalysisRun analyzer = null;

    @Before
    public void setup() throws DirectoryStoreException {
	File runDirectory = new File("test/analysis");
	AnalysisInformation analysisInformation = new AnalysisInformation(
		UUID.randomUUID(), "ProjectAnalyzer", "description", new Date());
	analyzer = AnalysisRunImpl.create(runDirectory, analysisInformation,
		UUID.randomUUID(), new File("src/main/java"),
		new FileSearchConfiguration());
    }

    @Test
    public void testInstance() {
	assertNotNull(new CoCoMo(analyzer));
    }

    @Test
    public void testInitValues() {
	CoCoMo metric = new CoCoMo(analyzer);
	assertSame(analyzer, metric.getAnalysisRun());
	assertFalse(metric.getName().isEmpty());
	assertNotNull(metric.getInformation());
	assertNotNull(metric.getEvaluatedQualityCharacteristics());
	assertNotNull(metric.getResults());
	assertNotNull(metric.getTime());
    }

}
