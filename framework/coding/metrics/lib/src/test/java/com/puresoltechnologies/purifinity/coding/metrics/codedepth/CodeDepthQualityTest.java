package com.puresoltechnologies.purifinity.coding.metrics.codedepth;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.metrics.codedepth.CodeDepthQuality;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

public class CodeDepthQualityTest {

	@Test
	public void testCompleteness() {
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (CodeDepthQuality.get(type, 3) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}

}
