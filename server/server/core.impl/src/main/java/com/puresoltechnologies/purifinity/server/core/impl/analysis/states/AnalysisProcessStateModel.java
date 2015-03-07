package com.puresoltechnologies.purifinity.server.core.impl.analysis.states;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessState;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessTransition;
import com.puresoltechnologies.statemodel.AbstractStateModel;

public class AnalysisProcessStateModel extends
		AbstractStateModel<AnalysisProcessState, AnalysisProcessTransition> {

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
		endStates.add(AnalysisProcessState.ABORT_REQUESTED);
		endStates.add(AnalysisProcessState.FAILED);
		return endStates;
	}

	@Override
	public Set<AnalysisProcessState> getVertices() {
		throw new IllegalStateException(
				"This state model does not support graph traversals.");
	}

}
