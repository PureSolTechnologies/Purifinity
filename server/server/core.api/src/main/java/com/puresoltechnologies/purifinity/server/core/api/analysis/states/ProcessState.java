package com.puresoltechnologies.purifinity.server.core.api.analysis.states;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains information about a single process state.
 * 
 * The {@link Comparable} interfaceis used to sort mutiple states in
 * {@link PurifinityProcessStates} alphabetically.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class ProcessState implements Serializable, Comparable<ProcessState> {

    private static final long serialVersionUID = 1L;

    private final String processId;
    private final String name;
    private final AnalysisProcessState status;
    private final int current;
    private final int max;
    private final String unit;

    /**
     * Default constructor for Jackson.
     */
    public ProcessState() {
	processId = null;
	name = null;
	status = null;
	current = -1;
	max = -1;
	unit = null;
    }

    @JsonCreator
    public ProcessState(
	    @JsonProperty("processId") String processId,
	    @JsonProperty("name") String name,//
	    @JsonProperty("status") AnalysisProcessState status,
	    @JsonProperty("current") int current, //
	    @JsonProperty("max") int max, //
	    @JsonProperty("unit") String unit) {
	super();
	this.processId = processId;
	this.name = Objects.requireNonNull(name, "name must not be null.");
	this.status = Objects
		.requireNonNull(status, "status must not be null.");
	this.max = max;
	this.current = current;
	this.unit = Objects.requireNonNull(unit, "unit must not be null.");
    }

    public String getProcessId() {
	return processId;
    }

    public String getName() {
	return name;
    }

    public AnalysisProcessState getStatus() {
	return status;
    }

    public int getCurrent() {
	return current;
    }

    public int getMax() {
	return max;
    }

    public String getUnit() {
	return unit;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + current;
	result = prime * result + max;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result
		+ ((processId == null) ? 0 : processId.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
	ProcessState other = (ProcessState) obj;
	if (current != other.current)
	    return false;
	if (max != other.max)
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (processId == null) {
	    if (other.processId != null)
		return false;
	} else if (!processId.equals(other.processId))
	    return false;
	if (status != other.status)
	    return false;
	if (unit == null) {
	    if (other.unit != null)
		return false;
	} else if (!unit.equals(other.unit))
	    return false;
	return true;
    }

    @Override
    public int compareTo(ProcessState o) {
	int nameComparison = name.compareTo(o.name);
	if (nameComparison != 0) {
	    return nameComparison;
	}
	return status.compareTo(o.status);
    }

    @Override
    public String toString() {
	return name + " (" + status + ") " + current + "/" + max + " " + unit;
    }
}
