package com.puresol.coding.analysis.metrics;

import org.junit.Test;

import com.puresol.coding.analysis.CodeAnalysisProperties;

import junit.framework.Assert;
import junit.framework.TestCase;

public class McCabeMetricTest extends TestCase {

	@Test
	public void testRegistrationInCodeAnalysisProperties() {
		Assert.assertTrue(CodeAnalysisProperties.getPropertyValue(
				"CodeAnalysis.Metrics").contains(McCabeMetric.class.getName()));
	}
}
