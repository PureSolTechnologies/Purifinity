package com.puresoltechnologies.toolshed.server.impl.nodes;

import java.util.Set;

import com.puresoltechnologies.toolshed.server.api.kpis.KPIDefinition;
import com.puresoltechnologies.toolshed.server.api.nodes.Node;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeService;

public class NodeServiceImpl implements NodeService {

    @Override
    public Set<Node> getNodes() {
	return NodeManager.getNodes();
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
