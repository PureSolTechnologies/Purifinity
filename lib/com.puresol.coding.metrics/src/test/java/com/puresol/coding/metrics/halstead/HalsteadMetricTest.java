package com.puresol.coding.metrics.halstead;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.lang.test.TestLanguage;
import com.puresol.uhura.parser.ParserTree;

@Ignore
public class HalsteadMetricTest {

    @Test
    public void testInstance() {
	AnalysisRun analysisRun = Mockito.mock(AnalysisRun.class);
	assertNotNull(new HalsteadMetric(analysisRun,
		TestLanguage.getInstance(), new CodeRange("FILE",
			CodeRangeType.FILE, new ParserTree("FILE"))));
    }

}
