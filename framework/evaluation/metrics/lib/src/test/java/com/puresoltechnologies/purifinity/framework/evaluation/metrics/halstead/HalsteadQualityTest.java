package com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead;

import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.halstead.HalsteadResult;

public class HalsteadQualityTest {

	@Test
	public void testCompleteness() {
		HalsteadResult result = new HalsteadResult(
				new HashMap<String, Integer>(), new HashMap<String, Integer>());
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (HalsteadQuality.get(type, result) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}
}
