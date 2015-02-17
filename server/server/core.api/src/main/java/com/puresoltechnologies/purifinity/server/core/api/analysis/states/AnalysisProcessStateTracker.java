package com.puresoltechnologies.purifinity.server.core.api.analysis.states;

import java.util.List;

public interface AnalysisProcessStateTracker {

    public void startProcess(String projectId);

    public List<AnalysisProcessStatusInformation> readProcessStates();

    public AnalysisProcessStatusInformation readProcessState(String projectId);

    public boolean changeProcessState(String projectId, long runId,
	    AnalysisProcessTransition transition);

    public void stopProcess(String projectId);
}
