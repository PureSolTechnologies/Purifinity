package com.puresol.osgi;

import static org.junit.Assert.*;

import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import com.puresol.gui.osgi.BundleConfigurator;
import com.puresol.gui.osgi.BundleConfiguratorPanel;

public class OSGiTest {

	@Test
	public void testResourcesAvailability() {
		assertNotNull(OSGi.class
				.getResource(OSGi.OSGI_FRAMEWORK_FACTORY_PROPERTIES));
		assertNotNull(OSGi.class.getResource(OSGi.OSGI_PROPERTIES));
	}

	@Test
	public void testInstance() {
		assertNotNull(new OSGi());
	}

	@Test
	public void testStartup() {
		try {
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
		} catch (OSGiException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (BundleException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testRegisterService() {
		try {
			synchronized (this) {
				OSGi osgi = new OSGi();
				osgi.start();
				try {
					BundleContext context = osgi.getContext();
					assertNotNull(context);
					Dictionary<Object, Object> properties = new Hashtable<Object, Object>();
					properties.put("o", "A");
					ServiceRegistration registration = context.registerService(
							BundleConfigurator.class.getName(),
							new BundleConfigurator() {

								@Override
								public String getName() {
									return null;
								}

								@Override
								public String getPathName() {
									return null;
								}

								@Override
								public BundleConfiguratorPanel createPanel() {
									return null;
								}
							}, properties);
					assertNotNull(registration);
					ServiceReference reference[] = context
							.getAllServiceReferences(
									BundleConfigurator.class.getName(), "(o=A)");
					assertNotNull(reference);
					assertEquals(1, reference.length);
					registration.unregister();
					reference = context.getAllServiceReferences(
							BundleConfigurator.class.getName(), "(o=A)");
					assertNull(reference);
				} finally {
					osgi.stop();
				}
			}
		} catch (OSGiException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (BundleException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
