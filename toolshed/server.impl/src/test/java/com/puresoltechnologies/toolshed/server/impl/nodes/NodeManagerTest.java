package com.puresoltechnologies.toolshed.server.impl.nodes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;

public class NodeManagerTest {

    @Test
    public void testInitialNodesList() {
	Set<NodeInformation> nodes = NodeManager.getInstance().getNodes();
	assertNotNull(nodes, "Nodes list must not be null.");
	assertEquals(1, nodes.size(), "Only one node for the  local node is expected.");
	NodeInformation localNode = nodes.iterator().next();
	assertNotNull(localNode.getName());
	assertNotNull(localNode.getOS());
	assertNotNull(localNode.getOSVersion());
	assertNotNull(localNode.getArchitecture());
	assertTrue(localNode.getCpus() > 0);
    }

}
