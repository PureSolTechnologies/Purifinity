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

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.ProgressObserver;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisStoreRestInterface;
import com.puresoltechnologies.purifinity.server.common.rest.JSONMapper;

public class AnalysisStoreClient implements AnalysisStore {

	private final AnalysisStoreRestInterface proxy;

	static {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}

	public static AnalysisStoreClient createInstance() {
		return new AnalysisStoreClient();
	}

	public AnalysisStoreClient() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget webTarget = client
				.target("http://localhost:8080/analysisservice");
		proxy = webTarget.proxy(AnalysisStoreRestInterface.class);
	}

	@Override
	public AnalysisProjectInformation createAnalysisProject(
			AnalysisProjectSettings settings) {
		try {
			String jsonString = JSONMapper.toJSONString(settings);
			Response response = proxy.createAnalysisProject(jsonString);
			System.err.println(response.getStatus());
			return null;
		} catch (IOException e) {
			throw new RuntimeException("Could not marshall JSON request.", e);
		}
	}

	@Override
	public List<AnalysisProjectInformation> readAllAnalysisProjectInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisProjectInformation readAnalysisProjectInformation(
			UUID projectUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAnalysisProject(UUID projectUUID) {
		// TODO Auto-generated method stub

	}

	@Override
	public AnalysisProjectSettings readAnalysisProjectSettings(
			UUID analysisProjectUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisProject readAnalysisProject(
			AnalysisProjectInformation information)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAnalysisProjectSettings(UUID projectUUID,
			AnalysisProjectSettings settings) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AnalysisRunInformation> readAllRunInformation(UUID projectUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRunInformation readAnalysisRun(UUID projectUUID,
			UUID analysisRunUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRun readAnalysisRun(
			AnalysisRunInformation analysisRunInformation)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRunInformation readLastAnalysisRun(UUID projectUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAnalysisRun(UUID projectUUID, UUID analysisRunUUID) {
		// TODO Auto-generated method stub

	}

	@Override
	public FileSearchConfiguration readSearchConfiguration(UUID analysisRunUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisFileTree readAnalysisFileTree(UUID projectUUID, UUID runUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisRunInformation createAnalysisRun(UUID analysisProjectUUID,
			Date startTime, long duration, String description,
			FileSearchConfiguration fileSearchConfiguration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeAnalysisFileTree(UUID projectUUID, UUID analysisRunUUID,
			AnalysisFileTree fileTree) throws AnalysisStoreException {
		// TODO Auto-generated method stub
	}

	@Override
	public void storeAnalysisFileTree(
			ProgressObserver<AnalysisStore> progressObserver, UUID projectUUID,
			UUID analysisRunUUID, AnalysisFileTree fileTree)
			throws AnalysisStoreException {
		// TODO Auto-generated method stub
	}

}
