package com.puresoltechnologies.toolshed.client.profiles.graph;

public class ClassVertex extends CodeGraphVertex {

    private final String className;

    public ClassVertex(String className) {
	super(className);
	this.className = className;
    }

    public String getClassName() {
	return className;
    }

    public void addImplementation(ImplementsEdge implementsEdge) {
	implementsEdge.setStartVertex(this);
	addEdge(implementsEdge);
    }

}
