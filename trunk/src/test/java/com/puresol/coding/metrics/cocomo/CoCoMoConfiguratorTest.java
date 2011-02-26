package com.puresol.coding.metrics.cocomo;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoCoMoConfiguratorTest {

	@Test
	public void testInstance() {
		assertNotNull(new CoCoMoConfigurator());
	}

	@Test
	public void testCreatePanel() {
		CoCoMoConfigurator configurator = new CoCoMoConfigurator();
		assertFalse(configurator.getName().isEmpty());
		assertTrue(configurator.getPathName().startsWith("Evaluators/CoCoMo"));
		assertTrue(configurator.getPropertyDescriptions().size() > 0);
	}

}
