package com.puresoltechnologies.purifinity.coding.metrics.maintainability;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityIndexResult;
import com.puresoltechnologies.purifinity.coding.metrics.maintainability.MaintainabilityQuality;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

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
