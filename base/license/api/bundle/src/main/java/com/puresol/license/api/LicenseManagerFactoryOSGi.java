package com.puresol.license.api;

import org.osgi.framework.BundleContext;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LicenseManagerFactoryOSGi extends LicenseManagerFactory {

    private static final BundleContext bundleContext = Activator
	    .getBundleContext();

    @Override
    public LicenseManager createLicenseManager() {
	// try {
	// Collection<ServiceReference<LicenseManager>> serviceReferences =
	// bundleContext
	// .getServiceReferences(LicenseManager.class, null);
	// Iterator<ServiceReference<LicenseManager>> iterator =
	// serviceReferences
	// .iterator();
	// if (!iterator.hasNext()) {
	// throw new RuntimeException("No license manager was registered!");
	// }
	// ServiceReference<LicenseManager> serviceReference = iterator.next();
	// if (iterator.hasNext()) {
	// throw new RuntimeException(
	// "More than one license manager was registered!");
	// }
	// return bundleContext.getService(serviceReference);
	// } catch (InvalidSyntaxException e) {
	// throw new RuntimeException("Could not find license manager!", e);
	// }
	return new LicenseManagerImpl();
    }
}
