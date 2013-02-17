package com.puresol.coding.metrics.halstead;

import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.SourceCodeQuality;

public class HalsteadQualityTest {

	@Test
	public void testCompleteness() {
		HalsteadResult result = new HalsteadResult(
				new HashMap<String, Integer>(), new HashMap<String, Integer>(),
				10, 10, 10, 10);
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (HalsteadQuality.get(type, result) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}
}