package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisStoreRestInterface;
import com.puresoltechnologies.purifinity.server.common.rest.JSONMapper;

public class AnalysisStoreRestService implements AnalysisStoreRestInterface {

	@Inject
	private Logger logger;

	@Inject
	private AnalysisStore analysisStore;

	@Override
	public Response createAnalysisProject(String string) {
		try {
			AnalysisProjectSettings settings = JSONMapper.fromJSONString(
					string, AnalysisProjectSettings.class);
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
