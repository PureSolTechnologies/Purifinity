package com.puresoltechnologies.purifinity.server.core.impl.analysis.states;

import java.util.UUID;

public class AnalysisProcessStatusInformation {

	private final UUID projectUUID;
	private final UUID runUUID;
	private final AnalysisProcessState state;

	public AnalysisProcessStatusInformation(UUID projectUUID, UUID runUUID,
			AnalysisProcessState state) {
		super();
		this.projectUUID = projectUUID;
		this.runUUID = runUUID;
		this.state = state;
	}

	public UUID getProjectUUID() {
		return projectUUID;
	}

	public UUID getRunUUID() {
		return runUUID;
	}

	public AnalysisProcessState getState() {
		return state;
	}

}
