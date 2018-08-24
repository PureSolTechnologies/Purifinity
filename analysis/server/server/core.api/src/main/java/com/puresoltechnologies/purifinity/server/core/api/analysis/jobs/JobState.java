package com.puresoltechnologies.purifinity.server.core.api.analysis.jobs;

import java.util.ArrayList;
import java.util.List;

public class JobState {

	private final long jobId;
	private final String projectId;
	private final String name;
	private final List<JobStepState> stepStates = new ArrayList<>();

	public JobState(long jobId, String projectId, String name,
			List<JobStepState> stepStates) {
		super();
		this.jobId = jobId;
		this.projectId = projectId;
		this.name = name;
		this.stepStates.addAll(stepStates);
	}

	public long getJobId() {
		return jobId;
	}

	public String getProjectId() {
		return projectId;
	}

	public String getName() {
		return name;
	}

	public List<JobStepState> getStepStates() {
		return stepStates;
	}

}
