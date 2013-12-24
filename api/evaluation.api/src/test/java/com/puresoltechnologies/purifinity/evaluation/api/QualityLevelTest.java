package com.puresoltechnologies.purifinity.evaluation.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class QualityLevelTest {

	@Test
	public void testInitialConstructorWithDoubleValue() {
		QualityLevel qualityLevel = new QualityLevel(0.5);
		assertEquals(0.5, qualityLevel.getLevel(), 1e-10);
	}

	@Test
	public void testInitialConstructorWithHighQuality() {
		QualityLevel qualityLevel = new QualityLevel(SourceCodeQuality.HIGH);
		assertEquals(1.0, qualityLevel.getLevel(), 1e-10);
	}

	@Test
	public void testInitialConstructorWithMediumQuality() {
		QualityLevel qualityLevel = new QualityLevel(SourceCodeQuality.MEDIUM);
		assertEquals(0.5, qualityLevel.getLevel(), 1e-10);
	}

	@Test
	public void testInitialConstructorWithLowQuality() {
		QualityLevel qualityLevel = new QualityLevel(SourceCodeQuality.LOW);
		assertEquals(0.0, qualityLevel.getLevel(), 1e-10);
	}

	@Test
	public void testAddQualityLevel() {
		QualityLevel qualityLevel = new QualityLevel(0.0);
		qualityLevel.add(new QualityLevel(0.5));
		assertEquals(0.25, qualityLevel.getLevel(), 1e-10);
	}

	@Test
	public void testAddQualityLevelWithMultipleEntriies() {
		QualityLevel added = new QualityLevel(1.0);
		added.add(new QualityLevel(1.0));
		assertEquals(1.0, added.getLevel(), 1e-10);

		QualityLevel qualityLevel = new QualityLevel(0.0);
		qualityLevel.add(added);
		assertEquals(0.6666666666, qualityLevel.getLevel(), 1e-10);
	}

	@Test
	public void testAddQuality() {
		QualityLevel qualityLevel = new QualityLevel(0.0);
		qualityLevel.add(SourceCodeQuality.HIGH);
		assertEquals(0.5, qualityLevel.getLevel(), 1e-10);
	}

}
