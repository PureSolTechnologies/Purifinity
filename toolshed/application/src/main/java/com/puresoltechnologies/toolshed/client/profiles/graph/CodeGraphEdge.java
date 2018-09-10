package com.puresoltechnologies.toolshed.client.profiles.graph;

import com.puresoltechnologies.graphs.graph.Edge;
import com.puresoltechnologies.graphs.graph.Pair;

public class CodeGraphEdge implements Edge<CodeGraphVertex, CodeGraphEdge> {

    private final String type;
    private CodeGraphVertex startVertex = null;
    private CodeGraphVertex endVertex = null;

    public CodeGraphEdge(String type) {
	super();
	this.type = type;
    }

    void setStartVertex(CodeGraphVertex codeGraphVertex) {
	startVertex = codeGraphVertex;
    }

    void setEndVertex(CodeGraphVertex codeGraphVertex) {
	endVertex = codeGraphVertex;
    }

    @Override
    public Pair<CodeGraphVertex> getVertices() {
	return new Pair<>(startVertex, endVertex);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((endVertex == null) ? 0 : endVertex.hashCode());
	result = prime * result + ((startVertex == null) ? 0 : startVertex.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	CodeGraphEdge other = (CodeGraphEdge) obj;
	if (endVertex == null) {
	    if (other.endVertex != null)
		return false;
	} else if (!endVertex.equals(other.endVertex))
	    return false;
	if (startVertex == null) {
	    if (other.startVertex != null)
		return false;
	} else if (!startVertex.equals(other.startVertex))
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	Pair<CodeGraphVertex> vertices = getVertices();
	return vertices.getFirst() + " -[" + type + "]-> " + vertices.getSecond();
    }

}
