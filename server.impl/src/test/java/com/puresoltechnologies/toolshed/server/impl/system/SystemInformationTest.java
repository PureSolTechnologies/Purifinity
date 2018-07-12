package com.puresoltechnologies.toolshed.server.impl.system;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;

public class SystemInformationTest {

    @Test
    public void testGetProcessInformation() {
	Set<ProcessInformation> processInformation = SystemInformation.getInstance().getProcessInformation();
	assertNotNull(processInformation);
	assertFalse(processInformation.isEmpty());
    }

}
