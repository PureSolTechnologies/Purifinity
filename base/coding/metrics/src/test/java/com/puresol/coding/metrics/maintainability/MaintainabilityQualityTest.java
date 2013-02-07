package com.puresol.coding.metrics.maintainability;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.quality.SourceCodeQuality;

public class MaintainabilityQualityTest {

	@Test
	public void testCompleteness() {
		MaintainabilityIndexResult result = new MaintainabilityIndexResult(10,
				10);
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (MaintainabilityQuality.get(type, result) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}

}
