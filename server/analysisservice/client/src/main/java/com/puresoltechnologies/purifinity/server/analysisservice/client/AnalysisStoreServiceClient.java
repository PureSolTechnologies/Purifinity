package com.puresoltechnologies.purifinity.server.analysisservice.client;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisFileTree;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisStoreRestException;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisStoreServiceRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.FileSearchConfiguration;
import com.puresoltechnologies.purifinity.server.common.rest.JSONMapper;

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
				.target("http://localhost:8080/analysisservice");
		proxy = webTarget.proxy(AnalysisStoreServiceRestInterface.class);
	}

	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) throws AnalysisStoreRestException {
		try {
			String jsonString = JSONMapper.toJSONString(settings);
			Response response = proxy.createAnalysisProject(jsonString);
			System.err.println(response.getStatus());
			return null;
		} catch (IOException e) {
			throw new RuntimeException("Could not marshall JSON request.", e);
		}
	}

	public List<AnalysisProjectInformation> readAllAnalysisProjectInformation()
			throws AnalysisStoreRestException {
		// TODO Auto-generated method stub
		return null;
	}

	public AnalysisProjectInformation readAnalysisProjectInformation(
			UUID projectUUID) throws AnalysisStoreRestException {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeAnalysisProject(UUID projectUUID)
			throws AnalysisStoreRestException {
		// TODO Auto-generated method stub

	}

	public AnalysisProjectSettings readAnalysisProjectSettings(
			UUID analysisProjectUUID) throws AnalysisStoreRestException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateAnalysisProjectSettings(UUID projectUUID,
			AnalysisProjectSettings settings) throws AnalysisStoreRestException {
		// TODO Auto-generated method stub

	}

	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID)
			throws AnalysisStoreRestException {
		// TODO Auto-generated method stub
		return null;
	}

	public AnalysisRunInformation readAnalysisRun(UUID projectUUID,
			UUID analysisRunUUID) throws AnalysisStoreRestException {
		// TODO Auto-generated method stub
		return null;
	}

	public AnalysisRunInformation readLastAnalysisRun(UUID projectUUID)
			throws AnalysisStoreRestException {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeAnalysisRun(UUID projectUUID, UUID analysisRunUUID)
			throws AnalysisStoreRestException {
		// TODO Auto-generated method stub

	}

	public FileSearchConfiguration readSearchConfiguration(UUID analysisRunUUID)
			throws AnalysisStoreRestException {
		// TODO Auto-generated method stub
		return null;
	}

	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID)
			throws AnalysisStoreRestException {
		// TODO Auto-generated method stub
		return null;
	}

	public AnalysisRunInformation createAnalysisRun(UUID analysisProjectUUID,
			Date startTime, long duration, String description,
			FileSearchConfiguration fileSearchConfiguration)
			throws AnalysisStoreRestException {
		// TODO Auto-generated method stub
		return null;
	}

}
