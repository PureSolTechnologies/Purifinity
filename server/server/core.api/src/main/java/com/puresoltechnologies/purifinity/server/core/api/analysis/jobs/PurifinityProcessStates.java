package com.puresoltechnologies.purifinity.server.core.api.analysis.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurifinityProcessStates {

	private final List<ProcessState> processStates = new ArrayList<ProcessState>();
	private final Date time;

	public PurifinityProcessStates(Date time) {
		super();
		this.time = time;
	}

	public PurifinityProcessStates(@JsonProperty("time") Date time,
			@JsonProperty("processStates") List<ProcessState> processStates) {
		super();
		this.time = time;
		this.processStates.addAll(processStates);
	}

	public Date getTime() {
		return time;
	}

	public List<ProcessState> getProcessStates() {
		return processStates;
	}

	public void addProcessState(ProcessState processState) {
		processStates.add(processState);
	}

}
