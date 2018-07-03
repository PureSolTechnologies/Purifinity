package com.puresoltechnologies.toolshed.server.api.nodes;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NIC {

    private final String displayName;
    private final String name;
    private final int hashCode;

    @JsonCreator
    public NIC( //
	    @JsonProperty("displayName") String displayName, //
	    @JsonProperty("name") String name //
    ) {
	super();
	Objects.requireNonNull(displayName, "displayName must not be null!");
	Objects.requireNonNull(name, "name must not be null!");
	this.displayName = displayName;
	this.name = name;
	this.hashCode = this.name.hashCode();
    }

    public String getDisplayName() {
	return displayName;
    }

    public String getName() {
	return name;
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
	NIC other = (NIC) obj;
	if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return displayName;
    }

}
