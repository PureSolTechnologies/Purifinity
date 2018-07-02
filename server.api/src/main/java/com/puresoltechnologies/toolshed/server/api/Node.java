package com.puresoltechnologies.toolshed.server.api;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Node {

    public final String id;
    public final Set<String> hostnames = new HashSet<>();
    public final Set<String> ipAddresses = new HashSet<>();

    public Node(String id, Set<String> hostnames, Set<String> ipAddresses) {
	super();
	this.id = id;
	this.hostnames.addAll(hostnames);
	this.ipAddresses.addAll(ipAddresses);
    }

    public String getId() {
	return id;
    }

    public Set<String> getHostnames() {
	return Collections.unmodifiableSet(hostnames);
    }

    public Set<String> getIpAddresses() {
	return Collections.unmodifiableSet(ipAddresses);
    }

}
