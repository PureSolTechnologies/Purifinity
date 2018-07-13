package com.puresoltechnologies.toolshed.server.impl.system.linux;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.streaming.streams.OptimizedFileInputStream;
import com.puresoltechnologies.toolshed.server.api.nodes.MemoryInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.NIC;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.OS;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessStatus;
import com.puresoltechnologies.toolshed.server.impl.system.SystemInformation;
import com.puresoltechnologies.toolshed.server.impl.system.SystemInformationProvider;

public class LinuxInformationProvider implements SystemInformationProvider {

    private static final Logger logger = LoggerFactory.getLogger(LinuxInformationProvider.class);

    private static final Pattern meminfoPattern = Pattern.compile("^(\\S+):\\s*(\\d+)\\s*(\\S*)$");

    @Override
    public NodeInformation getNodeInformation() {
	int cpus = Runtime.getRuntime().availableProcessors();
	OS os = OS.local();
	String version = System.getProperty("os.version");
	String architecture = System.getProperty("os.arch");
	MemoryInformation memoryInformation = getMemoryInformation();
	return new NodeInformation(SystemInformation.getNodeName(), os, architecture, version, cpus,
		memoryInformation.getMemTotal(), memoryInformation.getSwapTotal());
    }

    @Override
    public NodeDetails getNodeDetails() {
	Set<NIC> nics = new HashSet<>();
	try {
	    NetworkInterface.getNetworkInterfaces().asIterator().forEachRemaining(iface -> {
		nics.add(new NIC(iface.getDisplayName(), iface.getName()));
	    });
	} catch (SocketException e) {
	    logger.error("Could not find NICs.", e);
	}
	NodeInformation nodeInformation = getNodeInformation();
	return new NodeDetails(nodeInformation.getName(), nodeInformation.getOS(), nodeInformation.getArchitecture(),
		nodeInformation.getOSVersion(), nodeInformation.getCpus(), nodeInformation.getMemTotal(),
		nodeInformation.getSwapTotal(), nics);
    }

    @Override
    public MemoryInformation getMemoryInformation() {
	File meminfoFile = new File("/proc/meminfo");
	try (FileInputStream fileInputStream = new FileInputStream(meminfoFile);
		InputStreamReader reader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(reader)) {
	    long memTotal = -1;
	    long swapTotal = -1;
	    String line = bufferedReader.readLine();
	    while (line != null) {
		Matcher matcher = meminfoPattern.matcher(line);
		if (matcher.matches()) {
		    String name = matcher.group(1);
		    String value = matcher.group(2);
		    String unit = matcher.group(3);
		    if ("MemTotal".equals(name)) {
			memTotal = Long.parseLong(value);
			if ("kB".equalsIgnoreCase(unit)) {
			    memTotal *= 1024;
			} else if ("MB".equalsIgnoreCase(unit)) {
			    memTotal *= 1024 * 1024;
			}
		    } else if ("SwapTotal".equals(name)) {
			swapTotal = Long.parseLong(value);
			if ("kB".equalsIgnoreCase(unit)) {
			    swapTotal *= 1024;
			} else if ("MB".equalsIgnoreCase(unit)) {
			    swapTotal *= 1024 * 1024;
			}
		    }
		}
		line = bufferedReader.readLine();
	    }
	    return new MemoryInformation(memTotal, swapTotal);
	} catch (IOException e) {
	    logger.error("Could not read memory information.", e);
	    return null;
	}
    }

    @Override
    public Set<ProcessInformation> getProcessInformation() {
	Set<Integer> pids = readPids();
	Set<ProcessInformation> processInformation = new HashSet<>();
	for (int pid : pids) {
	    processInformation.add(getProcessDetails(pid));
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
    public ProcessDetails getProcessDetails(int pid) {
	try (OptimizedFileInputStream fileInputStream = new OptimizedFileInputStream(
		new File("/proc/" + pid + "/stat"))) {
	    return new ProcessDetails(pid, 0, LinuxProcesses.readComm(pid), ProcessStatus.RUNNING,
		    LinuxProcesses.readCmdline(pid));
	} catch (IOException e) {
	    logger.error("Could not retrieve process information.", e);
	    return null;
	}
    }

}
