package com.puresoltechnologies.toolshed.client.profiles;

import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryCreator;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryNulTerminateString;

public class MethodInvocationEntry {

    private final String className;
    private final String methodName;
    private final String descriptor;

    @BinaryCreator
    public MethodInvocationEntry( //
	    @BinaryNulTerminateString("className") String className, //
	    @BinaryNulTerminateString("methodName") String methodName, //
	    @BinaryNulTerminateString("descriptor") String descriptor //
    ) {
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
