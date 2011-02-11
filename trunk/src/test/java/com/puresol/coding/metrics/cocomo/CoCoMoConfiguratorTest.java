package com.puresol.coding.metrics.cocomo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.puresol.gui.osgi.AbstractBundleConfiguratorPanel;

public class CoCoMoConfiguratorTest {

	@Test
	public void testInstance() {
		assertNotNull(new CoCoMoConfigurator());
	}

	@Test
	public void testCreatePanel() {
		CoCoMoConfigurator configurator = new CoCoMoConfigurator();
		AbstractBundleConfiguratorPanel panel = configurator.createPanel();
		assertNotNull(panel);
		assertTrue(panel.getComponentCount() > 0);
	}

}
