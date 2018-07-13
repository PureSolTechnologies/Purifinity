package com.puresoltechnologies.toolshed.server.impl.system;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.server.api.nodes.MemoryInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;

public class SystemInformationTest {

    @Test
    public void testGetNodeInformation() {
	NodeInformation nodeInformation = SystemInformation.getInstance().getNodeInformation();
	assertNotNull(nodeInformation);
    }

    @Test
    public void testGetNodeDetails() {
	NodeDetails nodeDetails = SystemInformation.getInstance().getNodeDetails();
	assertNotNull(nodeDetails);
    }

    @Test
    public void testGetMemoryInformation() {
	MemoryInformation nodeInformation = SystemInformation.getInstance().getMemoryInformation();
	assertNotNull(nodeInformation);
    }

    @Test
    public void testGetProcessInformation() {
	Set<ProcessInformation> processInformation = SystemInformation.getInstance().getProcessInformation();
	assertNotNull(processInformation);
	assertFalse(processInformation.isEmpty());
    }

}
