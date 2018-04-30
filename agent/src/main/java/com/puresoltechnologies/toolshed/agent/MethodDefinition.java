package com.puresoltechnologies.toolshed.agent;

public class MethodDefinition {

    private final String className;
    private final String methodName;
    private final String descriptor;

    public MethodDefinition(String className, String methodName, String descriptor) {
	super();
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

}
