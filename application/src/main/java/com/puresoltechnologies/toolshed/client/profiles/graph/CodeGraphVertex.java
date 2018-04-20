package com.puresoltechnologies.toolshed.client.profiles.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.graphs.graph.Vertex;

public class CodeGraphVertex implements Vertex<CodeGraphVertex, CodeGraphEdge> {

    private final Set<CodeGraphEdge> edges = new HashSet<>();

    protected void addEdge(CodeGraphEdge edge) {
	edges.add(edge);
    }

    @Override
    public Set<CodeGraphEdge> getEdges() {
	return Collections.unmodifiableSet(edges);
    }

}
