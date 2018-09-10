package com.puresoltechnologies.toolshed.client.profiles.graph;

public class MethodVertex extends CodeGraphVertex {

    private final String className;
    private final String methodName;
    private final String descriptor;

    public MethodVertex(String className, String methodName, String descriptor) {
	super(className + "#" + methodName + descriptor);
	this.className = className;
	this.methodName = methodName;
	this.descriptor = descriptor;
    }

    public String getClassName() {
	return className;
    }

    public String getMethodName() {
	return methodName;
    }

    public String getDescriptor() {
	return descriptor;
    }

    public void addInvokation(InvokesEdge invokesEdge) {
	invokesEdge.setStartVertex(this);
	addEdge(invokesEdge);
    }
}
