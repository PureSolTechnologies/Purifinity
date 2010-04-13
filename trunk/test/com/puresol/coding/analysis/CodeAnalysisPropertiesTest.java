package com.puresol.coding.analysis;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CodeAnalysisPropertiesTest extends TestCase {

	private CodeAnalysisProperties properties = CodeAnalysisProperties
			.getInstance();

	@Test
	public void testLoadingAnalysers() {
		Assert.assertFalse(properties.getProperty("CodeAnalysis.Languages")
				.isEmpty());
	}

}
