package com.puresoltechnologies.purifinity.server.client.analysisservice;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.UUID;

import com.puresoltechnologies.purifinity.server.analysisservice.rest.api.AnalysisServiceRestInterface;
import com.puresoltechnologies.purifinity.server.common.rest.AbstractRestClient;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

public class AnalysisServiceClient extends
		AbstractRestClient<AnalysisServiceRestInterface> {
	public static AnalysisServiceClient getInstance() {

		try {
			return new AnalysisServiceClient();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Unknown URI for Analysis Service.", e);
		}
	}

	private AnalysisServiceClient() throws URISyntaxException {
		super(new URI("http://localhost:8080/purifinityserver/rest"),
				AnalysisServiceRestInterface.class);
	}

	public Collection<AnalyzerInformation> getAnalyzers() throws IOException {
		return getProxy().getAnalyzers();
	}

	public Collection<RepositoryType> getRepositoryTypes() throws IOException {
		return getProxy().getRepositoryTypes();
	}

	public void triggerNewAnalysisRun(UUID uuid) {
		getProxy().triggerNewAnalysisRun(uuid);
	}

}
