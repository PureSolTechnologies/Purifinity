package com.puresoltechnologies.toolshed.server.api.nodes;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessInformation {

    private final int pid;
    private final int ppid;
    private final String name;
    private final ProcessStatus status;
    private final String cmdline;
    private final int hashCode;

    @JsonCreator
    public ProcessInformation(@JsonProperty("pid") int pid, //
	    @JsonProperty("ppid") int ppid, //
	    @JsonProperty("name") String name, //
	    @JsonProperty("status") ProcessStatus status, //
	    @JsonProperty("cmdline") String cmdline //
    ) {
	super();
	this.pid = pid;
	this.ppid = ppid;
	this.name = name;
	this.status = status;
	this.cmdline = cmdline;
	this.hashCode = Objects.hash(pid, ppid, name, status, cmdline);
    }

    public int getPid() {
	return pid;
    }

    public int getPpid() {
	return ppid;
    }

    public String getName() {
	return name;
    }

    public ProcessStatus getStatus() {
	return status;
    }

    public String getCmdline() {
	return cmdline;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append(pid);
	builder.append(" ");
	builder.append(ppid);
	builder.append(" ");
	builder.append(name);
	builder.append(" ");
	builder.append(status.name());
	builder.append(" ");
	builder.append(cmdline);
	return builder.toString();
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
	ProcessInformation other = (ProcessInformation) obj;
	if (hashCode != other.hashCode)
	    return false;
	if (cmdline == null) {
	    if (other.cmdline != null)
		return false;
	} else if (!cmdline.equals(other.cmdline))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (pid != other.pid)
	    return false;
	if (ppid != other.ppid)
	    return false;
	if (status != other.status)
	    return false;
	return true;
    }

}
