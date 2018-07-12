package com.puresoltechnologies.toolshed.server.impl.system;

import java.util.Set;

import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;

public interface SystemInformationProvider {

    public Set<ProcessInformation> getProcessInformation();

    public ProcessInformation getProcessInformation(int pid);

}
