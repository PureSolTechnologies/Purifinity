package com.puresoltechnologies.toolshed.server.impl.machines;

import java.util.ArrayList;
import java.util.Collection;

import com.puresoltechnologies.toolshed.server.api.MachinesService;
import com.puresoltechnologies.toolshed.server.api.Node;

public class MachinesServiceImpl implements MachinesService {

    @Override
    public Collection<Node> getNodes() {
	return new ArrayList<>();
    }

}
