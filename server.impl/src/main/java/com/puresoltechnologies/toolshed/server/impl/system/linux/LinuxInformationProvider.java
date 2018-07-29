package com.puresoltechnologies.toolshed.server.impl.system.linux;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import com.puresoltechnologies.toolshed.server.api.system.SystemLoad;
import com.puresoltechnologies.toolshed.server.impl.system.SystemInformation;
import com.puresoltechnologies.toolshed.server.impl.system.SystemInformationProvider;

public class LinuxInformationProvider implements SystemInformationProvider {

    private static final Logger logger = LoggerFactory.getLogger(LinuxInformationProvider.class);

    private static final Pattern cpuLoadPattern = Pattern.compile(
	    "^cpu(\\d+)?\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)");
    private static final Pattern meminfoPattern = Pattern.compile("^(\\S+):\\s*(\\d+)\\s*(\\S*)$");

    private final int cpus = Runtime.getRuntime().availableProcessors();
    private final OS os = OS.local();
    private final String version = System.getProperty("os.version");
    private final String architecture = System.getProperty("os.arch");

    private LoadInfo lastSystemLoad = null;
    private Map<Integer, LoadInfo> lastCpuLoad = new HashMap<>();

    @Override
    public NodeInformation getNodeInformation() {
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
	MemoryInformation memoryInformation = getMemoryInformation();
	return new NodeDetails(SystemInformation.getNodeName(), os, architecture, version, cpus,
		memoryInformation.getMemTotal(), memoryInformation.getSwapTotal(), nics);
    }

    @Override
    public SystemLoad getLoad() {
	try (FileInputStream fileInputStream = new FileInputStream(new File("/proc/stat"));
		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(fileInputStream, Charset.defaultCharset()))) {
	    String line = bufferedReader.readLine();
	    LoadInfo lastSystemLoad = this.lastSystemLoad;
	    LoadInfo systemLoad = parseCPULoad(line);
	    this.lastSystemLoad = systemLoad;
	    double avgLoad = calculateAvgLoad(lastSystemLoad, systemLoad);
	    System.out.println("system: " + avgLoad + "%");
	    for (int id = 0; id < cpus; id++) {
		line = bufferedReader.readLine();
		LoadInfo cpuLoad = parseCPULoad(line);
		int cpuId = cpuLoad.getCpuId();
		LoadInfo lastCpuLoad = this.lastCpuLoad.get(cpuId);
		this.lastCpuLoad.put(cpuId, cpuLoad);
		avgLoad = calculateAvgLoad(lastCpuLoad, cpuLoad);
		System.out.println("cpu" + cpuId + ": " + avgLoad + "%");
	    }
	    return null;
	} catch (IOException e) {
	    logger.error("Could not determine CPU load.", e);
	    return null;
	}
    }

    private LoadInfo parseCPULoad(String line) {
	Matcher matcher = cpuLoadPattern.matcher(line);
	if (matcher.matches()) {
	    String cpuIdString = matcher.group(1);
	    int cpuId = cpuIdString == null ? -1 : Integer.parseInt(cpuIdString);
	    long user = Long.parseLong(matcher.group(2));
	    long nice = Long.parseLong(matcher.group(3));
	    long system = Long.parseLong(matcher.group(4));
	    long idle = Long.parseLong(matcher.group(5));
	    long iowait = Long.parseLong(matcher.group(6));
	    long irq = Long.parseLong(matcher.group(7));
	    long softirq = Long.parseLong(matcher.group(8));
	    long steal = Long.parseLong(matcher.group(9));
	    long quest = Long.parseLong(matcher.group(10));
	    long questNice = Long.parseLong(matcher.group(11));
	    return new LoadInfo(cpuId, user, nice, system, idle, iowait, irq, softirq, steal, quest, questNice);
	}
	return null;
    }

    private double calculateAvgLoad(LoadInfo lastLoad, LoadInfo load) {
	if (lastLoad == null) {
	    return 0.0;
	}
	long sum1 = load.getUser() + load.getNice() + load.getSystem();
	long sum2 = lastLoad.getUser() + lastLoad.getNice() + lastLoad.getSystem();
	return (double) (sum1 - sum2) / (double) (load.getTotal() - lastLoad.getTotal());
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
		.mapToInt(directory -> {
		    try {
			return Integer.parseInt(directory.getName());
		    } catch (NumberFormatException e) {
			return 0;
		    }
		}) //
		.filter(pid -> pid > 0) //
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
