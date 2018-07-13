package com.puresoltechnologies.toolshed.server.impl.system;

import java.util.Set;

import com.puresoltechnologies.toolshed.server.api.nodes.MemoryInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;

public interface SystemInformationProvider {

    public NodeInformation getNodeInformation();

    public NodeDetails getNodeDetails();

    public MemoryInformation getMemoryInformation();

    public Set<ProcessInformation> getProcessInformation();

    public ProcessDetails getProcessDetails(int pid);

}
