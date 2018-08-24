package com.puresoltechnologies.toolshed.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.puresoltechnologies.toolshed.server.api.nodes.NodeService;

public class ToolShedCient implements Closeable {

    private final NodeServiceClient nodeService;

    private final Client client;

    public ToolShedCient(URI baseUri) {
	client = ClientBuilder.newClient();

	WebTarget webTarget = client.target(baseUri);
	nodeService = new NodeServiceClient(webTarget);

    }

    public NodeService getNodeService() {
	return nodeService;
    }

    @Override
    public void close() throws IOException {
	client.close();
    }

}
