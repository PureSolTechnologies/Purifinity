package com.puresoltechnologies.toolshed.client.profiles.graph;

public class ImplementsEdge extends CodeGraphEdge {

    public ImplementsEdge(MethodVertex methodVertex) {
	super("implements");
	setEndVertex(methodVertex);
    }

}
