package com.puresol.coding.evaluator;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CodeEvaluationPropertiesTest extends TestCase {

	private CodeEvaluationProperties properties = CodeEvaluationProperties
			.getInstance();

	@Test
	public void testLoadingMetrics() {
		Assert.assertFalse(properties.getProperty("CodeEvaluation.Metrics")
				.isEmpty());
	}

	@Test
	public void testLoadingEvaluators() {
		Assert.assertFalse(properties.getProperty("CodeEvaluation.Evaluators")
				.isEmpty());
	}

}
