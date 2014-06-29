package com.puresoltechnologies.purifinity.server.core.api.analysis.states;

import com.puresoltechnologies.purifinity.framework.commons.utils.statemodel.Transition;

public enum AnalysisProcessTransition implements
		Transition<AnalysisProcessState> {

	QUEUE_FOR_STORAGE {
		@Override
		public String getName() {
			return "queue for storage";
		}

		@Override
		public String getDescription() {
			return "The project is going to be queued for project storage into database.";
		}

		@Override
		public AnalysisProcessState getFinalState() {
			return AnalysisProcessState.QUEUED_FOR_STORAGE;
		}
	},
	START_STORAGE {
		@Override
		public String getName() {
			return "start storage";
		}

		@Override
		public String getDescription() {
			return "The project is going to be started for storage.";
		}

		@Override
		public AnalysisProcessState getFinalState() {
			return AnalysisProcessState.STORING;
		}
	},
	QUEUE_FOR_ANALYSIS {
		@Override
		public String getName() {
			return "queue for analysis";
		}

		@Override
		public String getDescription() {
			return "The project is going to be queued for analysis.";
		}

		@Override
		public AnalysisProcessState getFinalState() {
			return AnalysisProcessState.QUEUED_FOR_ANALYSIS;
		}
	},
	START_ANALYSIS {
		@Override
		public String getName() {
			return "start analysis";
		}

		@Override
		public String getDescription() {
			return "The project is going to be started for analysis.";
		}

		@Override
		public AnalysisProcessState getFinalState() {
			return AnalysisProcessState.ANALYZING;
		}
	},
	QUEUE_FOR_EVALUATION {
		@Override
		public String getName() {
			return "queue for evaluation";
		}

		@Override
		public String getDescription() {
			return "The project is going to be queued for evaluation.";
		}

		@Override
		public AnalysisProcessState getFinalState() {
			return AnalysisProcessState.QUEUED_FOR_EVALUATION;
		}
	},
	START_EVALUATION {
		@Override
		public String getName() {
			return "start evaluation";
		}

		@Override
		public String getDescription() {
			return "The project is going to be started for evaluation.";
		}

		@Override
		public AnalysisProcessState getFinalState() {
			return AnalysisProcessState.EVALUATING;
		}
	},
	FINISH {
		@Override
		public String getName() {
			return "finish";
		}

		@Override
		public String getDescription() {
			return "The project is going to be finished.";
		}

		@Override
		public AnalysisProcessState getFinalState() {
			return AnalysisProcessState.FINISHED;
		}
	},
	ABORT {
		@Override
		public String getName() {
			return "abort";
		}

		@Override
		public String getDescription() {
			return "The project is going to be aborted.";
		}

		@Override
		public AnalysisProcessState getFinalState() {
			return AnalysisProcessState.ABORTED;
		}
	},
	FAIL {
		@Override
		public String getName() {
			return "fail";
		}

		@Override
		public String getDescription() {
			return "The project is going to failed state.";
		}

		@Override
		public AnalysisProcessState getFinalState() {
			return AnalysisProcessState.FAILED;
		}
	};

}
