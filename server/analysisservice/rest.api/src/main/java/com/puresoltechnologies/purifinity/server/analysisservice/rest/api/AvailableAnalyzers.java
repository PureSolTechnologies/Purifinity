package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

public class AvailableAnalyzers {

	private final Set<AnalyzerInformation> analyzers;

	public AvailableAnalyzers(
			@JsonProperty("analyzers") Set<AnalyzerInformation> analyzers) {
		super();
		this.analyzers = analyzers;
	}

	public Set<AnalyzerInformation> getAnalyzers() {
		return analyzers;
	}

}
