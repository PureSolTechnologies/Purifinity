package com.puresol.coding.metrics.sloc;

import org.junit.Test;

import com.puresol.coding.evaluator.CodeEvaluationProperties;
import com.puresol.coding.evaluator.metric.SLOCMetric;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SLOCMetricTest extends TestCase {

	@Test
	public void testRegistrationInCodeAnalysisProperties() {
		Assert.assertTrue(CodeEvaluationProperties.getPropertyValue(
				"CodeEvaluation.Metrics").contains(SLOCMetric.class.getName()));
	}
}
