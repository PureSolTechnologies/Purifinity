package com.puresol.purifinity.coding.metrics.normmaint;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityIndexResult;
import com.puresol.purifinity.coding.metrics.normmaint.NormalizedMaintainabilityQuality;

public class NormalizedMaintainabilityQualityTest {

	@Test
	public void testCompleteness() {
		NormalizedMaintainabilityIndexResult result = new NormalizedMaintainabilityIndexResult(
				10, 10);
		for (CodeRangeType type : CodeRangeType.class.getEnumConstants()) {
			if (NormalizedMaintainabilityQuality.get(type, result) == SourceCodeQuality.UNSPECIFIED) {
				fail("No source code quality check for code range type '"
						+ type.name() + "' defined!");
			}
		}
	}

}