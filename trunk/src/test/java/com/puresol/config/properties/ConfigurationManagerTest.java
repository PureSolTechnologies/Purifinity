package com.puresol.config.properties;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationManagerTest {

	@Test
	public void testSingleton() {
		ConfigurationManager configurationManager = ConfigurationManager
				.getInstance();
		assertNotNull(configurationManager);
		assertSame(configurationManager, ConfigurationManager.getInstance());
	}

}
