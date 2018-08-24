package com.puresoltechnologies.purifinity.server.metrics.halstead;

import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class HalsteadQualityTest {

	@Test
	public void testCompleteness() {
		HalsteadResult result = new HalsteadResult(
				new HashMap<String, Integer>(), new HashMap<String, Integer>());
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (HalsteadQuality.get(type, result) == Severity.NONE) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}
}
