package com.puresoltechnologies.purifinity.server.client.analysisservice;

import java.io.IOException;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AvailableAnalyzers;

public class AnalysisServiceClient implements AutoCloseable {

	private final ResteasyClient client;
	private final AnalysisServiceRestInterface proxy;

	static {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}

	public AnalysisServiceClient() {
		ResteasyClientBuilder resteasyClientBuilder = new ResteasyClientBuilder();
		resteasyClientBuilder.register(JacksonJsonProvider.class);
		client = resteasyClientBuilder.build();
		ResteasyWebTarget webTarget = client
				.target("http://localhost:8080/rest");
		proxy = webTarget.proxy(AnalysisServiceRestInterface.class);
	}

	@Override
	public void close() throws IOException {
		client.close();
	}

	public AvailableAnalyzers getAnalyzers() throws IOException {
		return proxy.getAnalyzers();
	}
}
