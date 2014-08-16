package com.puresoltechnologies.purifinity.server.core.api.analysis.states;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.purifinity.server.common.utils.statemodel.State;
import com.puresoltechnologies.purifinity.server.common.utils.statemodel.Transition;

public enum AnalysisProcessState implements State<AnalysisProcessState> {

	QUEUED_FOR_START {
		@Override
		public String getName() {
			return "queued for start";
		}

		@Override
		public String getDescription() {
			return "The project is queued for processing.";
		}

		@Override
		public Set<Transition<AnalysisProcessState>> getTransitions() {
			Set<Transition<AnalysisProcessState>> transitions = new HashSet<Transition<AnalysisProcessState>>();
			transitions.add(AnalysisProcessTransition.QUEUE_FOR_STORAGE);
			transitions.add(AnalysisProcessTransition.FAIL);
			return transitions;
		}
	},
	QUEUED_FOR_STORAGE {
		@Override
		public String getName() {
			return "queued for storage";
		}

		@Override
		public String getDescription() {
			return "The project is queued for file reading and storage into database.";
		}

		@Override
		public Set<Transition<AnalysisProcessState>> getTransitions() {
			Set<Transition<AnalysisProcessState>> transitions = new HashSet<Transition<AnalysisProcessState>>();
			transitions.add(AnalysisProcessTransition.START_STORAGE);
			transitions.add(AnalysisProcessTransition.FAIL);
			return transitions;
		}
	},
	STORING {
		@Override
		public String getName() {
			return "storing";
		}

		@Override
		public String getDescription() {
			return "The project files are currently stored into the database.";
		}

		@Override
		public Set<Transition<AnalysisProcessState>> getTransitions() {
			Set<Transition<AnalysisProcessState>> transitions = new HashSet<Transition<AnalysisProcessState>>();
			transitions.add(AnalysisProcessTransition.QUEUE_FOR_ANALYSIS);
			transitions.add(AnalysisProcessTransition.FAIL);
			return transitions;
		}
	},
	QUEUED_FOR_ANALYSIS {
		@Override
		public String getName() {
			return "queued for analysis";
		}

		@Override
		public String getDescription() {
			return "The project is queued for analysis.";
		}

		@Override
		public Set<Transition<AnalysisProcessState>> getTransitions() {
			Set<Transition<AnalysisProcessState>> transitions = new HashSet<Transition<AnalysisProcessState>>();
			transitions.add(AnalysisProcessTransition.START_ANALYSIS);
			transitions.add(AnalysisProcessTransition.FAIL);
			return transitions;
		}
	},
	ANALYZING {
		@Override
		public String getName() {
			return "analyzing";
		}

		@Override
		public String getDescription() {
			return "The project is currently analyzed.";
		}

		@Override
		public Set<Transition<AnalysisProcessState>> getTransitions() {
			Set<Transition<AnalysisProcessState>> transitions = new HashSet<Transition<AnalysisProcessState>>();
			transitions.add(AnalysisProcessTransition.QUEUE_FOR_EVALUATION);
			transitions.add(AnalysisProcessTransition.FAIL);
			return transitions;
		}
	},
	QUEUED_FOR_EVALUATION {
		@Override
		public String getName() {
			return "queue for evaluation";
		}

		@Override
		public String getDescription() {
			return "The project is queued for evaluation.";
		}

		@Override
		public Set<Transition<AnalysisProcessState>> getTransitions() {
			Set<Transition<AnalysisProcessState>> transitions = new HashSet<Transition<AnalysisProcessState>>();
			transitions.add(AnalysisProcessTransition.START_EVALUATION);
			transitions.add(AnalysisProcessTransition.FAIL);
			return transitions;
		}
	},
	EVALUATING {
		@Override
		public String getName() {
			return "evaluating";
		}

		@Override
		public String getDescription() {
			return "The project is currently evaluated.";
		}

		@Override
		public Set<Transition<AnalysisProcessState>> getTransitions() {
			Set<Transition<AnalysisProcessState>> transitions = new HashSet<Transition<AnalysisProcessState>>();
			transitions.add(AnalysisProcessTransition.FINISH);
			transitions.add(AnalysisProcessTransition.FAIL);
			return transitions;
		}
	},
	FINISHED {
		@Override
		public String getName() {
			return "finished";
		}

		@Override
		public String getDescription() {
			return "The analysis process was finished successfully.";
		}

		@Override
		public Set<Transition<AnalysisProcessState>> getTransitions() {
			return new HashSet<Transition<AnalysisProcessState>>();
		}
	},
	ABORTED {
		@Override
		public String getName() {
			return "aborted";
		}

		@Override
		public String getDescription() {
			return "The analysis process was aborted.";
		}

		@Override
		public Set<Transition<AnalysisProcessState>> getTransitions() {
			return new HashSet<Transition<AnalysisProcessState>>();
		}
	},
	FAILED {
		@Override
		public String getName() {
			return "failed";
		}

		@Override
		public String getDescription() {
			return "The analysis process has failed.";
		}

		@Override
		public Set<Transition<AnalysisProcessState>> getTransitions() {
			return new HashSet<Transition<AnalysisProcessState>>();
		}
	};

}
