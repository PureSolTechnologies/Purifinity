package com.puresoltechnologies.toolshed.client.profiles;

import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryCreator;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinaryNulTerminateString;
import com.puresoltechnologies.streaming.binary.mapper.annotations.BinarySignedShort;

public class MethodDeclarationEntry {

    private final String methodName;
    private final short methodId;

    @BinaryCreator
    public MethodDeclarationEntry( //
	    @BinaryNulTerminateString("methodName") String methodName, //
	    @BinarySignedShort("methodId") short methodId //
    ) {
	this.methodName = methodName;
	this.methodId = methodId;
    }

    public String getMethodName() {
	return methodName;
    }

    public short getMethodId() {
	return methodId;
    }

}
