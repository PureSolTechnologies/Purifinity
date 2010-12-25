package com.puresol.osgi;

import static org.junit.Assert.*;

import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class OSGiTest {

	@Test
	public void testResources() {
		assertNotNull(OSGi.class
				.getResource(OSGi.OSGI_FRAMEWORK_FACTORY_PROPERTIES));
		assertNotNull(OSGi.class.getResource(OSGi.OSGI_PROPERTIES));
	}

	@Test
	public void testStartupAndSingleton() {
		try {
			OSGi osgi = new OSGi();
			assertNotNull(osgi);
			assertFalse(osgi.isStarted());
			osgi.start();
			assertTrue(osgi.isStarted());
			BundleContext context = osgi.getContext();
			assertNotNull(context);
			assertTrue(context.getBundles().length > 0);
			osgi.stop();
			assertFalse(osgi.isStarted());
		} catch (OSGiException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (BundleException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
