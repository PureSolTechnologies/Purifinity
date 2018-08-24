package com.puresoltechnologies.purifinity.server.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PurifinityServerStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long startTime;
	private final long uptime;
	private final long usedMemory;
	private final long allocatedMemory;
	private final long maxMemory;
	private final double averageLoad;
	private final int availableCPUs;

	/**
	 * Default constructor for Jackson.
	 */
	public PurifinityServerStatus() {
		this.startTime = -1;
		this.uptime = -1;
		this.usedMemory = -1;
		this.allocatedMemory = -1;
		this.maxMemory = -1;
		this.averageLoad = -1;
		this.availableCPUs = -1;
	}

	@JsonCreator
	public PurifinityServerStatus(@JsonProperty("startTime") long startTime,
			@JsonProperty("uptime") long uptime,
			@JsonProperty("usedMemory") long usedMemory,
			@JsonProperty("allocatedMemory") long allocatedMemory,
			@JsonProperty("maxMemory") long maxMemory,
			@JsonProperty("averageLoad") double averageLoad,
			@JsonProperty("availableCPUs") int availableCPUs) {
		super();
		this.startTime = startTime;
		this.uptime = uptime;
		this.usedMemory = usedMemory;
		this.allocatedMemory = allocatedMemory;
		this.maxMemory = maxMemory;
		this.averageLoad = averageLoad;
		this.availableCPUs = availableCPUs;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getUptime() {
		return uptime;
	}

	public long getUsedMemory() {
		return usedMemory;
	}

	public long getAllocatedMemory() {
		return allocatedMemory;
	}

	public long getMaxMemory() {
		return maxMemory;
	}

	public double getAverageLoad() {
		return averageLoad;
	}

	public int getAvailableCPUs() {
		return availableCPUs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (allocatedMemory ^ (allocatedMemory >>> 32));
		result = prime * result + availableCPUs;
		long temp;
		temp = Double.doubleToLongBits(averageLoad);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (maxMemory ^ (maxMemory >>> 32));
		result = prime * result + (int) (startTime ^ (startTime >>> 32));
		result = prime * result + (int) (uptime ^ (uptime >>> 32));
		result = prime * result + (int) (usedMemory ^ (usedMemory >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurifinityServerStatus other = (PurifinityServerStatus) obj;
		if (allocatedMemory != other.allocatedMemory)
			return false;
		if (availableCPUs != other.availableCPUs)
			return false;
		if (Double.doubleToLongBits(averageLoad) != Double
				.doubleToLongBits(other.averageLoad))
			return false;
		if (maxMemory != other.maxMemory)
			return false;
		if (startTime != other.startTime)
			return false;
		if (uptime != other.uptime)
			return false;
		if (usedMemory != other.usedMemory)
			return false;
		return true;
	}
}
