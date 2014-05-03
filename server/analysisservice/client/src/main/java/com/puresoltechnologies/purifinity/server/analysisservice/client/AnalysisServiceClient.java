package com.puresoltechnologies.purifinity.server.analysisservice.client;

import java.util.Collection;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisStoreRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalyzerInformation;

public class AnalysisServiceClient implements AutoCloseable {

	private final AnalysisStoreRestInterface proxy;

	static {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}

	public AnalysisServiceClient() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget webTarget = client
				.target("http://localhost:8080/analysisservice");
		proxy = webTarget.proxy(AnalysisStoreRestInterface.class);
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
	}

	public Collection<AnalyzerInformation> getAnalyzers() {
		// TODO Auto-generated method stub
		return null;
	}

}
