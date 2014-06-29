package com.puresoltechnologies.purifinity.server.ui;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessState;

public class ActiveAnalysisProcess implements Serializable {

    private static final long serialVersionUID = 7178122290973837197L;

    private final UUID projectUUID;
    private final UUID runUUID;
    private final Date started;
    private final String projectName;
    private final AnalysisProcessState state;
    private final Date lastProgress;

    public ActiveAnalysisProcess(UUID projectUUID, UUID runUUID, Date started,
	    String projectName, AnalysisProcessState state, Date lastProgress) {
	super();
	this.projectUUID = projectUUID;
	this.runUUID = runUUID;
	this.started = started;
	this.projectName = projectName;
	this.state = state;
	this.lastProgress = lastProgress;
    }

    public UUID getProjectUUID() {
	return projectUUID;
    }

    public UUID getRunUUID() {
	return runUUID;
    }

    public Date getStarted() {
	return started;
    }

    public String getProjectName() {
	return projectName;
    }

    public AnalysisProcessState getState() {
	return state;
    }

    public Date getLastProgress() {
	return lastProgress;
    }

}
