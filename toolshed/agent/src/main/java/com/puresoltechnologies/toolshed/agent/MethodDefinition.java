package com.puresoltechnologies.toolshed.agent;

import java.util.Objects;

public class MethodDefinition {

    private final String className;
    private final String methodName;
    private final String descriptor;

    public MethodDefinition(String className, String methodName, String descriptor) {
	super();
	Objects.requireNonNull(className, "className must not be null");
	Objects.requireNonNull(methodName, "methodName must not be null");
	Objects.requireNonNull(descriptor, "descriptor must not be null");
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

    @Override
    public String toString() {
	return className + "#" + methodName + descriptor;
    }

}
