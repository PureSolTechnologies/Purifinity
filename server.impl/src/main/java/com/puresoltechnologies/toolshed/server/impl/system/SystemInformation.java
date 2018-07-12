package com.puresoltechnologies.toolshed.server.impl.system;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.toolshed.server.api.nodes.OS;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;
import com.puresoltechnologies.toolshed.server.impl.system.linux.LinuxInformationProvider;

public class SystemInformation implements SystemInformationProvider {

    private static final Logger logger = LoggerFactory.getLogger(SystemInformation.class);

    private static final SystemInformation instance = new SystemInformation();

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
    public Set<ProcessInformation> getProcessInformation() {
	return provider.getProcessInformation();
    }

    @Override
    public ProcessInformation getProcessInformation(int pid) {
	return provider.getProcessInformation(pid);
    }
}
