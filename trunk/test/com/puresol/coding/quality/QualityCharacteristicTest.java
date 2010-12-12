package com.puresol.coding.quality;

import org.junit.Test;

import junit.framework.TestCase;

public class QualityCharacteristicTest extends TestCase {

	@Test
	public void testLength() {
		assertEquals(21, QualityCharacteristic.values().length);
	}

	@Test
	public void testSuitability() {
		QualityCharacteristic qualChar = QualityCharacteristic.SUITABILITY;
		assertEquals(QualityMainCharacteristic.FUNCTIONALITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testAccuracy() {
		QualityCharacteristic qualChar = QualityCharacteristic.ACCURACY;
		assertEquals(QualityMainCharacteristic.FUNCTIONALITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testInteroperability() {
		QualityCharacteristic qualChar = QualityCharacteristic.INTEROPERABILITY;
		assertEquals(QualityMainCharacteristic.FUNCTIONALITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testSecurity() {
		QualityCharacteristic qualChar = QualityCharacteristic.SECURIY;
		assertEquals(QualityMainCharacteristic.FUNCTIONALITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testMaturity() {
		QualityCharacteristic qualChar = QualityCharacteristic.MATURITY;
		assertEquals(QualityMainCharacteristic.RELIABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testFaultTolerance() {
		QualityCharacteristic qualChar = QualityCharacteristic.FAULT_TOLERANCE;
		assertEquals(QualityMainCharacteristic.RELIABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testRecoverability() {
		QualityCharacteristic qualChar = QualityCharacteristic.RECOVERABILITY;
		assertEquals(QualityMainCharacteristic.RELIABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testUnderstandability() {
		QualityCharacteristic qualChar = QualityCharacteristic.UNDERSTANDABILITY;
		assertEquals(QualityMainCharacteristic.USABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testLearnability() {
		QualityCharacteristic qualChar = QualityCharacteristic.LEARNABILITY;
		assertEquals(QualityMainCharacteristic.USABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testOperability() {
		QualityCharacteristic qualChar = QualityCharacteristic.OPERABILITY;
		assertEquals(QualityMainCharacteristic.USABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testAttractiveness() {
		QualityCharacteristic qualChar = QualityCharacteristic.ATTRACTIVENESS;
		assertEquals(QualityMainCharacteristic.USABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testTimeBehavior() {
		QualityCharacteristic qualChar = QualityCharacteristic.TIME_BEHAVIOR;
		assertEquals(QualityMainCharacteristic.EFFICIENCY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testResourceUtilitzation() {
		QualityCharacteristic qualChar = QualityCharacteristic.RESOURCE_UTILIZATION;
		assertEquals(QualityMainCharacteristic.EFFICIENCY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testAnalysability() {
		QualityCharacteristic qualChar = QualityCharacteristic.ANALYSABILITY;
		assertEquals(QualityMainCharacteristic.MAINTAINABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testChangeability() {
		QualityCharacteristic qualChar = QualityCharacteristic.CHANGEABILITY;
		assertEquals(QualityMainCharacteristic.MAINTAINABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testStability() {
		QualityCharacteristic qualChar = QualityCharacteristic.STABILITY;
		assertEquals(QualityMainCharacteristic.MAINTAINABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testTestability() {
		QualityCharacteristic qualChar = QualityCharacteristic.TESTABILITY;
		assertEquals(QualityMainCharacteristic.MAINTAINABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testAdaptability() {
		QualityCharacteristic qualChar = QualityCharacteristic.ADAPTABILITY;
		assertEquals(QualityMainCharacteristic.PORTABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testInstallability() {
		QualityCharacteristic qualChar = QualityCharacteristic.INSTALLABILITY;
		assertEquals(QualityMainCharacteristic.PORTABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testCoExistence() {
		QualityCharacteristic qualChar = QualityCharacteristic.CO_EXISTANCE;
		assertEquals(QualityMainCharacteristic.PORTABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

	@Test
	public void testReplaceability() {
		QualityCharacteristic qualChar = QualityCharacteristic.REPLACEABILITY;
		assertEquals(QualityMainCharacteristic.PORTABILITY,
				qualChar.getMainCharacteristic());
		assertFalse(qualChar.getName().isEmpty());
		// TODO assertFalse(qualChar.getDescription().isEmpty());
	}

}
