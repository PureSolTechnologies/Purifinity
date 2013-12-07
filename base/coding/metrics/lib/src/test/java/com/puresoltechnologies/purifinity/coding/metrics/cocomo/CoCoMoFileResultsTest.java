package com.puresoltechnologies.purifinity.coding.metrics.cocomo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresoltechnologies.parser.impl.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.coding.metrics.cocomo.basic.BasicCoCoMoFileResults;
import com.puresoltechnologies.purifinity.coding.metrics.cocomo.basic.SoftwareProject;

public class CoCoMoFileResultsTest {

	@Test
	public void testInstance() {
		assertNotNull(new BasicCoCoMoFileResults(new UnspecifiedSourceCodeLocation()));
	}

	@Test
	public void testDefaultValues() {
		BasicCoCoMoFileResults set = new BasicCoCoMoFileResults(
				new UnspecifiedSourceCodeLocation());
		assertEquals(56286, set.getAverageSalary(), 1e-8);
		assertEquals("$", set.getCurrency());
		assertEquals(SoftwareProject.LOW, set.getComplexity());
	}

	@Test
	public void testSetterUndGetter() {
		BasicCoCoMoFileResults set = new BasicCoCoMoFileResults(
				new UnspecifiedSourceCodeLocation());
		set.setAverageSalary(12345.67, "EUR");
		set.setComplexity(SoftwareProject.HIGH);
		set.setSloc(1234567);

		assertEquals(12345.67, set.getAverageSalary(), 1e-8);
		assertEquals("EUR", set.getCurrency());
		assertEquals(SoftwareProject.HIGH, set.getComplexity());
		assertEquals(1234567, set.getPhyLOC());
	}

	@Test
	public void testFormalaConstants() {
		BasicCoCoMoFileResults set = new BasicCoCoMoFileResults(
				new UnspecifiedSourceCodeLocation());
		set.setComplexity(SoftwareProject.LOW);
		assertEquals(2.4, set.getC1(), 1e-8);
		assertEquals(1.05, set.getC2(), 1e-8);
		assertEquals(0.38, set.getC3(), 1e-8);

		set.setComplexity(SoftwareProject.MEDIUM);
		assertEquals(3.0, set.getC1(), 1e-8);
		assertEquals(1.12, set.getC2(), 1e-8);
		assertEquals(0.35, set.getC3(), 1e-8);

		set.setComplexity(SoftwareProject.HIGH);
		assertEquals(3.6, set.getC1(), 1e-8);
		assertEquals(1.20, set.getC2(), 1e-8);
		assertEquals(0.32, set.getC3(), 1e-8);
	}

	@Test
	public void testCalculation() {
		BasicCoCoMoFileResults set = new BasicCoCoMoFileResults(
				new UnspecifiedSourceCodeLocation());
		set.setAverageSalary(50000, "$");
		set.setComplexity(SoftwareProject.LOW);
		set.setSloc(100000);

		assertEquals(50000.0, set.getAverageSalary(), 1e-8);
		assertEquals("$", set.getCurrency());
		assertEquals(SoftwareProject.LOW, set.getComplexity());
		assertEquals(100000, set.getPhyLOC());

		assertEquals(3021.6, set.getEstimatedCosts(), 1e-8);
		assertEquals(100.0, set.getKsloc(), 1e-8);
		assertEquals(302.14, set.getPersonMonth(), 1e-8);
		assertEquals(25.18, set.getPersonYears(), 1e-8);
		assertEquals(21.90, set.getScheduledMonth(), 1e-8);
		assertEquals(1.83, set.getScheduledYears(), 1e-8);
		assertEquals(13.80, set.getTeamSize(), 1e-8);
	}

}
