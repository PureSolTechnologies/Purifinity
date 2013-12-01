package com.puresoltechnologies.purifinity.coding.metrics.mccabe;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mockito;

import com.puresoltechnologies.purifinity.coding.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.lang.test.TestLanguage;
import com.puresoltechnologies.purifinity.coding.metrics.mccabe.McCabeMetric;
import com.puresoltechnologies.purifinity.uhura.ust.terminal.Operand;

public class McCabeMetricTest {

	@Test
	public void testInstance() {
		AnalysisRun analysisRun = Mockito.mock(AnalysisRun.class);
		assertNotNull(new McCabeMetric(analysisRun, TestLanguage.getInstance(),
				new CodeRange("FILE", "FILE", CodeRangeType.FILE, new Operand(
						"FILE", "FILE", 1, 0))));
	}

}
