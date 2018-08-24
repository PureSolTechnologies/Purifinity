package com.puresoltechnologies.toolshed.client.profiles.graph;

public class InvokesEdge extends CodeGraphEdge {

    public InvokesEdge(MethodVertex methodVertex) {
	addEndVertex(methodVertex);
    }

}
