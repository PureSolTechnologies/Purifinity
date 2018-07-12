package com.puresoltechnologies.toolshed.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.client.ToolShedCient;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeService;

public class HostStreamIT extends AbstractToolShedServerTest {

    @Test
    public void test() {
	ToolShedCient client = getClient();
	NodeService nodeService = client.getNodeService();
	Collection<NodeInformation> nodes = nodeService.getNodes();
	assertNotNull(nodes);
	assertFalse(nodes.isEmpty());
    }

}
