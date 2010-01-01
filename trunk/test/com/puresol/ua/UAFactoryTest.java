package com.puresol.ua;

import javax.swingx.config.ClassRegistry;
import javax.swingx.config.ClassRegistryElement;
import javax.swingx.config.ClassRegistryElementType;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class UAFactoryTest extends TestCase {

	@Test
	public void testCreate() {
		ClassRegistry.register(UA.class, new ClassRegistryElement(
				ClassRegistryElementType.FACTORY, "com.puresol.ua.TestJAAS"));
		UA ua = UAFactory.create();
		Assert.assertNotNull(ua);
		Assert.assertEquals("com.puresol.ua.TestJAAS", ua.getClass().getName());
	}
}
