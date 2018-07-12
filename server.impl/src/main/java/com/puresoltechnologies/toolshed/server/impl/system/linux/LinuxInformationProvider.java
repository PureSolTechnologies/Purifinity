package com.puresoltechnologies.toolshed.server.impl.system.linux;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessStatus;
import com.puresoltechnologies.toolshed.server.impl.system.SystemInformationProvider;

public class LinuxInformationProvider implements SystemInformationProvider {

    private static final Logger logger = LoggerFactory.getLogger(LinuxInformationProvider.class);

    @Override
    public Set<ProcessInformation> getProcessInformation() {
	Set<Integer> pids = readPids();
	Set<ProcessInformation> processInformation = new HashSet<>();
	for (int pid : pids) {
	    processInformation.add(getProcessInformation(pid));
	}
	return processInformation;
    }

    private Set<Integer> readPids() {
	HashSet<Integer> pids = new HashSet<>();
	File procDirectory = new File("/proc");
	Arrays.asList(procDirectory.listFiles()) //
		.parallelStream() //
		.filter(file -> file.isDirectory()) //
		.filter(directory -> {
		    try {
			Integer.parseInt(directory.getName());
			return true;
		    } catch (NumberFormatException e) {
			return false;
		    }
		}) //
		.mapToInt(pidDirectory -> Integer.parseInt(pidDirectory.getName())) //
		.forEach(pid -> pids.add(pid));
	return pids;
    }

    @Override
    public ProcessInformation getProcessInformation(int pid) {
	try (FileInputStream fileInputStream = new FileInputStream(new File("/proc/" + pid + "/stat"))) {
	    return new ProcessInformation(pid, 0, LinuxProcesses.readComm(pid), ProcessStatus.RUNNING,
		    LinuxProcesses.readCmdline(pid));
	} catch (IOException e) {
	    logger.error("Could not retrieve process information.", e);
	    return null;
	}
    }

}
