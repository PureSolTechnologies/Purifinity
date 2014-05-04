package com.puresoltechnologies.purifinity.server.analysisservice.rest.impl;

import java.io.IOException;
import java.util.HashSet;

import javax.ws.rs.core.Response;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AvailableAnalyzers;
import com.puresoltechnologies.purifinity.server.common.rest.JSONMapper;

public class AnalysisServiceRestService implements AnalysisServiceRestInterface {

	@Override
	public Response getAnalyzers() throws IOException {
		AvailableAnalyzers availableAnalyzers = new AvailableAnalyzers(
				new HashSet<AnalyzerInformation>());
		return Response.ok()
				.entity(JSONMapper.toJSONString(availableAnalyzers)).build();
	}

}
