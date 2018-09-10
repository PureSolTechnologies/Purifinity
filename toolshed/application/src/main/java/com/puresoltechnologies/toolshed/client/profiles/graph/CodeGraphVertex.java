package com.puresoltechnologies.toolshed.client.profiles.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.graphs.graph.Vertex;

public class CodeGraphVertex implements Vertex<CodeGraphVertex, CodeGraphEdge> {

    private final String id;
    private final Set<CodeGraphEdge> edges = new HashSet<>();
    private final int hash;

    public CodeGraphVertex(String id) {
	super();
	this.id = id;
	this.hash = id.hashCode();
    }

    protected void addEdge(CodeGraphEdge edge) {
	edges.add(edge);
    }

    @Override
    public Set<CodeGraphEdge> getEdges() {
	return Collections.unmodifiableSet(edges);
    }

    @Override
    public int hashCode() {
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	if (hashCode() != obj.hashCode()) {
	    return false;
	}
	CodeGraphVertex other = (CodeGraphVertex) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return id;
    }
}
