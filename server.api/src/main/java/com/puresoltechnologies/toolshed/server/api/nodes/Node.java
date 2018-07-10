package com.puresoltechnologies.toolshed.server.api.nodes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Node {

    private final String id;
    private final OS os;
    private final String architecture;
    private final String osVersion;
    private final int cpus;
    private final Set<NIC> nics = new HashSet<>();

    @JsonCreator
    public Node( //
	    @JsonProperty("id") String id, //
	    @JsonProperty("oS") OS os, //
	    @JsonProperty("architecture") String architecture, //
	    @JsonProperty("osversion") String osVersion, //
	    @JsonProperty("cpus") int cpus, //
	    @JsonProperty("nics") Set<NIC> nics //
    ) {
	super();
	Objects.requireNonNull(id, "id must not be null!");
	Objects.requireNonNull(nics, "nics must not be null!");
	this.id = id;
	this.os = os;
	this.architecture = architecture;
	this.osVersion = osVersion;
	this.cpus = cpus;
	this.nics.addAll(nics);
    }

    public String getId() {
	return id;
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

    public Set<NIC> getNICs() {
	return Collections.unmodifiableSet(nics);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((nics == null) ? 0 : nics.hashCode());
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
	Node other = (Node) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (nics == null) {
	    if (other.nics != null)
		return false;
	} else if (!nics.equals(other.nics))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return id + " (" + os.name() + " " + osVersion + ", " + cpus + " CPUs, " + architecture + ")";
    }
}
