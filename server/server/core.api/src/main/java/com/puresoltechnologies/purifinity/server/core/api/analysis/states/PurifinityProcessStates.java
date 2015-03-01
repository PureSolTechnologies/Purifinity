package com.puresoltechnologies.purifinity.server.core.api.analysis.states;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PurifinityProcessStates {

    private final Date time;
    private final List<ProcessState> processStates = new ArrayList<>();

    /**
     * Default constructor for Jackson.
     */
    public PurifinityProcessStates() {
	this.time = null;
    }

    @JsonCreator
    public PurifinityProcessStates(@JsonProperty("time") Date time,
	    @JsonProperty("processes") List<ProcessState> processStates) {
	this.time = Objects.requireNonNull(time, "time must not be null.");
	if (processStates != null) {
	    this.processStates.addAll(processStates);
	}
	Collections.sort(this.processStates);
    }

    public Date getTime() {
	return time;
    }

    public List<ProcessState> getProcessStates() {
	return processStates;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((processStates == null) ? 0 : processStates.hashCode());
	result = prime * result + ((time == null) ? 0 : time.hashCode());
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
	PurifinityProcessStates other = (PurifinityProcessStates) obj;
	if (processStates == null) {
	    if (other.processStates != null)
		return false;
	} else if (!processStates.equals(other.processStates))
	    return false;
	if (time == null) {
	    if (other.time != null)
		return false;
	} else if (!time.equals(other.time))
	    return false;
	return true;
    }

}
