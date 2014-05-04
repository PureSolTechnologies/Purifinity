package com.puresoltechnologies.purifinity.server.analysisservice.client;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AvailableAnalyzers;
import com.puresoltechnologies.purifinity.server.common.rest.JSONMapper;

public class AnalysisServiceClient implements AutoCloseable {

	private final AnalysisServiceRestInterface proxy;

	static {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}

	public AnalysisServiceClient() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget webTarget = client
				.target("http://localhost:8080/analysisservice");
		proxy = webTarget.proxy(AnalysisServiceRestInterface.class);
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
	}

	public AvailableAnalyzers getAnalyzers() throws IOException {
		Response response = proxy.getAnalyzers();
		return JSONMapper.fromJSONString((String) response.getEntity(),
				AvailableAnalyzers.class);
	}

}
