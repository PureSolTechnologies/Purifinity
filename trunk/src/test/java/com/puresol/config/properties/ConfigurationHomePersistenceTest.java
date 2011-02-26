package com.puresol.config.properties;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class ConfigurationHomePersistenceTest {

	private ConfigurationManager configurationManager = null;

	@Before
	public void setup() {
		assertFalse(System.getProperty("user.home").isEmpty());

		configurationManager = ConfigurationManager
				.getInstance(ConfigurationType.SYSTEM);
		assertNotNull(configurationManager);
		configurationManager.addProperty("Context", "Key", "Value");
	}

	@Test
	public void testLoadAndSave() throws Throwable {
		assertEquals("Value",
				configurationManager.getContextProperties("Context").get("Key"));
		ConfigurationHomePersistence.store("", ConfigurationType.SYSTEM);
		configurationManager.clear();
		assertEquals(null, configurationManager.getContextProperties("Context"));
		ConfigurationHomePersistence.load("", ConfigurationType.SYSTEM);
		assertEquals("Value",
				configurationManager.getContextProperties("Context").get("Key"));
		/*
		 * Delete test file...
		 */
		File file = new File(System.getProperty("user.home"),
				"Context.properties");
		assertTrue(file.exists());
		assertTrue(file.delete());
	}
}
