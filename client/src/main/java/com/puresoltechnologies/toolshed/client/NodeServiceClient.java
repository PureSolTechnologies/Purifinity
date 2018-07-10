package com.puresoltechnologies.toolshed.client;

import java.util.Collection;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.toolshed.server.api.nodes.Node;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeService;

public class NodeServiceClient implements NodeService {

    private final WebTarget webTarget;

    public NodeServiceClient(WebTarget webTarget) {
	this.webTarget = webTarget;
    }

    @Override
    public Collection<Node> getNodes() {
	Builder request = webTarget.path("rest").path("nodes").request(MediaType.APPLICATION_JSON);
	return request.get(new GenericType<Collection<Node>>() {
	});
    }

}
