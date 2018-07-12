package com.puresoltechnologies.toolshed.client;

import java.util.Set;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.toolshed.server.api.kpis.KPIDefinition;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeService;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;

public class NodeServiceClient implements NodeService {

    private final WebTarget webTarget;

    public NodeServiceClient(WebTarget webTarget) {
	this.webTarget = webTarget;
    }

    @Override
    public Set<NodeInformation> getNodes() {
	Builder request = webTarget.path("rest").path("nodes").request(MediaType.APPLICATION_JSON);
	return request.get(new GenericType<Set<NodeInformation>>() {
	});
    }

    @Override
    public Set<ProcessInformation> getProcesses(String nodeName) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ProcessInformation getProcess(String nodeName, String pid) {
	// TODO Auto-generated method stub
	return null;
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
