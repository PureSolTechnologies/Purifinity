package com.puresoltechnologies.toolshed.server.api.nodes;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeDetails extends NodeInformation {

    private final Set<NIC> nics = new HashSet<>();

    public NodeDetails( //
	    @JsonProperty("name") String name, //
	    @JsonProperty("oS") OS os, //
	    @JsonProperty("architecture") String architecture, //
	    @JsonProperty("osversion") String osVersion, //
	    @JsonProperty("cpus") int cpus, //
	    @JsonProperty("memTotal") long memTotal, //
	    @JsonProperty("swapTotal") long swapTotal, //
	    @JsonProperty("nics") Set<NIC> nics //
    ) {
	super(name, os, architecture, osVersion, cpus, memTotal, swapTotal);
	this.nics.addAll(nics);
    }

    public Set<NIC> getNics() {
	return nics;
    }

}
