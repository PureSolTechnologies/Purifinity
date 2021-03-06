package com.puresoltechnologies.toolshed.server.impl.nodes;

import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.toolshed.server.api.nodes.NodeDetails;
import com.puresoltechnologies.toolshed.server.api.nodes.NodeInformation;
import com.puresoltechnologies.toolshed.server.api.nodes.ProcessInformation;
import com.puresoltechnologies.toolshed.server.impl.system.SystemInformation;

public class NodeManager {

    private static final String localNodeName = SystemInformation.getNodeName();

    private static NodeManager instance;

    public static void initialize(List<URL> upstreamServers) {
	instance = new NodeManager();
    }

    public static NodeManager getInstance() {
	return instance;
    }

    public static boolean isLocalNode(String nodeName) {
	return localNodeName.equalsIgnoreCase(nodeName);
    }

    private final SystemInformation localSystemInformation = SystemInformation.getInstance();
    private final Set<NodeInformation> nodes = new HashSet<>();

    public NodeManager() {
	try {
	    nodes.add(createLocalNode());
	    nodes.addAll(createRemoteNodes());
	} catch (UnknownHostException | SocketException e) {
	    throw new RuntimeException("Could not initialize local node.", e);
	}
    }

    private NodeInformation createLocalNode() throws UnknownHostException, SocketException {
	return localSystemInformation.getNodeInformation();
    }

    private List<? extends NodeInformation> createRemoteNodes() {
	List<NodeInformation> nodes = new ArrayList<>();
	return nodes;
    }

    public Set<NodeInformation> getNodes() {
	return nodes;
    }

    public NodeDetails getNode(String nodeName) {
	if (isLocalNode(nodeName)) {
	    return localSystemInformation.getNodeDetails();
	} else {
	    // TODO add client call to nodeName
	    return null;
	}
    }

    public Set<ProcessInformation> getProcesses(String nodeName) {
	if (isLocalNode(nodeName)) {
	    return localSystemInformation.getProcessInformation();
	} else {
	    // TODO add client call to nodeName
	    return null;
	}
    }

    public ProcessInformation getProcess(String nodeName, int pid) {
	if (isLocalNode(nodeName)) {
	    return localSystemInformation.getProcessDetails(pid);
	} else {
	    // TODO add client call to nodeName
	    return null;
	}
    }

}
