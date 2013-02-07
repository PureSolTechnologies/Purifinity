package com.puresol.coding.metrics.mccabe;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.quality.SourceCodeQuality;

public class McCabeQualityTest {

	@Test
	public void testCompleteness() {
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (McCabeQuality.get(type, 10) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}

}
