package com.puresoltechnologies.toolshed.test.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.client.ToolShedCient;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeService;
import com.puresoltechnologies.toolshed.server.impl.system.SystemInformation;
import com.puresoltechnologies.toolshed.test.AbstractToolShedServerTest;

public class ClientIT extends AbstractToolShedServerTest {

    private static NodeService nodeService = null;

    @BeforeAll
    public static void initializeNodeServiceClient() {
	ToolShedCient client = getClient();
	nodeService = client.getNodeService();
    }

    @Test
    public void testGetNodes() {
	Collection<NodeInformation> nodes = nodeService.getNodes();
	assertNotNull(nodes);
	assertFalse(nodes.isEmpty());
    }

    @Test
    public void testGetNode() {
	NodeDetails node = nodeService.getNode(SystemInformation.getNodeName());
	assertNotNull(node);
	assertEquals(node, SystemInformation.getInstance().getNodeDetails());
    }
}
