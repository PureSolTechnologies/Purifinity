package com.puresoltechnologies.toolshed.server.impl.nodes;

import java.util.Collection;

import com.puresoltechnologies.toolshed.server.api.nodes.Node;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeService;

public class MachinesServiceImpl implements NodeService {

    @Override
    public Collection<Node> getNodes() {
	return NodeManager.getNodes();
    }

}
