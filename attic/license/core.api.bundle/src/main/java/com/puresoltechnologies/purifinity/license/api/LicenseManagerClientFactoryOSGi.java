package com.puresoltechnologies.purifinity.license.api;

import org.osgi.framework.BundleContext;

import com.puresoltechnologies.purifinity.license.api.LicenseManagerClient;
import com.puresoltechnologies.purifinity.license.api.LicenseManagerClientFactory;
import com.puresoltechnologies.purifinity.license.api.LicenseManagerClientImpl;

/**
 * This is the central factory for a file store.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LicenseManagerClientFactoryOSGi extends
		LicenseManagerClientFactory {

	private static final BundleContext bundleContext = Activator
			.getBundleContext();

	@Override
	public LicenseManagerClient createLicenseManagerClient() {
		// try {
		// Collection<ServiceReference<LicenseManagerClient>> serviceReferences
		// = bundleContext
		// .getServiceReferences(LicenseManagerClient.class, null);
		// Iterator<ServiceReference<LicenseManagerClient>> iterator =
		// serviceReferences
		// .iterator();
		// if (!iterator.hasNext()) {
		// throw new RuntimeException("No license manager was registered!");
		// }
		// ServiceReference<LicenseManagerClient> serviceReference =
		// iterator.next();
		// if (iterator.hasNext()) {
		// throw new RuntimeException(
		// "More than one license manager was registered!");
		// }
		// return bundleContext.getService(serviceReference);
		// } catch (InvalidSyntaxException e) {
		// throw new RuntimeException("Could not find license manager!", e);
		// }
		return new LicenseManagerClientImpl();
	}
}
