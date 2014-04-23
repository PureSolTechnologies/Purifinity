package com.puresoltechnologies.purifinity.client.common.server;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.purifinity.server.purifinityserver.client.PurifinityServerClient;

public class PurifinityServerClientFactory {

	public static PurifinityServerClient getInstance() {
		Activator activator = Activator.getDefault();
		Bundle bundle = activator.getBundle();
		BundleContext bundleContext = bundle.getBundleContext();
		ServiceReference<PurifinityServerClient> serviceReference = bundleContext
				.getServiceReference(PurifinityServerClient.class);
		return bundleContext.getService(serviceReference);
	}

}
