package com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.entropy;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class EntropyQualityTest {

	@Test
	public void testCompleteness() {
		EntropyMetricResult result = new EntropyMetricResult(0, 0, 0, 0, 0, 0,
				0, 0);
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (EntropyQuality.get(type, result) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}

}
