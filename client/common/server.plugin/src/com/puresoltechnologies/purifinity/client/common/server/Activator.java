package com.puresoltechnologies.purifinity.client.common.server;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.puresoltechnologies.purifinity.server.purifinityserver.client.PurifinityServerClientImpl;
import com.puresoltechnologies.purifinity.server.purifinityserver.socket.api.PurifinityServerClient;

public class Activator extends AbstractUIPlugin {

	// The shared instance
	private static Activator plugin = null;

	private ServiceRegistration<PurifinityServerClient> clientRegistration = null;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		if (plugin != null) {
			throw new RuntimeException("A " + getClass().getName()
					+ " plugin was already started!");
		}
		plugin = this;
		clientRegistration = context.registerService(
				PurifinityServerClient.class, new PurifinityServerClientImpl(),
				null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (plugin == null) {
			throw new RuntimeException("A " + getClass().getName()
					+ " plugin was never started!");
		}
		plugin = null;

		clientRegistration.unregister();
		clientRegistration = null;

		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		if (plugin == null) {
			throw new RuntimeException("A " + Activator.class.getName()
					+ " plugin was never started!");
		}
		return plugin;
	}
}