package com.puresol.config.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationManagerTest {

	@Test
	public void testSingleton() {
		ConfigurationManager configurationManager = ConfigurationManager
				.getInstance(ConfigurationLayer.SYSTEM);
		assertNotNull(configurationManager);
		assertSame(configurationManager,
				ConfigurationManager.getInstance(ConfigurationLayer.SYSTEM));
		assertNotSame(configurationManager,
				ConfigurationManager.getInstance(ConfigurationLayer.PLUGINS));
	}

}
