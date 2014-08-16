package com.puresoltechnologies.purifinity.server.core.api.analysis.states;

import java.util.List;
import java.util.UUID;

import com.puresoltechnologies.purifinity.server.common.utils.statemodel.Transition;

public interface AnalysisProcessStateTracker {

	public void startProcess(UUID projectUUID);

	public List<AnalysisProcessStatusInformation> readProcessStates();

	public AnalysisProcessStatusInformation readProcessState(UUID projectUUID);

	public boolean changeProcessState(UUID projectUUID, UUID runUUID,
			Transition<AnalysisProcessState> transition);

	public void stopProcess(UUID projectUUID);
}
