package com.puresoltechnologies.purifinity.server.core.impl.analysis.states;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.purifinity.framework.commons.utils.statemodel.AbstractStateModel;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessState;

public class AnalysisProcessStateModel extends
		AbstractStateModel<AnalysisProcessState> {

	public AnalysisProcessStateModel() {
		super();
	}

	public AnalysisProcessStateModel(AnalysisProcessState state) {
		super(state);
	}

	@Override
	public AnalysisProcessState getStartState() {
		return AnalysisProcessState.QUEUED_FOR_START;
	}

	@Override
	public Set<AnalysisProcessState> getEndStates() {
		Set<AnalysisProcessState> endStates = new HashSet<>();
		endStates.add(AnalysisProcessState.FINISHED);
		endStates.add(AnalysisProcessState.ABORTED);
		endStates.add(AnalysisProcessState.FAILED);
		return endStates;
	}

}
