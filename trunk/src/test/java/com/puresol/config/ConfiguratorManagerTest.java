package com.puresol.config;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfiguratorManagerTest {

	@Test
	public void testInstance() {
		assertNotNull(new ConfigurationManager());
	}

}
