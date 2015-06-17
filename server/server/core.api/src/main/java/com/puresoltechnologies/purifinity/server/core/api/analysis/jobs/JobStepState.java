package com.puresoltechnologies.purifinity.server.core.api.analysis.jobs;

public class JobStepState {

	private final String name;
	private final String status;
	private final long current;
	private final long max;

	public JobStepState(String name, String status, long current, long max) {
		super();
		this.name = name;
		this.status = status;
		this.current = current;
		this.max = max;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public long getCurrent() {
		return current;
	}

	public long getMax() {
		return max;
	}

}
