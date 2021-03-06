package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class McCabeQualityTest {

	@Test
	public void testCompleteness() {
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (McCabeQuality.get(type, 10) == Severity.NONE) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}

}
