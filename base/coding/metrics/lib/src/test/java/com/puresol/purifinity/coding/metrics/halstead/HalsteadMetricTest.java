package com.puresol.purifinity.coding.metrics.halstead;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.lang.test.TestLanguage;
import com.puresol.purifinity.coding.metrics.halstead.HalsteadMetric;
import com.puresol.purifinity.uhura.parser.ParserTree;

@Ignore
public class HalsteadMetricTest {

    @Test
    public void testInstance() {
	AnalysisRun analysisRun = Mockito.mock(AnalysisRun.class);
	assertNotNull(new HalsteadMetric(analysisRun,
		TestLanguage.getInstance(), new CodeRange("FILE", "FILE",
			CodeRangeType.FILE, new ParserTree("FILE"))));
    }

}