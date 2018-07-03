package com.puresoltechnologies.toolshed.server.impl.nodes;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.toolshed.server.api.nodes.NIC;
import com.puresoltechnologies.toolshed.server.api.nodes.Node;
import com.puresoltechnologies.toolshed.server.api.nodes.OS;

public class NodeManager {

    private static final List<Node> nodes = new ArrayList<>();
    static {
	try {
	    nodes.add(createLocalNode());
	} catch (UnknownHostException | SocketException e) {
	    throw new RuntimeException("Could not initialize local node.", e);
	}
    }

    private static Node createLocalNode() throws UnknownHostException, SocketException {
	InetAddress localHost = InetAddress.getLocalHost();
	String id = localHost.getCanonicalHostName();
	Set<NIC> nics = new HashSet<>();
	NetworkInterface.getNetworkInterfaces().asIterator().forEachRemaining(iface -> {
	    nics.add(new NIC(iface.getDisplayName(), iface.getName()));
	});

	int cpus = Runtime.getRuntime().availableProcessors();
	OS os = OS.local();
	String version = System.getProperty("os.version");
	String architecture = System.getProperty("os.arch");

	return new Node(id, os, architecture, version, cpus, nics);
    }

    public static List<Node> getNodes() {
	return nodes;
    }

}
