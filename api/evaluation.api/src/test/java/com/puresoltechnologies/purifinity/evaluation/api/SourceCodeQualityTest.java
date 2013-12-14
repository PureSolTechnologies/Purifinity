package com.puresoltechnologies.purifinity.evaluation.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

public class SourceCodeQualityTest {

	@Test
	public void testGetMinLevel() {
		assertEquals(SourceCodeQuality.LOW, SourceCodeQuality.getMinimum(
				SourceCodeQuality.LOW, SourceCodeQuality.MEDIUM,
				SourceCodeQuality.HIGH, SourceCodeQuality.UNSPECIFIED));
		assertEquals(SourceCodeQuality.LOW, SourceCodeQuality.getMinimum(
				SourceCodeQuality.LOW, SourceCodeQuality.HIGH,
				SourceCodeQuality.UNSPECIFIED));
		assertEquals(SourceCodeQuality.MEDIUM, SourceCodeQuality.getMinimum(
				SourceCodeQuality.MEDIUM, SourceCodeQuality.HIGH,
				SourceCodeQuality.UNSPECIFIED));
		assertEquals(SourceCodeQuality.HIGH, SourceCodeQuality.getMinimum(
				SourceCodeQuality.HIGH, SourceCodeQuality.UNSPECIFIED));
	}
}
