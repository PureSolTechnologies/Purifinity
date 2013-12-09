package com.puresoltechnologies.purifinity.coding.metrics.entropy;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyMetricResult;
import com.puresoltechnologies.purifinity.coding.metrics.entropy.EntropyQuality;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

public class EntropyQualityTest {

	@Test
	public void testCompleteness() {
		EntropyMetricResult result = new EntropyMetricResult(0, 0, 0, 0, 0, 0, 0, 0);
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (EntropyQuality.get(type, result) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}

}
