package com.puresoltechnologies.purifinity.server.client.analysisservice;

import java.io.IOException;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AvailableAnalyzers;
import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.RepositoryTypes;
import com.puresoltechnologies.purifinity.server.common.rest.AbstractRestClient;

public class AnalysisServiceClient extends
		AbstractRestClient<AnalysisServiceRestInterface> {

	public AnalysisServiceClient() {
		super(AnalysisServiceRestInterface.class);
	}

	public AvailableAnalyzers getAnalyzers() throws IOException {
		return getProxy().getAnalyzers();
	}

	public RepositoryTypes getRepositoryTypes() throws IOException {
		return getProxy().getRepositoryTypes();
	}

}
