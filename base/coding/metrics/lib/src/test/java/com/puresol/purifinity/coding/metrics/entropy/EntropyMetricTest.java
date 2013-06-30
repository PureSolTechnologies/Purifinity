package com.puresol.purifinity.coding.metrics.entropy;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.lang.test.TestLanguage;
import com.puresol.purifinity.coding.metrics.entropy.EntropyMetric;
import com.puresol.purifinity.uhura.parser.ParserTree;

@Ignore
public class EntropyMetricTest {

    @Test
    public void testInstance() {
	AnalysisRun analysisRun = Mockito.mock(AnalysisRun.class);
	assertNotNull(new EntropyMetric(analysisRun,
		TestLanguage.getInstance(), new CodeRange("FILE", "FILE",
			CodeRangeType.FILE, new ParserTree("FILE"))));
    }
}
