package com.puresoltechnologies.toolshed.server.api.nodes;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MemoryInformation {

    private final long memTotal;
    private final long swapTotal;
    private final int hashCode;

    @JsonCreator
    public MemoryInformation( //
	    @JsonProperty("memTotal") long memTotal, //
	    @JsonProperty("swapTotal") long swapTotal //
    ) {
	this.memTotal = memTotal;
	this.swapTotal = swapTotal;
	this.hashCode = Objects.hash(memTotal, swapTotal);
    }

    public long getMemTotal() {
	return memTotal;
    }

    public long getSwapTotal() {
	return swapTotal;
    }

    @Override
    public int hashCode() {
	return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	MemoryInformation other = (MemoryInformation) obj;
	if (memTotal != other.memTotal)
	    return false;
	if (swapTotal != other.swapTotal)
	    return false;
	return true;
    }

}
