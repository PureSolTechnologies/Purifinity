package com.puresoltechnologies.purifinity.server.core.api.analysis.jobs;

public class ProcessState {

	private final String name;
	private final String status;
	private final String step;
	private final long current;
	private final long max;

	public ProcessState(String name, String status, String step, long current,
			long max) {
		super();
		this.name = name;
		this.status = status;
		this.step = step;
		this.current = current;
		this.max = max;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public String getStep() {
		return step;
	}

	public long getCurrent() {
		return current;
	}

	public long getMax() {
		return max;
	}

}
