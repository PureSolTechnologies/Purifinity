package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.Collection;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;

public class AvailableAnalyzers {

	private final Collection<AnalyzerInformation> analyzers;

	public AvailableAnalyzers(
			@JsonProperty("analyzers") Collection<AnalyzerInformation> analyzers) {
		super();
		this.analyzers = analyzers;
	}

	public Collection<AnalyzerInformation> getAnalyzers() {
		return analyzers;
	}

}
