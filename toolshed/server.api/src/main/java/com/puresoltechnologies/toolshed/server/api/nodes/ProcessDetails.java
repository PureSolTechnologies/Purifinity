package com.puresoltechnologies.toolshed.server.api.nodes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessDetails extends ProcessInformation {

    public ProcessDetails( //
	    @JsonProperty("pid") int pid, //
	    @JsonProperty("ppid") int ppid, //
	    @JsonProperty("name") String name, //
	    @JsonProperty("status") ProcessStatus status, //
	    @JsonProperty("cmdline") String cmdline //
    ) {
	super(pid, ppid, name, status, cmdline);
    }

}
