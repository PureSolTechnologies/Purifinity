package com.puresoltechnologies.purifinity.server.domain;

import java.io.Serializable;
import java.util.Objects;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

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

    private final String processName;
    private final int current;
    private final int max;
    private final String unit;

    /**
     * Default constructor for Jackson.
     */
    public ProcessState() {
	processName = null;
	current = -1;
	max = -1;
	unit = null;
    }

    @JsonCreator
    public ProcessState(@JsonProperty("processName") String processName,
	    @JsonProperty("current") int current, //
	    @JsonProperty("max") int max, //
	    @JsonProperty("unit") String unit) {
	super();
	this.processName = Objects.requireNonNull(processName,
		"processName must not be null.");
	this.max = max;
	this.current = current;
	this.unit = Objects.requireNonNull(unit, "unit must not be null.");
    }

    public String getProcessName() {
	return processName;
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
	result = prime * result
		+ ((processName == null) ? 0 : processName.hashCode());
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
	if (processName == null) {
	    if (other.processName != null)
		return false;
	} else if (!processName.equals(other.processName))
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
	return this.getProcessName().compareTo(o.getProcessName());
    }

    @Override
    public String toString() {
	return processName + " " + current + "/" + max + " " + unit;
    }
}
