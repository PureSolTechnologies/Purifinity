package com.puresol.config.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationTypeTest {

	/**
	 * This test checks and assures the sort order of the enum constants. This
	 * is very(!) important for the ConfigurationManager to find the corret
	 * settings.
	 * 
	 * The order needs to be:
	 * 
	 * <pre>
	 *     1) SYSTEM
	 *     2) PLUGINS
	 *     3) PROJECT
	 * </pre>
	 * 
	 * In that order the configuration is searched and overridden if as soon as
	 * there is another value found. E.g. PLUGIN configuration override SYSTEM
	 * and PROJECT overrides PLUGINS. Due to it transitiveness the PROJECT
	 * settings also override SYSTEM.
	 */
	@Test
	public void testOrder() {
		Object constants[] = ConfigurationLayer.class.getEnumConstants();
		assertNotNull(constants);
		assertEquals(3, constants.length);
		assertEquals(ConfigurationLayer.SYSTEM, constants[0]);
		assertEquals(ConfigurationLayer.PLUGINS, constants[1]);
		assertEquals(ConfigurationLayer.PROJECT, constants[2]);
	}

}
