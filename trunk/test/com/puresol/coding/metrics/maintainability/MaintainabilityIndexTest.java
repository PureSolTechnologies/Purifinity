package com.puresol.coding.metrics.maintainability;

import org.junit.Test;

import com.puresol.coding.evaluator.CodeEvaluationProperties;
import com.puresol.coding.evaluator.metric.MaintainabilityIndex;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MaintainabilityIndexTest extends TestCase {

	@Test
	public void testRegistrationInCodeAnalysisProperties() {
		Assert.assertTrue(CodeEvaluationProperties.getPropertyValue(
				"CodeEvaluation.Metrics").contains(
				MaintainabilityIndex.class.getName()));
	}
}
