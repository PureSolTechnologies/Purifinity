package com.puresol.coding.metrics.halstead;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class HalsteadQualityTest {

	@Test
	public void testCompleteness() {
		HalsteadResult result = new HalsteadResult(10, 10, 10, 10);
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (HalsteadQuality.get(type, result) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}

}
