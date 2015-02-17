package com.puresoltechnologies.purifinity.server.core.api.analysis.states;

import java.io.Serializable;
import java.util.Date;

public class AnalysisProcessStatusInformation implements Serializable {

    private static final long serialVersionUID = -7752050012862389505L;

    private final Date started;
    private final String projectId;
    private final long runId;
    private final AnalysisProcessState state;
    private final Date lastProgress;

    public AnalysisProcessStatusInformation(Date started, String projectId,
	    long runId, AnalysisProcessState state, Date lastProgress) {
	super();
	this.started = started;
	this.projectId = projectId;
	this.runId = runId;
	this.state = state;
	this.lastProgress = lastProgress;
    }

    public Date getStarted() {
	return started;
    }

    public String getProjectId() {
	return projectId;
    }

    public long getRunId() {
	return runId;
    }

    public AnalysisProcessState getState() {
	return state;
    }

    public Date getLastProgress() {
	return lastProgress;
    }

}
