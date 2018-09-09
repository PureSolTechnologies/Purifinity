package com.puresoltechnologies.toolshed.client.profiles;

import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryCreator;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryNulTerminateString;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryUnsignedShort;

public class MethodDeclarationEntry {

    private final String methodName;
    private final String descriptor;
    private final int methodId;

    @BinaryCreator
    public MethodDeclarationEntry( //
	    @BinaryNulTerminateString("methodName") String methodName, //
	    @BinaryNulTerminateString("descriptor") String descriptor, //
	    @BinaryUnsignedShort("methodId") int methodId //
    ) {
	this.methodName = methodName;
	this.descriptor = descriptor;
	this.methodId = methodId;
    }

    public String getMethodName() {
	return methodName;
    }

    public String getDescriptor() {
	return descriptor;
    }

    public int getMethodId() {
	return methodId;
    }

}
