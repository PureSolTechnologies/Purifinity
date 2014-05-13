package com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.maintainability;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexResult;

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
