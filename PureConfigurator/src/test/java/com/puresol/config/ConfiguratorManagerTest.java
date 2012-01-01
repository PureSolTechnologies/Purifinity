package com.puresol.config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class ConfiguratorManagerTest {

    @Test
    public void testInstance() {
	assertNotNull(ConfigurationManager.getInstance());
    }

    @Test
    public void testSingleton() {
	ConfigurationManager manager1 = ConfigurationManager.getInstance();
	ConfigurationManager manager2 = ConfigurationManager.getInstance();
	assertSame(manager1, manager2);
    }

}
