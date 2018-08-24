package com.puresoltechnologies.toolshed.server.impl.dashboards;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DashboardInformation {

    private final String id;
    private final String name;
    private final int hashCode;

    @JsonCreator
    public DashboardInformation( //
	    @JsonProperty("id") String id, //
	    @JsonProperty("name") String name //
    ) {
	this.id = id;
	this.name = name;
	this.hashCode = Objects.hash(id, name);
    }

    public String getId() {
	return id;
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
	DashboardInformation other = (DashboardInformation) obj;
	if (hashCode != other.hashCode)
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

}
