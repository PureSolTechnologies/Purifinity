package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import org.codehaus.jackson.annotate.JsonProperty;

public class AnalyzerInformation {

	private final String name;
	private final String version;
	private final String description;

	public AnalyzerInformation(@JsonProperty("name") String name,
			@JsonProperty("version") String version,
			@JsonProperty("description") String description) {
		super();
		this.name = name;
		this.version = version;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getDescription() {
		return description;
	}

}
