package com.puresoltechnologies.purifinity.server.core.api.analysis.states;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class AnalysisProcessStatusInformation implements Serializable {

    private static final long serialVersionUID = -7752050012862389505L;

    private final Date started;
    private final UUID projectUUID;
    private final UUID runUUID;
    private final AnalysisProcessState state;
    private final Date lastProgress;

    public AnalysisProcessStatusInformation(Date started, UUID projectUUID,
	    UUID runUUID, AnalysisProcessState state, Date lastProgress) {
	super();
	this.started = started;
	this.projectUUID = projectUUID;
	this.runUUID = runUUID;
	this.state = state;
	this.lastProgress = lastProgress;
    }

    public Date getStarted() {
	return started;
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

    public Date getLastProgress() {
	return lastProgress;
    }

}
