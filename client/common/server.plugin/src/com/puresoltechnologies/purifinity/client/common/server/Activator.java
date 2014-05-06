package com.puresoltechnologies.purifinity.client.common.server;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.puresoltechnologies.purifinity.server.analysisservice.client.AnalysisServiceClient;
import com.puresoltechnologies.purifinity.server.client.PurifinityServerClient;

public class Activator extends AbstractUIPlugin {

	// The shared instance
	private static Activator plugin = null;

	private ServiceRegistration<PurifinityServerClient> purifinityServerClientRegistration = null;
	private PurifinityServerClient purifinityServerClient = null;

	private ServiceRegistration<AnalysisServiceClient> analysisServiceClientRegistration = null;
	private AnalysisServiceClient analysisServiceClient = null;

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		if (plugin != null) {
			throw new RuntimeException("A " + getClass().getName()
					+ " plugin was already started!");
		}
		plugin = this;

		purifinityServerClient = new PurifinityServerClient();
		purifinityServerClientRegistration = context.registerService(
				PurifinityServerClient.class, purifinityServerClient, null);

		analysisServiceClient = new AnalysisServiceClient();
		analysisServiceClientRegistration = context.registerService(
				AnalysisServiceClient.class, analysisServiceClient, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (plugin == null) {
			throw new RuntimeException("A " + getClass().getName()
					+ " plugin was never started!");
		}
		plugin = null;

		purifinityServerClientRegistration.unregister();
		purifinityServerClientRegistration = null;
		purifinityServerClient.close();
		purifinityServerClient = null;

		analysisServiceClientRegistration.unregister();
		analysisServiceClientRegistration = null;
		analysisServiceClient.close();
		analysisServiceClient = null;

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
