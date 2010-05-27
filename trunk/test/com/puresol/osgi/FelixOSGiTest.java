package com.puresol.osgi;

import org.junit.Test;
import org.osgi.framework.launch.Framework;

import junit.framework.TestCase;

public class FelixOSGiTest extends TestCase {

    @Test
    public void testSingleton() {
	OSGi felix = new FelixOSGi();
	assertNotNull(felix);
	try {
	    Framework framework = felix.startFramework();
	    assertNotNull(framework);
	    assertTrue(felix.isStarted());
	    felix.stopFramework();
	    assertFalse(felix.isStarted());
	} catch (OSGiException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
    }

}
