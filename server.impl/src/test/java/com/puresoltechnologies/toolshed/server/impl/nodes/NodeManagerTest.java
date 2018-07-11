package com.puresoltechnologies.toolshed.server.impl.nodes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.server.api.nodes.NIC;
import com.puresoltechnologies.toolshed.server.api.nodes.Node;

public class NodeManagerTest {

    @Test
    public void testInitialNodesList() {
	Set<Node> nodes = NodeManager.getNodes();
	assertNotNull(nodes, "Nodes list must not be null.");
	assertEquals(1, nodes.size(), "Only one node for the  local node is expected.");
	Node localNode = nodes.iterator().next();
	assertNotNull(localNode.getId());
	Set<NIC> nics = localNode.getNICs();
	assertNotNull(nics);
	assertTrue(nics.size() > 0);
    }

}
