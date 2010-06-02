package com.puresol.osgi;

import junit.framework.TestCase;

import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class OSGiTest extends TestCase {

	@Test
	public void testStartupAndSingleton() {
		try {
			OSGi osgi = OSGi.getStartedInstance();
			assertNotNull(osgi);
			OSGi osgi2 = OSGi.getStartedInstance();
			assertSame(osgi, osgi2);
			BundleContext context = osgi.getContext();
			assertNotNull(context);
			assertTrue(context.getBundles().length > 0);
			OSGi.stopAndKillInstance();
		} catch (OSGiException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (BundleException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
