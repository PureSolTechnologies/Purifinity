package com.puresoltechnologies.purifinity.framework.evaluation.metrics.mccabe;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mockito;

import com.puresoltechnologies.parsers.impl.ust.terminal.Operand;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.mccabe.McCabeMetric;
import com.puresoltechnologies.purifinity.framework.lang.test.TestLanguage;

public class McCabeMetricTest {

	@Test
	public void testInstance() {
		AnalysisRun analysisRun = Mockito.mock(AnalysisRun.class);
		assertNotNull(new McCabeMetric(analysisRun, TestLanguage.getInstance(),
				new CodeRange("FILE", "FILE", CodeRangeType.FILE, new Operand(
						"FILE", "FILE", 1, 0))));
	}

}
