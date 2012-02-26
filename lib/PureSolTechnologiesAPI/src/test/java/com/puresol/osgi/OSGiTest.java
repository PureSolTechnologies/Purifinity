package com.puresol.osgi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.Test;
import org.osgi.framework.BundleContext;

public class OSGiTest {

    @Test
    public void testResourcesAvailability() {
	assertEquals(
		"/META-INF/services/org.osgi.framework.launch.FrameworkFactory",
		OSGi.OSGI_FRAMEWORK_FACTORY_PROPERTIES);
	assertEquals("/META-INF/services/osgi.properties", OSGi.OSGI_PROPERTIES);
    }

    @Test
    public void testInstance() {
	assertNotNull(new OSGi());
    }

    @Test
    public void testStartup() throws Exception {
	synchronized (this) {
	    OSGi osgi = new OSGi();
	    assertFalse(osgi.isStarted());
	    osgi.start();
	    assertTrue(osgi.isStarted());
	    BundleContext context = osgi.getContext();
	    assertNotNull(context);
	    assertTrue(context.getBundles().length > 0);
	    osgi.stop();
	    assertFalse(osgi.isStarted());
	}
    }

    @Test
    public void testRegisterService() throws Exception {
	synchronized (this) {
	    OSGi osgi = new OSGi();
	    osgi.start();
	    try {
		BundleContext context = osgi.getContext();
		assertNotNull(context);
		Dictionary<String, Object> properties = new Hashtable<String, Object>();
		properties.put("o", "A");
		/*
		 * TODO check the uncommented code as soon as it is clear, that
		 * the rest if the code is stable
		 */
		// ServiceReference reference[] =
		// context.getAllServiceReferences(
		// BundleConfigurator.class.getName(), "(o=A)");
		// assertNotNull(reference);
		// assertEquals(1, reference.length);
		// registration.unregister();
		// reference = context.getAllServiceReferences(
		// BundleConfigurator.class.getName(), "(o=A)");
		// assertNull(reference);
	    } finally {
		osgi.stop();
	    }
	}
    }
}
