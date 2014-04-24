package com.puresoltechnologies.purifinity.server.analysisservice.client;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisStoreServiceRestInterface;

public class AnalysisStoreServiceClient {

	private final AnalysisStoreServiceRestInterface proxy;

	static {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}

	public static AnalysisStoreServiceClient createInstance() {
		return new AnalysisStoreServiceClient();
	}

	public AnalysisStoreServiceClient() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget webTarget = client
				.target("http://localhost:8080/passwordstore");
		proxy = webTarget.proxy(AnalysisStoreServiceRestInterface.class);
	}

}
