package com.puresol.osgi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private static final Map<String, OSGi> frameworks = new HashMap<String, OSGi>();

	public static OSGi getInstance(String name) {
		return frameworks.get(name);
	}

	public static OSGi createInstance(String name) {
		frameworks.put(name, new OSGi());
		return getInstance(name);
	}

	public static void deleteInstance(String name) {
		frameworks.get(name);
	}

	public static <T> List<T> getServices(String frameworkName,
			String serviceName, String filter, Class<T> clazz)
			throws InvalidSyntaxException {
		OSGi osgi = OSGiFrameworkManager.getInstance(frameworkName);
		BundleContext bundleContext = osgi.getContext();
		ServiceReference references[];
		references = bundleContext.getServiceReferences(serviceName, filter);
		List<T> services = new ArrayList<T>();
		for (ServiceReference reference : references) {
			@SuppressWarnings("unchecked")
			T t = (T) bundleContext.getService(reference);
			services.add(t);
		}
		return services;
	}
}
