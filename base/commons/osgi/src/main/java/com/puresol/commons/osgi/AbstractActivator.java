package com.puresol.commons.osgi;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is an abstract Activator implementation which can be used by all OSGi
 * bundles to provide a simple Activator which logs at least the start and stop
 * and provides simple functionality like {@link BundleContext} retrieval.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractActivator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(AbstractActivator.class);

    /**
     * This field holds the current bundle context. This field is static due to
     * the fact that the {@link BundleActivator} is treaded like a singleton and
     * is only called once per state.
     */
    private static BundleContext context = null;

    private final List<ServiceRegistration<?>> serviceRegistrations = new ArrayList<ServiceRegistration<?>>();

    public AbstractActivator() {
	super();
	System.err.println("Activator " + getClass().getPackage().getName()
		+ " was instantiated.");
	logger.debug("Activator " + getClass().getPackage().getName()
		+ " was instantiated.");
    }

    @Override
    public void start(BundleContext context) throws Exception {
	System.err.println("Starting bundle "
		+ getClass().getPackage().getName() + "...");
	logger.info("Starting bundle " + getClass().getPackage().getName()
		+ "...");
	if (AbstractActivator.context != null) {
	    throw new RuntimeException("Bundle was already started!");

	}
	AbstractActivator.context = context;
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	System.err.println("Stopping bundle "
		+ getClass().getPackage().getName() + "...");
	logger.info("Stopping bundle " + getClass().getPackage().getName()
		+ "...");
	if (AbstractActivator.context == null) {
	    throw new RuntimeException("Bundle was never started!");
	}
	AbstractActivator.context = null;
	Iterator<ServiceRegistration<?>> serviceIterator = serviceRegistrations
		.iterator();
	while (serviceIterator.hasNext()) {
	    ServiceRegistration<?> serviceReference = serviceIterator.next();
	    serviceReference.unregister();
	    serviceRegistrations.remove(serviceIterator);
	}
    }

    /**
     * This method returns the current bundle context.
     * 
     * @return A {@link BundleContext} is returned containing the current
     *         context. The return value is expected to be not null due to this
     *         class is called during bundle startup. If the bundle was not
     *         started, the return value is null.
     */
    public static final BundleContext getBundleContext() {
	if (context == null) {
	    throw new RuntimeException("Bundle was not activated!");
	}
	return context;
    }

    public <T> ServiceRegistration<T> registerService(Class<T> iface, T instance) {
	Hashtable<String, String> properties = new Hashtable<String, String>();
	return registerService(iface, instance, properties);
    }

    public <T> ServiceRegistration<T> registerService(Class<T> iface,
	    T instance, Dictionary<String, String> dictionary) {
	ServiceRegistration<T> serviceRegistration = context.registerService(
		iface, instance, dictionary);
	serviceRegistrations.add(serviceRegistration);
	return serviceRegistration;
    }
}
