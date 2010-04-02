package com.puresol.coding.evaluator.metric;

import org.junit.Test;

import com.puresol.coding.evaluator.CodeEvaluationProperties;
import com.puresol.coding.evaluator.metric.HalsteadMetric;

import junit.framework.Assert;
import junit.framework.TestCase;

public class HalsteadMetricTest extends TestCase {

	@Test
	public void testRegistrationInCodeAnalysisProperties() {
		Assert.assertTrue(CodeEvaluationProperties.getPropertyValue(
				"CodeEvaluation.Metrics").contains(
				HalsteadMetric.class.getName()));
	}
}
