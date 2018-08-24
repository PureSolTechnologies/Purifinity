package com.puresoltechnologies.toolshed.server.impl.nodes;

import java.util.Set;

import com.puresoltechnologies.toolshed.server.api.kpis.KPIDefinition;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeService;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;

public class NodeServiceImpl implements NodeService {

    @Override
    public Set<NodeInformation> getNodes() {
	return NodeManager.getInstance().getNodes();
    }

    @Override
    public NodeDetails getNode(String nodeName) {
	return NodeManager.getInstance().getNode(nodeName);
    }

    @Override
    public Set<ProcessInformation> getProcesses(String nodeName) {
	return NodeManager.getInstance().getProcesses(nodeName);
    }

    @Override
    public ProcessInformation getProcess(String nodeName, String pid) {
	return NodeManager.getInstance().getProcess(nodeName, Integer.parseInt(pid));
    }

    @Override
    public Set<KPIDefinition> getKPIs(String nodeName) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Set<KPIDefinition> getCounters(String nodeName) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Set<KPIDefinition> getGauges(String nodeName) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Set<KPIDefinition> getHistorams(String nodeName) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Set<KPIDefinition> getMeters(String nodeName) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Set<KPIDefinition> getTimers(String nodeName) {
	// TODO Auto-generated method stub
	return null;
    }

}
