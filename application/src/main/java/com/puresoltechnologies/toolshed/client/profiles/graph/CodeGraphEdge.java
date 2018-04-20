package com.puresoltechnologies.toolshed.client.profiles.graph;

import com.puresoltechnologies.graphs.graph.Edge;
import com.puresoltechnologies.graphs.graph.Pair;

public class CodeGraphEdge implements Edge<CodeGraphVertex, CodeGraphEdge> {

    private CodeGraphVertex startVertex = null;
    private CodeGraphVertex endVertex = null;

    void addStartVertex(CodeGraphVertex codeGraphVertex) {
	startVertex = codeGraphVertex;
    }

    void addEndVertex(CodeGraphVertex codeGraphVertex) {
	endVertex = codeGraphVertex;
    }

    @Override
    public Pair<CodeGraphVertex> getVertices() {
	return new Pair<>(startVertex, endVertex);
    }

}
