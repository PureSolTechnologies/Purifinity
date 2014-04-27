package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisStoreServiceRestInterface;
import com.puresoltechnologies.purifinity.server.common.rest.JSONMapper;

public class AnalysisStoreServiceRestService implements
		AnalysisStoreServiceRestInterface {

	@Inject
	private Logger logger;

	@Inject
	private AnalysisStore analysisStore;

	@Override
	public Response createAnalysisProject(String string) {
		try {
			com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisProjectSettings settingsREST = JSONMapper
					.fromJSONString(
							string,
							com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisProjectSettings.class);
			AnalysisProjectSettings settings = AnalysisProjectSettingsConverter
					.fromREST(settingsREST);
			analysisStore.createAnalysisProject(settings);
			return Response.status(Status.CREATED).build();
		} catch (IOException | AnalysisStoreException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public Response getProjects() {
		return Response.status(Status.OK).entity("Hello, world!").build();
	}
}
