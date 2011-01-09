package com.puresol.osgi;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * This manager keeps different named OSGi frameworks for central access. The
 * OSGi frameworks are only created and deleted. Opening and closing them is in
 * responsible to the creating application.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class OSGiFrameworkManager {

	private static OSGi osgi = null;

	public static OSGi getInstance() {
		if (osgi == null) {
			createInstance();
		}
		return osgi;
	}

	private static synchronized void createInstance() {
		if (osgi == null) {
			osgi = new OSGi();
		}
	}

	public static <T> List<T> getServices(String serviceName, String filter)
			throws InvalidSyntaxException {
		List<T> services = new ArrayList<T>();
		if (osgi == null) {
			return services;
		}
		BundleContext bundleContext = osgi.getContext();
		if (bundleContext == null) {
			return services;
		}
		ServiceReference references[];
		references = bundleContext.getServiceReferences(serviceName, filter);
		if (references == null) {
			return services;
		}
		for (ServiceReference reference : references) {
			@SuppressWarnings("unchecked")
			T t = (T) bundleContext.getService(reference);
			services.add(t);
		}
		return services;
	}
}
