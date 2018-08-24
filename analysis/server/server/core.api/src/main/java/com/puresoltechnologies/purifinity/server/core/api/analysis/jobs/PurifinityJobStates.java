package com.puresoltechnologies.purifinity.server.core.api.analysis.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurifinityJobStates {

	private final List<JobState> jobStates = new ArrayList<JobState>();
	private final Date time;

	public PurifinityJobStates(Date time) {
		super();
		this.time = time;
	}

	public PurifinityJobStates(@JsonProperty("time") Date time,
			@JsonProperty("jobStates") List<JobState> jobStates) {
		super();
		this.time = time;
		this.jobStates.addAll(jobStates);
	}

	public Date getTime() {
		return time;
	}

	public List<JobState> getJobStates() {
		return jobStates;
	}

	public void addJobState(JobState jobState) {
		jobStates.add(jobState);
	}

}
