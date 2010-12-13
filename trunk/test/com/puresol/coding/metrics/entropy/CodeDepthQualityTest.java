package com.puresol.coding.metrics.entropy;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresol.coding.CodeRangeType;
import com.puresol.coding.quality.SourceCodeQuality;

public class CodeDepthQualityTest {

	@Test
	public void testCompleteness() {
		EntropyResult result = new EntropyResult(0, 0, 0, 0, 0, 0, 0, 0);
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (EntropyQuality.get(type, result) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}

}
