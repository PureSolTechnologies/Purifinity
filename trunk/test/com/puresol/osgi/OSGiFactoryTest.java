package com.puresol.osgi;

import org.junit.Test;

import junit.framework.TestCase;

public class OSGiFactoryTest extends TestCase {

    @Test
    public void testSingleton() {
	OSGi osgi = OSGiFactory.create();
	assertNotNull(osgi);
	OSGi osgi2 = OSGiFactory.create();
	assertNotNull(osgi2);
	assertSame(osgi, osgi2);
    }
}
