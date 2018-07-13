package com.puresoltechnologies.toolshed.server.api.nodes;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeInformation {

    private final String name;
    private final OS os;
    private final String architecture;
    private final String osVersion;
    private final int cpus;
    private final long memTotal;
    private final long swapTotal;
    private final int hashCode;

    @JsonCreator
    public NodeInformation( //
	    @JsonProperty("name") String name, //
	    @JsonProperty("oS") OS os, //
	    @JsonProperty("architecture") String architecture, //
	    @JsonProperty("osversion") String osVersion, //
	    @JsonProperty("cpus") int cpus, //
	    @JsonProperty("memTotal") long memTotal, //
	    @JsonProperty("swapTotal") long swapTotal //
    ) {
	super();
	Objects.requireNonNull(name, "name must not be null!");
	this.name = name;
	this.os = os;
	this.architecture = architecture;
	this.osVersion = osVersion;
	this.cpus = cpus;
	this.memTotal = memTotal;
	this.swapTotal = swapTotal;
	this.hashCode = Objects.hash(name, os, architecture, osVersion, cpus, memTotal, swapTotal);
    }

    public String getName() {
	return name;
    }

    public OS getOS() {
	return os;
    }

    public String getArchitecture() {
	return architecture;
    }

    public String getOSVersion() {
	return osVersion;
    }

    public int getCpus() {
	return cpus;
    }

    public long getMemTotal() {
	return memTotal;
    }

    public long getSwapTotal() {
	return swapTotal;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	NodeInformation other = (NodeInformation) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return name + " (" + os.name() + " " + osVersion + ", " + cpus + " CPUs, " + architecture + ")";
    }
}
