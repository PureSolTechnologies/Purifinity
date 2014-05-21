package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class AnalysisProject implements Serializable {

	private static final long serialVersionUID = 5059143637226359030L;

	private final AnalysisProjectInformation information;
	private final AnalysisProjectSettings settings;

	public AnalysisProject(
			@JsonProperty("information") AnalysisProjectInformation information,
			@JsonProperty("settings") AnalysisProjectSettings settings) {
		super();
		this.information = information;
		this.settings = settings;
	}

	public AnalysisProjectInformation getInformation() {
		return information;
	}

	public AnalysisProjectSettings getSettings() {
		return settings;
	}

	@Override
	public String toString() {
		return getSettings().getName() + ": (" + getInformation().getUUID()
				+ ")";
	}
}
