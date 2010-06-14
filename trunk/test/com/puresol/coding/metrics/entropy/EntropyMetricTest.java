package com.puresol.coding.metrics.entropy;

import org.junit.Test;

import com.puresol.coding.evaluator.CodeEvaluationProperties;
import com.puresol.coding.evaluator.metric.EntropyMetric;

import junit.framework.Assert;
import junit.framework.TestCase;

public class EntropyMetricTest extends TestCase {

	@Test
	public void testRegistrationInCodeAnalysisProperties() {
		Assert.assertTrue(CodeEvaluationProperties.getPropertyValue(
				"CodeEvaluation.Metrics").contains(
				EntropyMetric.class.getName()));
	}
}
