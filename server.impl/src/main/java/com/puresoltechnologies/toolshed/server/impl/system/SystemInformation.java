package com.puresoltechnologies.toolshed.server.impl.system;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.toolshed.server.api.nodes.MemoryInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.OS;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;
import com.puresoltechnologies.toolshed.server.impl.system.linux.LinuxInformationProvider;

public class SystemInformation implements SystemInformationProvider {

    private static final Logger logger = LoggerFactory.getLogger(SystemInformation.class);

    private static final SystemInformation instance = new SystemInformation();

    public static String getNodeName() {
	try {
	    InetAddress localHost = InetAddress.getLocalHost();
	    return localHost.getCanonicalHostName();
	} catch (UnknownHostException e) {
	    throw new RuntimeException("Could not retrieve localhost id.", e);
	}
    }

    public static SystemInformation getInstance() {
	return instance;
    }

    private final SystemInformationProvider provider;

    public SystemInformation() {
	OS os = LocalNode.getOS();
	switch (os) {
	case LINUX:
	    provider = new LinuxInformationProvider();
	    break;
	default:
	    throw new RuntimeException("Unsupport operating system '" + os.name() + "' found.");
	}
    }

    @Override
    public NodeInformation getNodeInformation() {
	return provider.getNodeInformation();
    }

    @Override
    public NodeDetails getNodeDetails() {
	return provider.getNodeDetails();
    }

    @Override
    public MemoryInformation getMemoryInformation() {
	return provider.getMemoryInformation();
    }

    @Override
    public Set<ProcessInformation> getProcessInformation() {
	return provider.getProcessInformation();
    }

    @Override
    public ProcessDetails getProcessDetails(int pid) {
	return provider.getProcessDetails(pid);
    }

}
