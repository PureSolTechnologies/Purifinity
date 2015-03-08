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
    private final String step;
    private final int current;
    private final int max;

    public AnalysisProcessStatusInformation(Date started, String projectId,
	    long runId, AnalysisProcessState state, Date lastProgress,
	    String step, int current, int max) {
	super();
	this.started = started;
	this.projectId = projectId;
	this.runId = runId;
	this.state = state;
	this.lastProgress = lastProgress;
	this.step = step;
	this.current = current;
	this.max = max;
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

    public String getStep() {
	return step;
    }

    public int getCurrent() {
	return current;
    }

    public int getMax() {
	return max;
    }

}
