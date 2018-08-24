package com.puresoltechnologies.toolshed.server.impl.system;

import java.util.Set;

import com.puresoltechnologies.toolshed.server.api.nodes.MemoryInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;
import com.puresoltechnologies.toolshed.server.api.system.SystemLoad;

public interface SystemInformationProvider {

    public NodeInformation getNodeInformation();

    public NodeDetails getNodeDetails();

    public SystemLoad getLoad();

    public MemoryInformation getMemoryInformation();

    public Set<ProcessInformation> getProcessInformation();

    public ProcessDetails getProcessDetails(int pid);

}
