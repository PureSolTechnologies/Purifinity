package com.puresol.coding.quality;

import org.junit.Test;

import junit.framework.TestCase;

public class QualityMainCharacteristicTest extends TestCase {

	@Test
	public void testLength() {
		assertEquals(6, QualityMainCharacteristic.values().length);
	}

	@Test
	public void testFunctionality() {
		QualityMainCharacteristic qualChar = QualityMainCharacteristic.FUNCTIONALITY;
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testReliabilit8y() {
		QualityMainCharacteristic qualChar = QualityMainCharacteristic.RELIABILITY;
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testUsability() {
		QualityMainCharacteristic qualChar = QualityMainCharacteristic.USABILITY;
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testEfficiency() {
		QualityMainCharacteristic qualChar = QualityMainCharacteristic.EFFICIENCY;
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testMaintainability() {
		QualityMainCharacteristic qualChar = QualityMainCharacteristic.MAINTAINABILITY;
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testPortability() {
		QualityMainCharacteristic qualChar = QualityMainCharacteristic.PORTABILITY;
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

}
